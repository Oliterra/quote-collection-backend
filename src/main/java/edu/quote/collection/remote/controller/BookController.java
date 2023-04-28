package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.BookFilterVO;
import edu.quote.collection.remote.vo.BookVO;
import edu.quote.collection.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/filter")
    public List<BookVO> getFilteredBooks(@RequestBody BookFilterVO bookFilter) {
        return bookService.getFilteredBooks(bookFilter);
    }

    @PostMapping()
    public BookVO createBook(@RequestBody BookVO book) {
        return bookService.createBook(book);
    }

    @PatchMapping()
    public BookVO editBook(@RequestBody BookVO book) {
        return bookService.editBook(book);
    }
}
