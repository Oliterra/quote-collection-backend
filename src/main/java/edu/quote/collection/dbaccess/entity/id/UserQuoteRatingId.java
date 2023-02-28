package edu.quote.collection.dbaccess.entity.id;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserQuoteRatingId implements Serializable {

    private Long userId;
    private Long quoteId;
}
