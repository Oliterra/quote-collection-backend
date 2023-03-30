package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
