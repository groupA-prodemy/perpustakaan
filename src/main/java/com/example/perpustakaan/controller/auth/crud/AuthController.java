package com.example.perpustakaan.controller.auth.crud;

import com.example.perpustakaan.controller.auth.converter.DtoToEntity;
import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.LoginDto;
import com.example.perpustakaan.model.dto.UserDto;
import com.example.perpustakaan.model.entity.Role;
import com.example.perpustakaan.model.entity.User;
import com.example.perpustakaan.repository.RoleRepository;
import com.example.perpustakaan.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    DtoToEntity dtoToEntity = new DtoToEntity();

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public AuthController(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public DefaultResponse LoginUser(@RequestBody LoginDto loginDto){
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<User> optionalUserUsername = userRepository.findByUsername(loginDto.getUsername());
        Optional<User> optionalUserPassword = userRepository.findByPassword(loginDto.getPassword());
        if (optionalUserUsername.isPresent()){
            if(optionalUserPassword.isPresent()){
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setMessage("Succeeded Login");
            }else {
                defaultResponse.setStatus(Boolean.FALSE);
                defaultResponse.setMessage("Wrong Password!!!");
            }
        }else {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Username Unregistered!!! Please, register before");
        }
        return defaultResponse;
    }

    @PostMapping("/logout")
    public DefaultResponse LogoutUser(@RequestBody LoginDto loginDto){
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (optionalUser.isPresent()){
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Succeeded Logout");
        }else {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Error");
        }
        return defaultResponse;
    }

    @PostMapping("/register")
    public DefaultResponse<UserDto> regist(@RequestBody UserDto userDto) {
        User user = dtoToEntity.convertDtoToEntity(userDto);
        DefaultResponse<UserDto> defaultResponse = new DefaultResponse<>();
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());
        Optional<Role> optionalRole = roleRepository.findByRoleId(userDto.getRoleId());
        if (optionalUser.isPresent()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to save data, data was exists");
        } else {
            user.setRoleName(optionalRole.get().getRoleName());
            userRepository.save(user);
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setData(userDto);
            defaultResponse.setMessage("Succeeded to save data");
        }
        return defaultResponse;
    }


}
