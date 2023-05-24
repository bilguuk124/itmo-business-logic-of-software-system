package com.roclh.commentmodule.repositories;


import com.roclh.mainmodule.entities.Comment;
import com.roclh.mainmodule.entities.CommentKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentKey> {
    long deleteByIdAndAccountIdAndArticleId(Long id, Long accountId, Long articleId);


}
