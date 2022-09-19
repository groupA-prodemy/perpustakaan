package com.example.perpustakaan.controller.book.converter;

import com.example.perpustakaan.model.dto.book.PostBookDto;
import com.example.perpustakaan.model.entity.Book;
import com.example.perpustakaan.repository.AuthorRepository;
import com.example.perpustakaan.repository.CategoryRepository;
import com.example.perpustakaan.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoToEntity {
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public Book convertDtoToEntity (PostBookDto bookDto) {
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
