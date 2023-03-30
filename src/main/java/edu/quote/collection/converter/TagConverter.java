package edu.quote.collection.converter;

import edu.quote.collection.dbaccess.entity.TagEntity;
import edu.quote.collection.remote.vo.TagVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagConverter extends BaseConverter {

    private static final String HASH_SYMBOL = "#";

    public List<TagVO> convertToVOList(List<TagEntity> tagEntityList) {
        List<TagVO> tagVOList = new ArrayList<>();
        if (tagEntityList != null) {
            tagVOList = tagEntityList.stream()
                    .map(this::convertToVO)
                    .collect(Collectors.toList());
        }
        return tagVOList;
    }

    public TagVO convertToVO(TagEntity tagEntity) {
        TagVO tagVO = new TagVO();
        tagVO.setId(tagEntity.getId());
        tagVO.setName(HASH_SYMBOL + tagEntity.getName());
        return tagVO;
    }

    public TagEntity convertToEntity(TagVO tagVO) {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setName(tagVO.getName().substring(1));
        return tagEntity;
    }
}
