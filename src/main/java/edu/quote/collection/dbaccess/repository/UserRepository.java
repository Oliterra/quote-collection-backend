package edu.quote.collection.dbaccess.repository;

import edu.quote.collection.dbaccess.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
