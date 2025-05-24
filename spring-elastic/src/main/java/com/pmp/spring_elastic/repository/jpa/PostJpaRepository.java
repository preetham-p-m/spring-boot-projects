package com.pmp.spring_elastic.repository.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pmp.spring_elastic.model.Post;

@Repository
public interface PostJpaRepository extends JpaRepository<Post, UUID> {
}
