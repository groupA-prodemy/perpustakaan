package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

Optional<Author> findByAuthorId (Integer authorId);



}
