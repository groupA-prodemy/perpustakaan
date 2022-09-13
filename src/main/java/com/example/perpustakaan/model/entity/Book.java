package com.example.perpustakaan.model.entity;


import javax.persistence.*;

@Entity
@Table(name = "t_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Book_Id_Seq")
    @SequenceGenerator(name="Book_Id_Seq", sequenceName = "Book_Id_Seq", allocationSize = 1)
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "book_title")
    private String bookTitle;
    @Column(name = "book_status")
    private String bookStatus;
    @Column(name = "book_year")
    private Integer bookYear;
    @Column(name = "author_id")
    private Integer authorId;
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "publisher_id")
    private Integer publisherId;


    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Integer getBookYear() {
        return bookYear;
    }

    public void setBookYear(Integer bookYear) {
        this.bookYear = bookYear;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }
}
