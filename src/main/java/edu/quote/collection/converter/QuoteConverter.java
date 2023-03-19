package edu.quote.collection.converter;

import edu.quote.collection.dbaccess.entity.*;
import edu.quote.collection.remote.vo.QuoteMainInfoVO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QuoteConverter extends BaseConverter {
    private final TagConverter tagConverter;

    public List<QuoteMainInfoVO> convertToMainInfoVOList(List<QuoteEntity> quotes) {
        List<QuoteMainInfoVO> mainInfoVOList = new ArrayList<>();
        if (quotes != null) {
            mainInfoVOList = quotes.stream()
                .map(this::convertToMainInfoVO)
                .collect(Collectors.toList());
        }
        return mainInfoVOList;
    }

    public QuoteMainInfoVO convertToMainInfoVO(QuoteEntity quoteEntity) {
        QuoteMainInfoVO mainInfoVO = super.convert(quoteEntity, QuoteMainInfoVO.class);
        if (quoteEntity != null) {
            UserEntity user = quoteEntity.getUser();
            if (user != null) {
                mainInfoVO.setUserId(user.getId());
                mainInfoVO.setUsername(user.getUsername());
            }
            BookEntity book = quoteEntity.getBook();
            if (book != null) {
                mainInfoVO.setBookName(book.getName());
                mainInfoVO.setAuthorName(book.getAuthor().getName() + " " + book.getAuthor().getSurname());
            }
            List<GroupEntity> groups = quoteEntity.getGroups();
            if (groups != null) {
                List<String> groupNames = groups.stream().map(GroupEntity::getName).toList();
                mainInfoVO.setGroupNames(groupNames);
            }
            List<TagEntity> tags = quoteEntity.getTags();
            if (tags != null) {
                mainInfoVO.setTags(tagConverter.convertToVOList(tags));
            }
        }
        return mainInfoVO;
    }
}
