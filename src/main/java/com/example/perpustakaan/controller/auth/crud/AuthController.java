package com.example.perpustakaan.controller.auth.crud;

import com.example.perpustakaan.controller.auth.converter.DtoToEntity;
import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.LoginDto;
import com.example.perpustakaan.model.dto.LoginDtoResponse;
import com.example.perpustakaan.model.dto.UserDtoRegister;
import com.example.perpustakaan.model.entity.Role;
import com.example.perpustakaan.model.entity.User;
import com.example.perpustakaan.repository.RoleRepository;
import com.example.perpustakaan.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins="http://localhost:8080")
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
    public DefaultResponse<LoginDtoResponse> LoginUser(@RequestBody LoginDto loginDto){
        DefaultResponse<LoginDtoResponse> defaultResponse = new DefaultResponse();
        LoginDtoResponse loginDtoResponse = new LoginDtoResponse();
        Optional<User> optionalUserUsername = userRepository.findByUsername(loginDto.getUsername());
        Optional<User> optionalUserPassword = userRepository.findByPassword(loginDto.getPassword());
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (optionalUserUsername.isPresent()){
            if(optionalUserPassword.isPresent()){
                loginDtoResponse.setName(optionalUserPassword.get().getName());
                loginDtoResponse.setUsername(optionalUserPassword.get().getUsername());
                loginDtoResponse.setRoleName(optionalUserPassword.get().getRole().getRoleName());
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setMessage("Succeeded Login, Hallo " + optionalUser.get().getRole().getRoleName());
                defaultResponse.setData(loginDtoResponse);

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
    public DefaultResponse<UserDtoRegister> regist(@RequestBody UserDtoRegister userDtoRegister) {
        User user = dtoToEntity.convertDtoToEntityRegister(userDtoRegister);
        DefaultResponse<UserDtoRegister> defaultResponse = new DefaultResponse<>();
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(userDtoRegister.getUsername(), userDtoRegister.getPassword());
        Optional<Role> optionalRole = roleRepository.findById(userDtoRegister.getRoleId());
        if (optionalUser.isPresent()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to save data, data was exists");
        } else {
            userRepository.save(user);
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setData(userDtoRegister);
            defaultResponse.setMessage("Succeeded to save data, you registered as " + optionalRole.get().getRoleName() + " (Role Id : " + userDtoRegister.getRoleId() + ")");
        }
        return defaultResponse;
    }
}
