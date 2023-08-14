package com.app.TODO_backend.repository;

import com.app.TODO_backend.entity.Task;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task,Long> {

    Task save(Task task);
    List<Task> getAllBy();
    Optional<Task> getTaskById(long id);

    Task deleteById(long id);

    @Transactional
    @Modifying
    @Query("UPDATE Task t set t.date = :date, t.done = :done, t.description = :description where t.id = :id")
    void updateTaskById(long id, @Param("date") LocalDate date, @Param("done") boolean done, @Param("description") String description );

    @Modifying
    @Transactional
    @Query("update Task t set t.done = :done WHERE t.id =:id")
    void markTask(long id, boolean done);
}