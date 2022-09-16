package com.example.perpustakaan.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class PostUserBookDto {
    private Integer userBookId;
    private Integer idBook;
    private Integer idUser;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd"/* HH:mm:ss*/)
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd"/* HH:mm:ss*/)
    private Date dueDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd"/* HH:mm:ss*/)
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
