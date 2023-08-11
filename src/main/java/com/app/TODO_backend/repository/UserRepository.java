package com.app.TODO_backend.repository;

import com.app.TODO_backend.entity.User;
import jakarta.persistence.Table;
import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Table(name = "users")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User save(User task);
    List<User> getAllBy();
    User getUserById(long id);
    User deleteById(long id);
    Optional<User> getUserByLogin(String login);
}
