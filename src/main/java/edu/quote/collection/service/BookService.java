package edu.quote.collection.service;

import edu.quote.collection.converter.BookConverter;
import edu.quote.collection.dbaccess.repository.BookRepository;
import edu.quote.collection.remote.vo.BookVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public List<BookVO> getAllBooks() {
        return bookConverter.convertToVOList(bookRepository.findAllByOrderByNameAsc());
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
}
