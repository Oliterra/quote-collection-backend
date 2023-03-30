package edu.quote.collection.converter;

import edu.quote.collection.dbaccess.entity.GroupEntity;
import edu.quote.collection.remote.vo.GroupVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GroupConverter extends BaseConverter {

    public List<GroupVO> convertToVOList(List<GroupEntity> groupEntities) {
        List<GroupVO> groupVOList = new ArrayList<>();
        if (groupEntities != null) {
            groupVOList = groupEntities.stream()
                    .map(this::convertToVO)
                    .collect(Collectors.toList());
        }
        return groupVOList;
    }

    public GroupVO convertToVO(GroupEntity groupEntity) {
        return super.convert(groupEntity, GroupVO.class);
    }
}
