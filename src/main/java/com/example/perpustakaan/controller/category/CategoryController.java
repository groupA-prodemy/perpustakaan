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

@CrossOrigin(origins="http://localhost:8080")
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @PostMapping("/add")
    public DefaultResponse<CategoryDto> saveCategory(@RequestBody CategoryDto categoryDto){
        Category category = convertDtoToEntity(categoryDto);
        DefaultResponse<CategoryDto> response = new DefaultResponse();
        Optional<Category> optional = categoryRepository.findByCategoryName(categoryDto.getCategoryName());
        if(optional.isPresent()){
            response.setStatus(Boolean.FALSE);
            response.setMessage("Error, Data Sudah Tersedia");
        } else {
            categoryRepository.save(category);
            response.setStatus(Boolean.TRUE);
            response.setMessage("Berhasil Simpan Data");
        }

        return response;
    }
    public Category convertDtoToEntity(CategoryDto dto){
        Category category = new Category();
        category.setCategoryName(dto.getCategoryName());

        return category;
    }

    @GetMapping("/list")
    public List<CategoryDto> getListKategori(){
        List<CategoryDto> list = new ArrayList();
        for(Category category :categoryRepository.findAll()){
            list.add(convertEntityToDto(category));
        }
        return list;
    }

    public CategoryDto convertEntityToDto(Category entity){
        CategoryDto dto = new CategoryDto();
        dto.setCategoryId(entity.getCategoryID());
        dto.setCategoryName(entity.getCategoryName());

        return dto;
    }

    @DeleteMapping("/delete/{categoryId}")
    public DefaultResponse deleteById(@PathVariable("categoryId") Integer idKategori) {
        DefaultResponse df = new DefaultResponse();
        Optional<Category> optionalKategori =categoryRepository.findById(idKategori);
        if (optionalKategori.isPresent()){
            categoryRepository.delete(optionalKategori.get());
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data Berhasil Dihapus");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Tidak Ditemukan");
        }
        return df;
    }

    @PutMapping("/update/{categoryId}")
    public DefaultResponse update(@PathVariable("categoryId") Integer idKategori, @RequestBody CategoryDto categoryDto) {
        DefaultResponse df = new DefaultResponse();
        Optional<Category> optionalKategori = categoryRepository.findById(idKategori);
        Category category = optionalKategori.get();
        if (optionalKategori.isPresent()) {
            category.setCategoryID(categoryDto.getCategoryId());
            category.setCategoryName(categoryDto.getCategoryName());
            categoryRepository.save(category);
            df.setStatus(Boolean.TRUE);
            df.setMessage("Data berhasil diperbarui");
        } else {
            df.setStatus(Boolean.FALSE);
            df.setMessage("Data Sudah Terdaftar");
        }
        return df;
    }


}

