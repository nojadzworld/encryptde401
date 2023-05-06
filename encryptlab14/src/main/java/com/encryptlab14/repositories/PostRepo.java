package com.encryptlab14.repositories;

import com.encryptlab14.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByDateDesc();

}
