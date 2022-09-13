package com.example.perpustakaan.controller.admin.crud;

import com.example.SistemPerpustakaan.controller.admin.converter.DtoToEntity;
import com.example.SistemPerpustakaan.controller.admin.converter.EntityToDto;
import com.example.SistemPerpustakaan.model.dto.AdminDto;
import com.example.SistemPerpustakaan.model.dto.DefaultResponse;
import com.example.SistemPerpustakaan.model.entity.Admin;
import com.example.SistemPerpustakaan.repository.AdminRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminRepository adminRepository;

    public AdminController(AdminRepository adminRepository){
        this.adminRepository = adminRepository;
    }

    DtoToEntity dtoToEntity = new DtoToEntity();
    EntityToDto entityToDto = new EntityToDto();

    @GetMapping("/listadmin")
    public List<AdminDto> getListAdmin(){
        List<AdminDto> list = new ArrayList<>();
        for(Admin i: adminRepository.findAll()){
            list.add(entityToDto.convertEntityToDto(i));
        }
        return list;
    }

    @PostMapping("/savedata")
    public DefaultResponse<AdminDto> saveAdmin(@RequestBody AdminDto adminDto){
        Admin admin = dtoToEntity.convertDtoToEntity(adminDto);
        DefaultResponse<AdminDto> defaultResponse = new DefaultResponse<>();
        Optional<Admin> optionalAdmin = adminRepository.findByUsernameAndPassword(adminDto.getUsername(), adminDto.getPassword());
        if(optionalAdmin.isPresent()){
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to save data, data was exists");
        }else{
            adminRepository.save(admin);
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setData(adminDto);
            defaultResponse.setMessage("Succeeded to save data");
        }
        return defaultResponse;
    }

    @DeleteMapping("/delete/{id}")
    public DefaultResponse deleteById(@PathVariable ("id") Integer id){
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if(optionalAdmin.isPresent()){
            adminRepository.delete(optionalAdmin.get());
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Succeeded delete data");
        }else{
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to delete data, data was not found");
        }
        return defaultResponse;
    }

    @PutMapping("/update/{id}")
    public DefaultResponse updateById (@PathVariable Integer id, @RequestBody AdminDto adminDto){
        DefaultResponse defaultResponse = new DefaultResponse();
        try{
            Optional<Admin> optionalAdmin = adminRepository.findById(id);
            Admin admin = optionalAdmin.get();
            if (optionalAdmin.isPresent()){
                admin.setUsername(adminDto.getUsername());
                admin.setPassword(adminDto.getPassword());
                adminRepository.save(admin);
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setData(adminDto);
                defaultResponse.setMessage("Succeeded update data");
            }
        }catch(Exception e){
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to update data, Id was not found");
        }
        return defaultResponse;
    }
}
