package com.example.perpustakaan.controller.loaner.converter;

import com.example.SistemPerpustakaan.model.dto.BookLoanerDto;
import com.example.SistemPerpustakaan.model.entity.BookLoaner;

public class DtoToEntity {
    public BookLoaner convertDtoToEntity(BookLoanerDto bookLoanerDto){
        BookLoaner bookLoaner = new BookLoaner();
        bookLoaner.setName(bookLoanerDto.getName());
        bookLoaner.setUsername(bookLoanerDto.getUsername());
        bookLoaner.setPassword(bookLoanerDto.getPassword());

        return bookLoaner;
    }
}