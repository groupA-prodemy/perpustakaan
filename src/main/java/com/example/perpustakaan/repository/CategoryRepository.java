package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository <Category, Integer> {
}
