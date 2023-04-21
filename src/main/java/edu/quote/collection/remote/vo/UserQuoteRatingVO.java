package edu.quote.collection.remote.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserQuoteRatingVO {

    private Long userId;
    private Long quoteId;
    private Byte rating;
}
