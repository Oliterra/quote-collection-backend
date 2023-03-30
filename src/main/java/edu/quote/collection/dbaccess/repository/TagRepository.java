package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    List<TagEntity> findAllByOrderByNameAsc();
}
