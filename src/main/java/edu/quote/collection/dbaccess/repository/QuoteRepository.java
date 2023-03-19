package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.QuoteEntity;
import edu.quote.collection.dbaccess.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

    Long countByUser(UserEntity userEntity);

    Page<QuoteEntity> findAllByOrderByCreationTimeAsc(Pageable pageable);

    Page<QuoteEntity> findAllByUserOrderByCreationTimeAsc(UserEntity userEntity, Pageable pageable);
}
