package edu.quote.collection.converter;

import edu.quote.collection.dbaccess.entity.AuthorEntity;
import edu.quote.collection.remote.vo.AuthorVO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorConverter extends BaseConverter {

    public List<AuthorVO> convertToVOList(List<AuthorEntity> authorEntities) {
        List<AuthorVO> authorVOList = new ArrayList<>();
        if (authorEntities != null) {
            authorVOList = authorEntities.stream()
                    .map(this::convertToVO)
                    .collect(Collectors.toList());
        }
        return authorVOList;
    }

    public AuthorVO convertToVO(AuthorEntity authorEntity) {
        AuthorVO authorVO = new AuthorVO();
        authorVO.setId(authorEntity.getId());
        authorVO.setName("%s %s".formatted(authorEntity.getName(), authorEntity.getSurname()));
        return authorVO;
    }
}
