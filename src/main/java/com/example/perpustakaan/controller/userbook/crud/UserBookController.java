package com.example.perpustakaan.controller.userbook.crud;

import com.example.perpustakaan.controller.userbook.converter.DtoToEntity;
import com.example.perpustakaan.controller.userbook.converter.EntityToDto;
import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.userbook.PostUserBookDto;
import com.example.perpustakaan.model.dto.userbook.UserBookDto;
import com.example.perpustakaan.model.entity.UserBook;
import com.example.perpustakaan.repository.UserBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/userbook")
public class UserBookController {

    @Autowired
    private final UserBookRepository userBookRepository;

    public UserBookController(UserBookRepository userBookRepository) {
        this.userBookRepository = userBookRepository;
    }

    DtoToEntity dtoToEntity = new DtoToEntity();
    EntityToDto entityToDto = new EntityToDto();

    @GetMapping("/list-userbook")
    public List<UserBookDto> getListUserBook() {
        List <UserBookDto> list = new ArrayList<>();
        for (UserBook u : userBookRepository.findAll()) {
            list.add(entityToDto.convertEntityToDto(u));
        }
        return list;
    }

    @GetMapping("/{userbookId}")
    public DefaultResponse<UserBookDto> getById(@PathVariable Integer userbookId) {
        DefaultResponse<UserBookDto> defaultResponse = new DefaultResponse<>();
        Optional<UserBook> optionalBook = userBookRepository.findByUserBookId(userbookId);
        if (optionalBook.isEmpty()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("ID Yang Dimasukkan Salah");
        } else {
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Selamat Melihat Data Berikut");
            defaultResponse.setData(entityToDto.convertEntityToDto(optionalBook.get()));
        }

        return defaultResponse;
    }

    @PostMapping("/add-userbook")
    public DefaultResponse<PostUserBookDto> addUserBook (@RequestBody PostUserBookDto userBookDto) {
        UserBook userBook = dtoToEntity.convertDtoToEntity(userBookDto);
        DefaultResponse<PostUserBookDto> defaultResponse = new DefaultResponse<>();
        Optional<UserBook> optionalUserBook = userBookRepository.findByUserBookId (userBookDto.getUserBookId());
        if (optionalUserBook.isPresent()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Gagal Menambahkan Pengguna Buku");
        } else {
            userBookRepository.save(userBook);
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Pengguna Buku Berhasil Ditambahkan");
            defaultResponse.setData(userBookDto);
        }
        return defaultResponse;
    }

    @PutMapping("/update-userbook/{UserBookId}")
    public DefaultResponse updateUserBookById (@PathVariable Integer UserBookId, @RequestBody PostUserBookDto userBookDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Optional <UserBook> optionalUserBook = userBookRepository.findByUserBookId(UserBookId);
            UserBook userBook = optionalUserBook.get();
            if (optionalUserBook.isPresent()) {
                userBook.setBookId(userBookDto.getBookId());
                userBook.setUserId(userBookDto.getUserId());
                userBook.setStartDate(userBookDto.getStartDate());
                userBook.setDueDate(userBookDto.getDueDate());
                userBook.setReturnDate(userBookDto.getReturnDate());
                userBookRepository.save(userBook);
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setData(userBookDto);
                defaultResponse.setMessage("Data Berhasil Diperbarui");
            }
        } catch (Exception e) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Data Gagal Diperbarui");
        }
        return defaultResponse;
    }

    @DeleteMapping("/delete/{userBookId}")
    public DefaultResponse deleteByUserBookId (@PathVariable Integer userBookId) {
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<UserBook> optionalUserBook = userBookRepository.findByUserBookId(userBookId);
        if (optionalUserBook.isPresent()) {
            userBookRepository.delete(optionalUserBook.get());
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
