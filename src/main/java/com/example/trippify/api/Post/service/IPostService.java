package com.example.trippify.api.Post.service;

import com.example.trippify.api.Post.model.Post;

import java.util.List;
import java.util.Optional;


public interface IPostService {


    Post save(Post post);

    void delete(Post post);

    Post update(Post post);

    List<Post> saveAll(List<Post> posts);

    Optional<Post> findById(long id);

}
