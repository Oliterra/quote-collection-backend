package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.AuthorVO;
import edu.quote.collection.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/authors")
@RestController
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping()
    public List<AuthorVO> getAllBooks() {
        return authorService.getAllAuthors();
    }
}
