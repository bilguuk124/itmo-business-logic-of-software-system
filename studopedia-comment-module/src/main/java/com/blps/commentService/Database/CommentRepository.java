package com.blps.commentService.Database;


import com.roclh.blps.entities.Comment;
import com.roclh.blps.entities.CommentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentKey> {
    long deleteByIdAndArticleIdAndAccountId(Long id, Long articleId, Long accountId);

}
