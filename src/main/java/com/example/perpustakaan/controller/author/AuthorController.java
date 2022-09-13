package com.example.perpustakaan.controller.author;

import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.entity.Author;
import com.example.perpustakaan.model.dto.AuthorDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.perpustakaan.repository.AuthorRepository;

import java.util.Optional;

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
//            addressRepository.save(address);
            response.setStatus(Boolean.TRUE);
            response.setMessage("Berhasil Simpan Data");
        }

        return response;
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
