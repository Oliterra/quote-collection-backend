package edu.quote.collection.service;

import edu.quote.collection.converter.BookConverter;
import edu.quote.collection.dbaccess.repository.BookRepository;
import edu.quote.collection.remote.vo.BookVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookConverter bookConverter;

    public List<BookVO> getAllBooks() {
        return bookConverter.convertToVOList(bookRepository.findAll());
    }
}
