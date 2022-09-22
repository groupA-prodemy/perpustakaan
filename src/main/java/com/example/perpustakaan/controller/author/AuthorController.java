package com.example.perpustakaan.controller.author;

import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.entity.Author;
import com.example.perpustakaan.model.dto.author.AuthorDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.perpustakaan.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping("/save")
    public DefaultResponse saveauthor(@RequestBody AuthorDto authorDto){
        Author author = convertDtoToEntity(authorDto);
        DefaultResponse response = new DefaultResponse();
        Optional<Author> optional = authorRepository.findByAuthorId(authorDto.getAuthorId());
        if(optional.isPresent()){
            response.setStatus(Boolean.FALSE);
            response.setMessage("Error, Data Sudah Tersedia");
        } else {
            authorRepository.save(author);
            response.setStatus(Boolean.TRUE);
            response.setData(authorDto);
            response.setMessage("Berhasil Simpan Data");
        }

        return response;
    }

    @GetMapping("/all") //menampilkan all author
    public List<AuthorDto> getListAuthor() {
        List<AuthorDto> list = new ArrayList<>();
        for (Author a : authorRepository.findAll()) {
            list.add(convertEntitytoDto(a));
        }
        return list;
    }

    @GetMapping("/{authorId}") // buat filter sesuai tipe produk tapi cuma satu
    public List<AuthorDto> getById(@PathVariable Integer authorId) {
        List<AuthorDto> list = new ArrayList<>();
        Optional<Author> productOptional = authorRepository.findByAuthorId(authorId);
        list.add(convertEntitytoDto(productOptional.get()));

        return list;
    }

    @DeleteMapping("/delete/{authorid}")
    public DefaultResponse deleteByAuthorId (@PathVariable Integer authorid){
        DefaultResponse df = new DefaultResponse();
        Optional<Author> optionalAuthor = authorRepository.findByAuthorId(authorid);
        if (optionalAuthor.isPresent()){
            authorRepository.delete(optionalAuthor.get());
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Tidak Ditemukan");
        }
        return df;

        }
    @PutMapping("/update/{id}")
    public DefaultResponse updateById (@PathVariable Integer id, @RequestBody AuthorDto authorDto){
        DefaultResponse defaultResponse = new DefaultResponse();
        try{
            Optional<Author> optionalAuthor = authorRepository.findByAuthorId(id);
            Author author = optionalAuthor.get();
            if (optionalAuthor.isPresent()){
                author.setAuthorName(authorDto.getAuthorName());
                author.setAuthorAddress(authorDto.getAuthorAddress());
                author.setNoHp(author.getNoHp());
                authorRepository.save(author);
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setData(authorDto);
                defaultResponse.setMessage("Succeeded update data");
            }
        }catch(Exception e){
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to update data, Id was not found");
        }
        return defaultResponse;
    }




    private AuthorDto convertEntitytoDto(Author entity) {

        AuthorDto dto = new AuthorDto();
        dto.setAuthorId(entity.getAuthorId());
        dto.setAuthorName(entity.getAuthorName());
        dto.setAuthorAddress(entity.getAuthorAddress());
        dto.setNoHp(entity.getNoHp());

        return dto;
    }

    private Author convertDtoToEntity(AuthorDto authorDto) {

        Author author = new Author();
        author.setAuthorId(authorDto.getAuthorId());
        author.setAuthorName(authorDto.getAuthorName());
        author.setAuthorAddress(authorDto.getAuthorAddress());
        author.setNoHp(authorDto.getNoHp());

        return author;
    }


}
