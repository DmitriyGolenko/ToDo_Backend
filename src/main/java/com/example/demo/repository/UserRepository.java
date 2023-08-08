package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    User save(User task);
    List<User> getAllBy();
    User getUserById(long id);
    User deleteById(long id);

    Optional<User> findByLogin(String login);
}
