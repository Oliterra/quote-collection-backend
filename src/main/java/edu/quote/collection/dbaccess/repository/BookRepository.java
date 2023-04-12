package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findAllByOrderByNameAsc();

    @Query("SELECT COUNT(b) > 0 FROM BookEntity b WHERE b.id = :bookId AND b.author.id = :authorId")
    boolean existsByIdAndAuthor(@Param("bookId") Long bookId, @Param("authorId") Long authorId);

    @Query("SELECT COUNT(b) > 0 FROM BookEntity b JOIN b.categories c WHERE b.id = :bookId AND c.id = :categoryId")
    boolean existsByIdAndCategory(@Param("bookId") Long bookId, @Param("categoryId") Long categoryId);
}
