package edu.quote.collection.service;

import edu.quote.collection.converter.QuoteConverter;
import edu.quote.collection.converter.TagConverter;
import edu.quote.collection.dbaccess.entity.*;
import edu.quote.collection.dbaccess.repository.*;
import edu.quote.collection.remote.vo.GroupVO;
import edu.quote.collection.remote.vo.QuoteFilterVO;
import edu.quote.collection.remote.vo.QuoteListVO;
import edu.quote.collection.remote.vo.QuoteMainInfoVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class QuoteService {

    private final BookRepository bookRepository;
    private final GroupRepository groupRepository;
    private final QuoteRepository quoteRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;
    private final QuoteConverter quoteConverter;
    private final TagConverter tagConverter;
    private final BookService bookService;

    public QuoteListVO getAllQuotesMainInfoPage(Pageable paging) {
        QuoteListVO quoteList = new QuoteListVO();
        quoteList.setCount(quoteRepository.count());
        List<QuoteEntity> quoteEntities = quoteRepository.findAllByOrderByCreationTimeDesc(paging).stream().toList();
        List<QuoteMainInfoVO> quotes = quoteConverter.convertToMainInfoVOList(quoteEntities);
        quoteList.setQuotes(quotes);
        return quoteList;
    }

    public QuoteListVO getUserQuotesMainInfoPage(Long userId, Pageable paging) {
        QuoteListVO quoteList = new QuoteListVO();
        UserEntity user = userRepository.findById(userId).get();
        quoteList.setCount(quoteRepository.countByUser(user));
        List<QuoteEntity> quoteEntities = quoteRepository.findAllByUserOrderByCreationTimeDesc(user, paging).stream().toList();
        List<QuoteMainInfoVO> quotes = quoteConverter.convertToMainInfoVOList(quoteEntities);
        quoteList.setQuotes(quotes);
        return quoteList;
    }

    public QuoteListVO getFilteredQuotes(QuoteFilterVO quoteFilter) {
        QuoteListVO filteredQuotesList = new QuoteListVO();
        List<QuoteEntity> quoteEntities = new ArrayList<>();
        if (!bookService.bookBelongsToAuthor(quoteFilter.getBookId(), quoteFilter.getAuthorId()) || !bookService.bookBelongsToCategories(quoteFilter.getBookId(), quoteFilter.getCategoryIds())) {
            filteredQuotesList.setCount(0L);
        } else {
            List<QuoteEntity> filteredQuoteEntities = quoteRepository.findQuotesByTextAndBookAndAuthorAndCategories(quoteFilter.getAuthorId(), quoteFilter.getBookId(), quoteFilter.getText()).stream()
                    .filter(quote -> {
                        List<Long> quoteCategoryIds = quote.getBook().getCategories().stream().map(PersistableEntity::getId).toList();
                        List<Long> quoteTagIds = quote.getTags().stream().map(PersistableEntity::getId).toList();
                        return quoteCategoryIds.containsAll(quoteFilter.getCategoryIds()) && quoteTagIds.containsAll(quoteFilter.getTagIds());
                    }).toList();
            quoteEntities.addAll(filteredQuoteEntities);
        }
        filteredQuotesList.setCount((long) quoteEntities.size());
        filteredQuotesList.setQuotes(quoteConverter.convertToMainInfoVOList(quoteEntities));
        return filteredQuotesList;
    }

    public void createQuote(QuoteMainInfoVO quoteMainInfo) {
        UserEntity user = userRepository.findById(quoteMainInfo.getUserId()).get();
        BookEntity book = bookRepository.findById(quoteMainInfo.getBook().getId()).get();
        List<Long> groupIds = quoteMainInfo.getGroups().stream().map(GroupVO::getId).toList();
        List<GroupEntity> groups = groupRepository.findAllById(groupIds);
        List<TagEntity> tags = new ArrayList<>();
        quoteMainInfo.getTags().forEach(tag -> {
            TagEntity tagEntity = null;
            if (tag.getId() != null) {
                tagEntity = tagRepository.findById(tag.getId()).get();
            } else {
                tagEntity = tagRepository.save(tagConverter.convertToEntity(tag));
            }
            tags.add(tagEntity);
        });
        QuoteEntity quote = new QuoteEntity();
        quote.setText(quoteMainInfo.getText());
        quote.setIsPublic(quoteMainInfo.getIsPublic());
        quote.setNumberOfVotes(0);
        quote.setRating((double) 0);
        quote.setCreationTime(OffsetDateTime.now());
        quote.setUser(user);
        quote.setBook(book);
        quote.setGroups(groups);
        quote.setTags(tags);
        quoteRepository.save(quote);
    }
}
