package edu.quote.collection.converter;

import edu.quote.collection.dbaccess.entity.AuthorEntity;
import edu.quote.collection.remote.vo.AuthorVO;
import org.springframework.stereotype.Component;

@Component
public class AuthorConverter extends BaseConverter {

    public AuthorVO convertToVO(AuthorEntity authorEntity) {
        return super.convert(authorEntity, AuthorVO.class);
    }
}
