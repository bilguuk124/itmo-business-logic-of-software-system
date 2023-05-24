package com.roclh.commentmodule.Database;


import com.roclh.mainmodule.entities.Comment;
import com.roclh.mainmodule.entities.CommentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentKey> {
    long deleteByIdAndArticleIdAndAccountId(Long id, Long articleId, Long accountId);

}
