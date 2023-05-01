package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.AuthorVO;
import edu.quote.collection.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/authors")
@RestController
@AllArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping()
    public List<AuthorVO> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping()
    public AuthorVO createAuthor(@RequestBody AuthorVO author) {
        return authorService.createAuthor(author);
    }
}
