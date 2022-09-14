package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserBookRepository extends JpaRepository <UserBook, Integer> {

    Optional<UserBook> findByUserBookId(Integer userBookId);

}
