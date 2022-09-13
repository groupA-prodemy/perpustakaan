package com.example.perpustakaan.controller.book.converter;

import com.example.perpustakaan.model.dto.BookDto;
import com.example.perpustakaan.model.entity.Book;

public class DtoToEntity {
    public Book convertDtoToEntity (BookDto bookDto) {
        Book book = new Book();
        book.setBookId(bookDto.getBookId());
        book.setBookTitle(bookDto.getBookTitle());
        book.setBookStatus(bookDto.getBookStatus());
        book.setBookYear(bookDto.getBookYear());
        book.setAuthorId(bookDto.getAuthorId());
        book.setCategoryId(bookDto.getCategoryId());
        book.setPublisherId(bookDto.getPublisherId());

        return book;
    }
}
