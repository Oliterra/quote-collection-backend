package edu.quote.collection.converter;

import edu.quote.collection.dbaccess.entity.UserQuoteRatingEntity;
import edu.quote.collection.remote.vo.UserQuoteRatingVO;
import org.springframework.stereotype.Component;

@Component
public class UserQuoteRatingConverter extends BaseConverter {

    public UserQuoteRatingEntity convertToEntity(UserQuoteRatingVO userQuoteRatingVO) {
        return super.convert(userQuoteRatingVO, UserQuoteRatingEntity.class);
    }
}
