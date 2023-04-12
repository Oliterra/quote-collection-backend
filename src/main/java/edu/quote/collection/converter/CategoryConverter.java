package edu.quote.collection.converter;

import edu.quote.collection.dbaccess.entity.CategoryEntity;
import edu.quote.collection.remote.vo.CategoryVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryConverter extends BaseConverter {

    public List<CategoryVO> convertToVOList(List<CategoryEntity> categoryEntities) {
        List<CategoryVO> categoryVOList = new ArrayList<>();
        if (categoryEntities != null) {
            categoryVOList = categoryEntities.stream()
                    .map(this::convertToVO)
                    .collect(Collectors.toList());
        }
        return categoryVOList;
    }

    public CategoryVO convertToVO(CategoryEntity categoryEntity) {
        return super.convert(categoryEntity, CategoryVO.class);
    }
}
