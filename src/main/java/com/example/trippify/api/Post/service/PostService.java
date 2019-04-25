package com.example.trippify.api.Post.service;


import com.example.trippify.api.Post.model.Post;
import com.example.trippify.api.Post.service.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PostService implements IPostService {


    @Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }


    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public Optional<Post> findById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post update(Post post) {
        return postRepository.save(post);

    }

    @Override
    public List<Post> saveAll(List<Post> posts) {
        return postRepository.saveAll(posts);
    }
}
