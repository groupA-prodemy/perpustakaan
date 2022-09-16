package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.Kategori;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategoriRepository extends JpaRepository <Kategori, Integer> {
}
