package com.example.perpustakaan.controller.book.converter;

import com.example.perpustakaan.model.dto.book.BookDto;
import com.example.perpustakaan.model.entity.Book;

public class EntityToDto {

    public BookDto convertEntityToDto (Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBookId(book.getBookId());
        bookDto.setBookTitle(book.getBookTitle());
        bookDto.setBookStatus(book.getBookStatus());
        bookDto.setBookYear(book.getBookYear());
        bookDto.setCategoryId(book.getCategoryId());
        bookDto.setCategoryName(book.getCategory().getCategoryName());
        bookDto.setAuthorId(book.getAuthorId());
        bookDto.setAuthorName(book.getAuthor().getAuthorName());
        bookDto.setPublisherId(book.getPublisherId());
        bookDto.setPublisherName(book.getPublisher().getPublisherName());

        return bookDto;
    }
    
}
