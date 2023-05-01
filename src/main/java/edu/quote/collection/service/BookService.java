package edu.quote.collection.service;

import edu.quote.collection.converter.BookConverter;
import edu.quote.collection.dbaccess.entity.AuthorEntity;
import edu.quote.collection.dbaccess.entity.BookEntity;
import edu.quote.collection.dbaccess.entity.CategoryEntity;
import edu.quote.collection.dbaccess.entity.PersistableEntity;
import edu.quote.collection.dbaccess.repository.*;
import edu.quote.collection.remote.vo.BookFilterVO;
import edu.quote.collection.remote.vo.BookVO;
import edu.quote.collection.remote.vo.CategoryVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Transactional
@Service
@AllArgsConstructor
public class BookService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;
    private final BookConverter bookConverter;

    public List<BookVO> getAllBooks() {
        return convertToVOList(bookRepository.findAllByOrderByNameAsc());
    }

    public List<BookVO> getFilteredBooks(BookFilterVO bookFilter) {
        List<BookEntity> bookEntities = new ArrayList<>();
        Long bookId = bookFilter.getBookId();
        Long authorId = bookFilter.getAuthorId();
        Long userId = bookFilter.getUserId();
        List<Long> categoryIds = bookFilter.getCategoryIds();
        if (bookBelongsToAuthor(bookId, authorId) || bookBelongsToCategories(bookId, categoryIds)) {
            if (bookId == null && authorId == null) {
                bookEntities = bookRepository.findAll();
            } else {
                if (authorId == null) {
                    bookEntities = Collections.singletonList(bookRepository.getById(bookId));
                } else {
                    AuthorEntity authorEntity = authorRepository.getById(authorId);
                    bookEntities = (bookId == null)
                            ? bookRepository.findAllByAuthorOrderByNameAsc(authorEntity)
                            : bookRepository.findAllByIdAndAuthorOrderByNameAsc(bookId, authorEntity);
                }
            }
            if (categoryIds != null && !categoryIds.isEmpty()) {
                bookEntities = bookEntities.stream()
                        .filter(book -> book.getCategories().stream().map(PersistableEntity::getId).toList().containsAll(categoryIds))
                        .toList();
            }
            if (userId != null) {
                bookEntities = bookEntities.stream()
                        .filter(book -> Objects.equals(book.getUser().getId(), userId))
                        .toList();
            }
        }
        return convertToVOList(bookEntities);
    }

    public boolean bookBelongsToAuthor(Long bookId, Long authorId) {
        if (bookId == null || authorId == null) {
            return true;
        }
        return bookRepository.existsByIdAndAuthor(bookId, authorId);
    }

    public boolean bookBelongsToCategories(Long bookId, List<Long> categoryIds) {
        if (bookId == null || categoryIds == null || CollectionUtils.isEmpty(categoryIds)) {
            return true;
        }
        boolean bookBelongsToCategories = true;
        for (Long categoryId : categoryIds) {
            if (!bookRepository.existsByIdAndCategory(bookId, categoryId)) {
                bookBelongsToCategories = false;
            }
        }
        return bookBelongsToCategories;
    }

    public BookVO createBook(BookVO book) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(book.getName());
        authorRepository.findById(book.getAuthor().getId()).ifPresent(bookEntity::setAuthor);
        userRepository.findById(book.getUserId()).ifPresent(bookEntity::setUser);
        List<Long> categoryIds = book.getCategories().stream().map(CategoryVO::getId).toList();
        bookEntity.setCategories(categoryRepository.findAllById(categoryIds));
        return bookConverter.convertToVO(bookRepository.save(bookEntity));
    }

    public BookVO editBook(BookVO updatedBook) {
        BookEntity bookEntity = bookRepository.getById(updatedBook.getId());
        Long authorId = updatedBook.getAuthor().getId();
        if (!authorId.equals(bookEntity.getAuthor().getId())) {
            authorRepository.findById(authorId).ifPresent(bookEntity::setAuthor);
        }
        List<Long> updatedCategoryIds = updatedBook.getCategories().stream().map(CategoryVO::getId).toList();
        List<Long> currentCategoryIds = bookEntity.getCategories().stream().map(CategoryEntity::getId).toList();
        if (!(updatedCategoryIds.containsAll(currentCategoryIds) && currentCategoryIds.containsAll(updatedCategoryIds))) {
            bookEntity.setCategories(categoryRepository.findAllById(updatedCategoryIds));
        }
        return bookConverter.convertToVO(bookRepository.save(bookEntity));
    }

    private List<BookVO> convertToVOList(List<BookEntity> bookEntities) {
        return bookEntities.stream()
                .map(book -> {
                    BookVO bookVO = bookConverter.convertToVO(book);
                    bookVO.setQuotesCount(quoteRepository.getCountByBook(book));
                    return bookVO;
                }).collect(Collectors.toList());
    }
}
