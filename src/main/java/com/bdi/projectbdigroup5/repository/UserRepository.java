package com.bdi.projectbdigroup5.repository;

import com.bdi.projectbdigroup5.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<User> findOneByUsername(String username);
}
