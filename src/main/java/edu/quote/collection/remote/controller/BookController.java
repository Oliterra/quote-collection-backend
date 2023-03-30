package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.BookVO;
import edu.quote.collection.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/books")
@RestController
@AllArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping()
    public List<BookVO> getAllBooks() {
        return bookService.getAllBooks();
    }
}
