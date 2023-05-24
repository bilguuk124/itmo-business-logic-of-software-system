package com.roclh.mainmodule.database;


import com.roclh.mainmodule.entities.Category;
import com.roclh.mainmodule.entities.StudopediaArticle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudopediaDatabase extends JpaRepository<StudopediaArticle, Long> {
    Optional<StudopediaArticle> findByIdEqualsAndApprovedTrue(Long id);
    List<StudopediaArticle> findByApprovedFalse();
    List<StudopediaArticle> findByCategoryEqualsAndApprovedTrue(@NonNull Category category, Pageable pageable);
    Optional<StudopediaArticle> findByNameLikeAndApprovedTrue(String name);
    List<StudopediaArticle> findByNameLikeIgnoreCaseAndApprovedTrue(String name, Pageable pageable);
    List<StudopediaArticle> getByApprovedTrue();
    List<StudopediaArticle> findByApprovedTrue(Pageable pageable);

    Optional<StudopediaArticle> findByIdEquals(Long articleId);
}
