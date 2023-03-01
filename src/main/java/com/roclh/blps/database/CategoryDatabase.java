package com.roclh.blps.database;

import com.roclh.blps.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDatabase extends JpaRepository<Category, Long>, PagingAndSortingRepository<Category, Long> {
}
