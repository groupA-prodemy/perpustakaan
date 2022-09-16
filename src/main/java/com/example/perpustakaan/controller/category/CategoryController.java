package com.example.perpustakaan.controller.category;

import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.CategoryDto;
import com.example.perpustakaan.model.entity.Category;
import com.example.perpustakaan.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/kategori")
public class CategoryController {

    @Autowired
    private CategoryRepository kategoriRepositori;

    @PostMapping("/save")
    public DefaultResponse<CategoryDto> savekategori(@RequestBody CategoryDto categoryDto){
        Category category = convertDtoToEntity(categoryDto);
        DefaultResponse<CategoryDto> response = new DefaultResponse();
        Optional<Category> optional = kategoriRepositori.findById(categoryDto.getIdKategori());
        if(optional.isPresent()){
            response.setStatus(Boolean.FALSE);
            response.setMessage("Error, Data Sudah Tersedia");
        } else {
            kategoriRepositori.save(category);
            response.setStatus(Boolean.TRUE);
            response.setMessage("Berhasil Simpan Data");
        }

        return response;
    }
    public Category convertDtoToEntity(CategoryDto dto){
        Category category = new Category();
        category.setIdKategori(dto.getIdKategori());
        category.setNamaKategori(dto.getNamaKategori());

        return category;
    }

    @GetMapping("/listkategori")
    public List<CategoryDto> getListKategori(){
        List<CategoryDto> list = new ArrayList();
        for(Category category :kategoriRepositori.findAll()){
            list.add(convertEntityToDto(category));
        }
        return list;
    }

    public CategoryDto convertEntityToDto(Category entity){
        CategoryDto dto = new CategoryDto();
        dto.setIdKategori(entity.getIdKategori());
        dto.setNamaKategori(entity.getNamaKategori());

        return dto;
    }

    @DeleteMapping("/delete/{idKategori}")
    public DefaultResponse deleteById(@PathVariable("idKategori") Integer idKategori) {
        DefaultResponse df = new DefaultResponse();
        Optional<Category> optionalKategori =kategoriRepositori.findById(idKategori);
        if (optionalKategori.isPresent()){
            kategoriRepositori.delete(optionalKategori.get());
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Tidak Ditemukan");
        }
        return df;
    }

    @PutMapping("/update/{idKategori}")
    public DefaultResponse update(@PathVariable("idKategori") Integer idKategori, @RequestBody CategoryDto categoryDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Category> optionalKategori = kategoriRepositori.findById(idKategori);
        Category category = optionalKategori.get();
        if (optionalKategori.isPresent()) {
            category.setIdKategori(categoryDto.getIdKategori());
            category.setNamaKategori(categoryDto.getNamaKategori());
            kategoriRepositori.save(category);
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data berhasil diperbarui");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Sudah Terdaftar");
        }
        return df;
    }


}

