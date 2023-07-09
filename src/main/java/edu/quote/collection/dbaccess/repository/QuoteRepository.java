package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.BookEntity;
import edu.quote.collection.dbaccess.entity.QuoteEntity;
import edu.quote.collection.dbaccess.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {

    Long countByUser(UserEntity userEntity);

    Page<QuoteEntity> findAllByOrderByCreationTimeDesc(Pageable pageable);

    Page<QuoteEntity> findAllByUserOrderByCreationTimeDesc(UserEntity userEntity, Pageable pageable);

    @Query("SELECT q FROM QuoteEntity q JOIN q.book b JOIN b.author a WHERE (:authorId IS NULL OR a.id = :authorId) " +
            "AND (:bookId IS NULL OR b.id = :bookId) AND (:text IS NULL OR q.text LIKE %:text%)")
    List<QuoteEntity> findAllByTextAndBookAndAuthor(@Param("authorId") Long authorId, @Param("bookId") Long bookId, @Param("text") String text);

    @Query("SELECT COUNT(q) FROM QuoteEntity q WHERE q.book = :bookEntity")
    Integer getCountByBook(@Param("bookEntity") BookEntity bookEntity);
}
