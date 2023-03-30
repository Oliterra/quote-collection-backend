package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.AuthorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorEntity, Long> {
}
