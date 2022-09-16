package com.example.perpustakaan.controller.userbook.converter;

import com.example.perpustakaan.model.dto.PostUserBookDto;
import com.example.perpustakaan.model.entity.UserBook;

public class DtoToEntity {

    public UserBook convertDtoToEntity (PostUserBookDto userBookDto) {
        UserBook userBook = new UserBook();
        userBook.setUserBookId(userBookDto.getUserBookId());
        userBook.setIdBook(userBookDto.getIdBook());
        userBook.setIdUser(userBookDto.getIdUser());
        userBook.setStartDate(userBookDto.getStartDate());
        userBook.setDueDate(userBookDto.getDueDate());
        userBook.setIsReturned(userBookDto.getIsReturned());

        return userBook;

    }
}
