package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.Book;
import com.example.perpustakaan.model.entity.Kategori;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository <Book, Integer> {

    Optional<Book> findByBookId(Integer bookId);

    @Override
    Page<Book> findAll(Pageable pageable);

    @Query(value = "select\n" +
            "t_book.book_id, kategori.nama_kategori, t_book.book_title, t_book.book_status,\n" +
            "t_book.book_year, t_author.nama_author, t_author.alamat_author, t_author.no_hp,\n" +
            "t_publisher.name_publisher, t_publisher.adress_publisher\n" +
            "from t_book\n" +
            "inner join kategori on t_book.category_id = kategori.id_kategori\n" +
            "inner join t_author on t_book.author_id = t_author.id_author\n" +
            "inner join t_publisher on t_book.publisher_id = t_publisher.id_publisher", nativeQuery = true)
    List<Book> getListBook();

}
