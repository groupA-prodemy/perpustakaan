package com.example.perpustakaan.controller.userbook.converter;

import com.example.perpustakaan.model.dto.UserBookDto;
import com.example.perpustakaan.model.entity.UserBook;

public class EntityToDto {

    public UserBookDto convertEntityToDto (UserBook userBook) {
        UserBookDto userBookDto = new UserBookDto();
        userBookDto.setUserBookId(userBook.getUserBookId());
        userBookDto.setIdBook(userBook.getIdBook());
        userBookDto.setIdUser(userBook.getIdUser());
        userBookDto.setStartDate(userBook.getStartDate());
        userBookDto.setDueDate(userBook.getDueDate());
        userBookDto.setIsReturned(userBook.getIsReturned());

        return userBookDto;
    }
}
