package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.UserBook;

import java.util.Optional;

public interface UserBookRepository {
    UserBook[] findAll();

    Optional<UserBook> findByUserBookId(Integer userBookId);

}
