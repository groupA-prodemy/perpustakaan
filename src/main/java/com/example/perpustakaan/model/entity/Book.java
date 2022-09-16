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

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "author_id", insertable = false, updatable = false)
    private Author author;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private Category category;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "publisher_id",  insertable = false, updatable = false)
    private Publisher publisher;

//    @Column(name = "publisher_name")
//    private String publisherName;
//
//    @Column(name = "author_name")
//    private String authorName;
//
//    @Column(name = "category_name")
//    private String categoryName;
//
//    public String getPublisherName() {
//        return publisherName;
//    }
//
//    public void setPublisherName(String publisherName) {
//        this.publisherName = publisherName;
//    }
//
//    public String getAuthorName() {
//        return authorName;
//    }
//
//    public void setAuthorName(String authorName) {
//        this.authorName = authorName;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getKategori() {
        return category;
    }

    public void setKategori(Category category) {
        this.category = category;
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
