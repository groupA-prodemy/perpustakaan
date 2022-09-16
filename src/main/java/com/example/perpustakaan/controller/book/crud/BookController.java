package com.example.perpustakaan.controller.book.crud;

import com.example.perpustakaan.model.dto.PostBookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.perpustakaan.controller.book.converter.DtoToEntity;
import com.example.perpustakaan.controller.book.converter.EntityToDto;
import com.example.perpustakaan.model.dto.BookDto;
import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.entity.Book;
import com.example.perpustakaan.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
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
    public DefaultResponse<PostBookDto> addBook (@RequestBody PostBookDto bookDto) {
        Book book = dtoToEntity.convertDtoToEntity(bookDto);
        DefaultResponse<PostBookDto> defaultResponse = new DefaultResponse<>();
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

    @PutMapping("/update/{BookId}")
    public DefaultResponse updateBookById (@PathVariable Integer BookId, @RequestBody PostBookDto bookDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
           Optional<Book> optionalBook = bookRepository.findByBookId(BookId);
           Book book = optionalBook.get();
           if (optionalBook.isPresent()) {
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

    @DeleteMapping("/delete/{bookId}")
    public DefaultResponse deleteByBookId (@PathVariable Integer bookId) {
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<Book> optionalBook = bookRepository.findByBookId(bookId);
        if (optionalBook.isPresent()) {
            bookRepository.delete(optionalBook.get());
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Berhasil Dihapus");
            defaultResponse.setData("Clear");
        } else {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Gagal Dihapus");
            defaultResponse.setData("Data Yang Anda Pilih Tidak Ada!");
        }
        return defaultResponse;
    }
}
