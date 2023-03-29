package com.roclh.blps.database;

import com.roclh.blps.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryDatabase extends JpaRepository<Category, Long> {
    Optional<Category> findByNameLikeIgnoreCase(String name);
}
