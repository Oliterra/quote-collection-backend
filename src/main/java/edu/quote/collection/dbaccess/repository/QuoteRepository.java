package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
}
