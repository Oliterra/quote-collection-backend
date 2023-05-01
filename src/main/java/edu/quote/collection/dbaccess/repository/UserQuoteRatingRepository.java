package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.UserQuoteRatingEntity;
import edu.quote.collection.dbaccess.entity.id.UserQuoteRatingId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserQuoteRatingRepository extends JpaRepository<UserQuoteRatingEntity, UserQuoteRatingId> {
    List<UserQuoteRatingEntity> findAllByQuoteId(Long userId);
}
