package com.roclh.blps.database;

import com.roclh.blps.entities.CommentKey;
import com.roclh.blps.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentDatabase extends JpaRepository<Comment, CommentKey> {
    Optional<Comment> findByArticle_IdEqualsAndAccountIdEqualsAndCommentLike(Long id, Long accountId, String comment);

}
