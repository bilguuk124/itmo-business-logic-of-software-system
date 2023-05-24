package com.blps.CommentService.Database;

import com.blps.CommentService.Entity.StudopediaArticle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<StudopediaArticle, Long> {
    @Override
    Optional<StudopediaArticle> findById(Long aLong);
}
