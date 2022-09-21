package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.Publisher;
import com.example.perpustakaan.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Optional<Publisher> findByPublisherName(String publisherName);
}
