package com.example.perpustakaan.controller.role.crud;

import com.example.perpustakaan.controller.role.converter.DtoToEntity;
import com.example.perpustakaan.controller.role.converter.EntityToDto;
import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.RoleDto;
import com.example.perpustakaan.model.entity.Role;
import com.example.perpustakaan.repository.RoleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {

    DtoToEntity dtoToEntity = new DtoToEntity();
    EntityToDto entityToDto = new EntityToDto();

    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping("/list-role")
    public List<RoleDto> getListRole() {
        List<RoleDto> list = new ArrayList<>();
        for (Role i : roleRepository.findAll()) {
            list.add(entityToDto.convertEntityToDto(i));
        }
        return list;
    }

    @PostMapping("/save-role")
    public DefaultResponse<RoleDto> saveRole(@RequestBody RoleDto roleDto) {
        Role role = dtoToEntity.convertDtoToEntity(roleDto);
        DefaultResponse<RoleDto> defaultResponse = new DefaultResponse<>();
        Optional<Role> optionalRole = roleRepository.findById(roleDto.getRoleId());
        if (optionalRole.isPresent()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to save data, data was exists");
        } else {
            roleRepository.save(role);
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setData(roleDto);
            defaultResponse.setMessage("Succeeded to save data");
        }
        return defaultResponse;
    }

    @DeleteMapping("/delete/{id}")
    public DefaultResponse deleteById(@PathVariable("id") Integer id) {
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            roleRepository.delete(optionalRole.get());
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Succeeded delete data");
        } else {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to delete data, data was not found");
        }
        return defaultResponse;
    }

    @PutMapping("/update/{id}")
    public DefaultResponse updateById(@PathVariable Integer id, @RequestBody RoleDto roleDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Optional<Role> optionalRole = roleRepository.findById(id);
            Role role = optionalRole.get();
            if (optionalRole.isPresent()) {
                role.setRoleId(roleDto.getRoleId());
                role.setRoleName(roleDto.getRoleName());
                roleRepository.save(role);
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setData(roleDto);
                defaultResponse.setMessage("Succeeded update data");
            }
        } catch (Exception e) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to update data, Id was not found");
        }
        return defaultResponse;
    }

}
