package com.example.perpustakaan.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_userbook")
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserBook_Id_Seq")
    @SequenceGenerator(name="UserBook_Id_Seq", sequenceName = "UserBook_Id_Seq", allocationSize = 1)
    @Column(name = "userbook_id")
    private Integer userBookId;

    @Column(name = "id_book")
    private Integer idBook;

    @Column(name = "id_user")
    private Integer idUser;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_book", insertable = false, updatable = false)
    private Book book;

    @OneToOne (cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private User user;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "due_date")
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "is_returned")
    private Date isReturned;

    public Integer getUserBookId() {
        return userBookId;
    }

    public void setUserBookId(Integer userBookId) {
        this.userBookId = userBookId;
    }

    public Integer getIdBook() {
        return idBook;
    }

    public void setIdBook(Integer idBook) {
        this.idBook = idBook;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getIsReturned() {
        return isReturned;
    }

    public void setIsReturned(Date isReturned) {
        this.isReturned = isReturned;
    }
}
