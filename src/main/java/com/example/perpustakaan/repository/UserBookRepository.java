package com.example.perpustakaan.repository;

import com.example.perpustakaan.model.entity.UserBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserBookRepository extends JpaRepository <UserBook, Integer> {

    Optional<UserBook> findByUserBookId(Integer userBookId);

    @Query(value = "select\n" +
            "t_userbook.userbook_id, t_userbook.start_date, t_userbook.due_date, t_userbook.is_returned,\n" +
            "t_loaner.t_name, t_loaner.t_password, t_loaner.t_username,\n" +
            "t_book.book_title, kategori.nama_kategori, t_book.book_status,\n" +
            "t_book.book_year, t_author.nama_author, t_author.alamat_author, t_author.no_hp,\n" +
            "t_publisher.name_publisher, t_publisher.adress_publisher\n" +
            "from t_userbook\n" +
            "inner join t_book on t_book.book_id = t_userbook.id_book\n" +
            "inner join t_loaner on t_loaner.t_id = t_userbook.id_user\n" +
            "inner join kategori on t_book.category_id = kategori.id_kategori\n" +
            "inner join t_author on t_book.author_id = t_author.id_author\n" +
            "inner join t_publisher on t_book.publisher_id = t_publisher.id_publisher",nativeQuery = true)
    List<UserBook> getListUserBook();

}
