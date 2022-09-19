package com.example.perpustakaan.controller.role.crud;

import com.example.perpustakaan.controller.role.converter.DtoToEntity;
import com.example.perpustakaan.controller.role.converter.EntityToDto;
import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.RoleDtoList;
import com.example.perpustakaan.model.dto.RoleDtoSave;
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
    public List<RoleDtoList> getListRole() {
        List<RoleDtoList> list = new ArrayList<>();
        for (Role i : roleRepository.findAll()) {
            list.add(entityToDto.convertEntityToDto(i));
        }
        return list;
    }

    @PostMapping("/save-role")
    public DefaultResponse<RoleDtoList> saveRole(@RequestBody RoleDtoSave roleDtoSave, RoleDtoList roleDtoList) {
        Role role = dtoToEntity.convertDtoToEntity(roleDtoSave);
        DefaultResponse<RoleDtoList> defaultResponse = new DefaultResponse<>();
        Optional<Role> optionalRole = roleRepository.findByRoleName(roleDtoSave.getRoleName());
        if (optionalRole.isPresent()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to save data, data was exists");
        } else {
            roleRepository.save(role);
            roleDtoList.setRoleId(roleRepository.findByRoleName(roleDtoSave.getRoleName()).get().getRoleId());
            roleDtoList.setRoleName(roleRepository.findByRoleName(roleDtoSave.getRoleName()).get().getRoleName());
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setData(roleDtoList);
            defaultResponse.setMessage("Succeeded to save data");
        }
        return defaultResponse;
    }

    @DeleteMapping("/delete/{id}")
    public DefaultResponse deleteById(@PathVariable("id") Integer id) {
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<Role> optionalRole = roleRepository.findById(id);
        try {
            if (optionalRole.isPresent()) {
                roleRepository.delete(optionalRole.get());
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setMessage("Succeeded delete data");
            } else {
                defaultResponse.setStatus(Boolean.FALSE);
                defaultResponse.setMessage("Failed to delete data, data was not found");
            }

        }catch (Exception e){
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to delete data. Your data was referenced from table t_user. Change that before you want to delete this data");
        }
        return defaultResponse;
    }

    @PutMapping("/update/{id}")
    public DefaultResponse updateById(@PathVariable Integer id, @RequestBody RoleDtoSave roleDtoSave, RoleDtoList roleDtoList) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Optional<Role> optionalRole = roleRepository.findById(id);
            Role role = optionalRole.get();
            if (optionalRole.isPresent()) {
                role.setRoleName(roleDtoSave.getRoleName());
                roleRepository.save(role);
                roleDtoList.setRoleId(roleRepository.findById(id).get().getRoleId());
                roleDtoList.setRoleName(roleRepository.findById(id).get().getRoleName());
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setData(roleDtoList);
                defaultResponse.setMessage("Succeeded update data");
            }
        } catch (Exception e) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to update data, Id was not found");
        }
        return defaultResponse;
    }

}
