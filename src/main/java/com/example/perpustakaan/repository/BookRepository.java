package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.Book;
import com.example.perpustakaan.model.entity.Kategori;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Integer> {

    Optional<Book> findByBookId(Integer bookId);

    @Override
    Page<Book> findAll(Pageable pageable);
}
