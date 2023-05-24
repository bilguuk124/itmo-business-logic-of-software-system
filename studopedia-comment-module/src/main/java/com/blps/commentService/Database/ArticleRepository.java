package com.blps.commentService.Database;

import com.roclh.blps.entities.StudopediaArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<StudopediaArticle, Long> {
    @Override
    Optional<StudopediaArticle> findById(Long aLong);
}
