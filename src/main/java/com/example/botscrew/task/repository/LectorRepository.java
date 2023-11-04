package com.example.botscrew.task.repository;

import com.example.botscrew.task.model.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {

    @Query(value = "SELECT name FROM lector WHERE name LIKE CONCAT('%',:namePart,'%')", nativeQuery = true)
    List<String> findLectorsByNameContaining(@Param("namePart") String namePart);
}
