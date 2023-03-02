package edu.quote.collection.dbaccess.entity;

import edu.quote.collection.dbaccess.entity.id.UserQuoteRatingId;
import jakarta.persistence.*;
import lombok.Data;

@Data
@IdClass(UserQuoteRatingId.class)
@Table(name = "user_quote_rating", schema = "public")
@Entity
public class UserQuoteRatingEntity {

    private Long userId;
    private Long quoteId;
    private Byte rating;

    @Id
    @Column(name = "user_id")
    public Long getUserId() {
        return userId;
    }

    @Id
    @Column(name = "quote_id")
    public Long getQuoteId() {
        return quoteId;
    }
}
