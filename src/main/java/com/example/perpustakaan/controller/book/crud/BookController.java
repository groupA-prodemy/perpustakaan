package com.example.perpustakaan.controller.book.crud;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.perpustakaan.controller.book.converter.DtoToEntity;
import com.example.perpustakaan.controller.book.converter.EntityToDto;
import com.example.perpustakaan.model.dto.BookDto;
import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.entity.Book;
import com.example.perpustakaan.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    
    private final BookRepository bookRepository;

    public BookController (BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    DtoToEntity dtoToEntity = new DtoToEntity();
    EntityToDto entityToDto = new EntityToDto();

    @GetMapping("/books")
    public List<BookDto> getListBook() {
        List<BookDto> list = new ArrayList<>();
       for (Book p : bookRepository.findAll()) {
           list.add(entityToDto.convertEntityToDto(p));
       }
        return list;
    }

    @PostMapping("/add-book")
    public DefaultResponse<BookDto> addBook (@RequestBody BookDto bookDto) {
        Book book = dtoToEntity.convertDtoToEntity(bookDto);
        DefaultResponse<BookDto> defaultResponse = new DefaultResponse<>();
        Optional<Book> optionalBook = bookRepository.findByBookId(bookDto.getBookId());
        if (optionalBook.isPresent()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Gagal Menyimpan Data Buku");
        } else {
            bookRepository.save(book);
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Data Buku Berhasil Disimpan");
            defaultResponse.setData(bookDto);
        }
        return defaultResponse;
    }

    @PutMapping("/update-books-detail/{id}")
    public DefaultResponse updateBookById (@PathVariable Integer BookId, @RequestBody BookDto bookDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
           Optional<Book> optionalBook = bookRepository.findByBookId(BookId);
           Book book = optionalBook.get();
           if (optionalBook.isPresent()) {
               book.setBookId(bookDto.getBookId());
               book.setBookTitle(bookDto.getBookTitle());
               book.setBookStatus(bookDto.getBookStatus());
               book.setBookYear(bookDto.getBookYear());
               book.setAuthorId(bookDto.getAuthorId());
               book.setCategoryId(bookDto.getCategoryId());
               book.setPublisherId(bookDto.getPublisherId());
               bookRepository.save(book);
               defaultResponse.setStatus(Boolean.TRUE);
               defaultResponse.setData(bookDto);
               defaultResponse.setMessage("Data Buku Berhasil Diperbarui");
           }

        } catch (Exception e) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Data Gagal Diperbarui");
        }
        return defaultResponse;
    }
}
