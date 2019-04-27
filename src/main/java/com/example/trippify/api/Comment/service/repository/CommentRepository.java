package com.example.trippify.api.Comment.service.repository;

import com.example.trippify.api.Post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Post, Long> {
}
