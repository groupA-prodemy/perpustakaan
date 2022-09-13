package com.example.perpustakaan.controller.loaner.converter;

import com.example.perpustakaan.model.dto.BookLoanerDto;
import com.example.perpustakaan.model.entity.BookLoaner;

public class EntityToDto {

    public BookLoanerDto convertEntityToDto(BookLoaner bookLoaner){
        BookLoanerDto bookLoanerDto = new BookLoanerDto();
        bookLoanerDto.setId(bookLoaner.getId());
        bookLoanerDto.setName(bookLoaner.getName());
        bookLoanerDto.setUsername(bookLoaner.getUsername());
        bookLoanerDto.setPassword(bookLoaner.getPassword());

        return bookLoanerDto;
    }
}
