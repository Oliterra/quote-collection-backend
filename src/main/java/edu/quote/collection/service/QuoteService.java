package edu.quote.collection.service;

import edu.quote.collection.converter.QuoteConverter;
import edu.quote.collection.converter.TagConverter;
import edu.quote.collection.dbaccess.entity.*;
import edu.quote.collection.dbaccess.repository.*;
import edu.quote.collection.remote.vo.GroupVO;
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

    public long getAllQuotesCount() {
        return quoteRepository.count();
    }

    public List<QuoteMainInfoVO> getAllQuotesMainInfoPage(Pageable paging) {
        List<QuoteEntity> s = quoteRepository.findAllByOrderByCreationTimeDesc(paging).stream().toList();
        return quoteConverter.convertToMainInfoVOList(s);
    }

    public long getUserQuotesCount(Long userId) {
        UserEntity user = userRepository.findById(userId).get();
        return quoteRepository.countByUser(user);
    }

    public List<QuoteMainInfoVO> getUserQuotesMainInfoPage(Long userId, Pageable paging) {
        UserEntity user = userRepository.findById(userId).get();
        return quoteConverter.convertToMainInfoVOList(quoteRepository.findAllByUserOrderByCreationTimeDesc(user, paging).stream().toList());
    }

    public void createQuote(QuoteMainInfoVO quoteMainInfo) {
        UserEntity user = userRepository.findById(quoteMainInfo.getUserId()).get();
        BookEntity book = bookRepository.findById(quoteMainInfo.getBookId()).get();
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
