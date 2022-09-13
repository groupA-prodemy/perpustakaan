package com.example.perpustakaan.controller.loaner.crud;

import com.example.SistemPerpustakaan.controller.loaner.converter.DtoToEntity;
import com.example.SistemPerpustakaan.controller.loaner.converter.EntityToDto;
import com.example.SistemPerpustakaan.model.dto.BookLoanerDto;
import com.example.SistemPerpustakaan.model.dto.DefaultResponse;
import com.example.SistemPerpustakaan.model.entity.BookLoaner;
import com.example.SistemPerpustakaan.repository.BookLoanerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/loaner")
public class LoanerController {
    private final BookLoanerRepository bookLoanerRepository;

    public LoanerController(BookLoanerRepository bookLoanerRepository){
        this.bookLoanerRepository = bookLoanerRepository;
    }

    DtoToEntity dtoToEntity = new DtoToEntity();
    EntityToDto entityToDto = new EntityToDto();

    @GetMapping("/listloaner")
    public List<BookLoanerDto> getListLoaner(){
        List<BookLoanerDto> list = new ArrayList<>();
        for(BookLoaner i: bookLoanerRepository.findAll()){
            list.add(entityToDto.convertEntityToDto(i));
        }
        return list;
    }

    @PostMapping("/savedata")
    public DefaultResponse<BookLoanerDto> saveAdmin(@RequestBody BookLoanerDto bookLoanerDto){
        BookLoaner bookLoaner= dtoToEntity.convertDtoToEntity(bookLoanerDto);
        DefaultResponse<BookLoanerDto> defaultResponse = new DefaultResponse<>();
        Optional<BookLoaner> optionalBookLoaner = bookLoanerRepository.findByUsernameAndPassword(bookLoanerDto.getUsername(), bookLoanerDto.getPassword());
        if(optionalBookLoaner.isPresent()){
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to save data, data was exists");
        }else{
            bookLoanerRepository.save(bookLoaner);
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setData(bookLoanerDto);
            defaultResponse.setMessage("Succeeded to save data");
        }
        return defaultResponse;
    }

    @DeleteMapping("/delete/{id}")
    public DefaultResponse deleteById(@PathVariable ("id") Integer id){
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<BookLoaner> optionalBookLoaner = bookLoanerRepository.findById(id);
        if(optionalBookLoaner.isPresent()){
            bookLoanerRepository.delete(optionalBookLoaner.get());
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Succeeded delete data");
        }else{
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to delete data, data was not found");
        }
        return defaultResponse;
    }

    @PutMapping("/update/{id}")
    public DefaultResponse updateById (@PathVariable ("id") Integer id, @RequestBody BookLoanerDto bookLoanerDto){
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Optional<BookLoaner> optionalBookLoaner = bookLoanerRepository.findById(id);
            BookLoaner bookLoaner = optionalBookLoaner.get();
            if (optionalBookLoaner.isPresent()){
                bookLoaner.setUsername(bookLoanerDto.getUsername());
                bookLoaner.setPassword(bookLoanerDto.getPassword());
                bookLoanerRepository.save(bookLoaner);
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setData(bookLoanerDto);
                defaultResponse.setMessage("Succeeded update data");
            }
        }catch (Exception e){
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to update data, Id was not found");
        }
        return defaultResponse;
    }

}
