package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.TagVO;
import edu.quote.collection.service.TagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/tags")
@RestController
@AllArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping()
    public List<TagVO> getAllTags() {
        return tagService.getAllTags();
    }
}
