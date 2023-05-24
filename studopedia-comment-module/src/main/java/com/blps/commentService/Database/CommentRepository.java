package com.blps.commentService.Database;


import com.blps.commentService.Entity.Comment;
import com.blps.commentService.Entity.CommentKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, CommentKey> {
    long deleteByIdAndArticleIdAndAccountId(Long id, Long articleId, Long accountId);

}
