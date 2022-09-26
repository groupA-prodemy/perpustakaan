package com.example.perpustakaan.controller.userbook.converter;

import com.example.perpustakaan.model.dto.userbook.UserBookDto;
import com.example.perpustakaan.model.entity.UserBook;

public class EntityToDto {

    public UserBookDto convertEntityToDto (UserBook userBook) {
        UserBookDto userBookDto = new UserBookDto();
        userBookDto.setUserbookId(userBook.getUserBookId());
        userBookDto.setBookId(userBook.getBook().getBookId());
        userBookDto.setBookTitle(userBook.getBook().getBookTitle());
        userBookDto.setUserId(userBook.getUser().getId());
        userBookDto.setUserName(userBook.getUser().getUsername());
        userBookDto.setStartDate(userBook.getStartDate());
        userBookDto.setDueDate(userBook.getDueDate());
        userBookDto.setReturnDate(userBook.getReturnDate());

        return userBookDto;
    }
}
