package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.BookLoaner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookLoanerRepository extends JpaRepository <BookLoaner, Integer> {
    Optional<BookLoaner> findByUsername(String username);

    Optional<BookLoaner> findByPassword(String password);

    Optional<BookLoaner> findByUsernameAndPassword(String username, String password);
}
