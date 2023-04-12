package edu.quote.collection.remote.controller;

import edu.quote.collection.remote.vo.CategoryVO;
import edu.quote.collection.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/categories")
@RestController
@AllArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping()
    public List<CategoryVO> getAllCategory() {
        return categoryService.getAllCategories();
    }
}
