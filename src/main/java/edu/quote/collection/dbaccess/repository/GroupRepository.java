package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    List<GroupEntity> findAllByOrderByNameAsc();

    List<GroupEntity> findAllByUserIdOrderByNameAsc(Long userId);

}
