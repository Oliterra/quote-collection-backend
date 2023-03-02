package edu.quote.collection.converter;

import edu.quote.collection.dbaccess.entity.TagEntity;
import edu.quote.collection.remote.vo.TagVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TagConverter extends BaseConverter {

    public List<TagVO> convertToVOList(List<TagEntity> tagEntityList) {
        List<TagVO> voList = new ArrayList<>();
        if (tagEntityList != null) {
            voList = tagEntityList.stream()
                .map(this::convertToVO)
                .collect(Collectors.toList());
        }
        return voList;
    }

    public TagVO convertToVO(TagEntity tagEntity) {
        return super.convert(tagEntity, TagVO.class);
    }
}
