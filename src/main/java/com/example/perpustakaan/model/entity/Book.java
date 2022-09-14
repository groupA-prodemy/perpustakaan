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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id", referencedColumnName = "id_author")
    private Author author;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", referencedColumnName = "id_kategori")
    private Kategori kategori;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "publisher_id", referencedColumnName = "id_publisher")
    private Publisher publisher;

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

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

//    public Integer getAuthorId() {
//        return authorId;
//    }
//
//    public void setAuthorId(Integer authorId) {
//        this.authorId = authorId;
//    }
//
//    public Integer getCategoryId() {
//        return categoryId;
//    }
//
//    public void setCategoryId(Integer categoryId) {
//        this.categoryId = categoryId;
//    }
//
//    public Integer getPublisherId() {
//        return publisherId;
//    }
//
//    public void setPublisherId(Integer publisherId) {
//        this.publisherId = publisherId;
//    }
}
