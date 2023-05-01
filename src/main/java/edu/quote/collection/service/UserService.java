package edu.quote.collection.service;

import edu.quote.collection.dbaccess.entity.UserQuoteRatingEntity;
import edu.quote.collection.dbaccess.entity.id.UserQuoteRatingId;
import edu.quote.collection.dbaccess.repository.UserQuoteRatingRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Transactional
@Service
@AllArgsConstructor
public class UserService {

    private final UserQuoteRatingRepository userQuoteRatingRepository;

    public Byte getRatingByUserId(Long userId, Long quoteId) {
        UserQuoteRatingId userRatingId = new UserQuoteRatingId(userId, quoteId);
        Optional<UserQuoteRatingEntity> userRating = userQuoteRatingRepository.findById(userRatingId);
        return userRating.isPresent() ? userRating.get().getRating() : 0;
    }
}
