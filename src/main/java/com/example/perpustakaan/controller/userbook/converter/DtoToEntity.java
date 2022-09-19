package com.example.perpustakaan.controller.userbook.converter;

import com.example.perpustakaan.model.dto.userbook.PostUserBookDto;
import com.example.perpustakaan.model.entity.UserBook;

public class DtoToEntity {

    public UserBook convertDtoToEntity (PostUserBookDto userBookDto) {
        UserBook userBook = new UserBook();
        userBook.setUserBookId(userBookDto.getUserBookId());
        userBook.setBookId(userBookDto.getBookId());
        userBook.setUserId(userBookDto.getUserId());
        userBook.setStartDate(userBookDto.getStartDate());
        userBook.setDueDate(userBookDto.getDueDate());
        userBook.setReturnDate(userBookDto.getReturnDate());

        return userBook;

    }
}
