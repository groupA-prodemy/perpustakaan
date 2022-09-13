package com.example.perpustakaan.controller.book.converter;

import com.example.perpustakaan.model.dto.BookDto;
import com.example.perpustakaan.model.entity.Book;

public class EntityToDto {

    public BookDto convertEntityToDto (Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getBookId());
        bookDto.setBookTitle(book.getBookTitle());
        bookDto.setBookStatus(book.getBookStatus());
        bookDto.setBookYear(book.getBookYear());
        bookDto.setAuthorId(book.getAuthorId());
        bookDto.setCategoryId(book.getCategoryId());
        bookDto.setPublisherId(book.getPublisherId());

        return bookDto;
    }
    
}
