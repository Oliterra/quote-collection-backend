package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.UserQuoteRatingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuoteRatingRepository extends JpaRepository<UserQuoteRatingEntity, Long> {
}
