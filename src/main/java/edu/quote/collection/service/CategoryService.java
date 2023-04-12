package edu.quote.collection.service;

import edu.quote.collection.converter.CategoryConverter;
import edu.quote.collection.dbaccess.repository.CategoryRepository;
import edu.quote.collection.remote.vo.CategoryVO;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Transactional
@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public List<CategoryVO> getAllCategories() {
        return categoryConverter.convertToVOList(categoryRepository.findAllByOrderByNameAsc());
    }
}
