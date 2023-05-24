package com.roclh.commentmodule.Database;

import com.roclh.mainmodule.entities.StudopediaArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<StudopediaArticle, Long> {
    @Override
    Optional<StudopediaArticle> findById(Long aLong);
}
