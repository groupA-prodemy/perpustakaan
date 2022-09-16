package com.example.perpustakaan.controller.book.converter;

import com.example.perpustakaan.model.dto.BookDto;
import com.example.perpustakaan.model.dto.PostBookDto;
import com.example.perpustakaan.model.entity.Author;
import com.example.perpustakaan.model.entity.Book;
import com.example.perpustakaan.model.entity.Kategori;
import com.example.perpustakaan.model.entity.Publisher;
import com.example.perpustakaan.repository.AuthorRepository;
import com.example.perpustakaan.repository.KategoriRepository;
import com.example.perpustakaan.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DtoToEntity {
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private KategoriRepository kategoriRepository;
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
