package com.example.perpustakaan.controller.auth.crud;

import com.example.perpustakaan.controller.auth.converter.DtoToEntity;
import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.dto.auth.LoginDto;
import com.example.perpustakaan.model.dto.auth.LoginDtoResponse;
import com.example.perpustakaan.model.dto.user.UserDtoRegister;
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
        Optional<User> optionalUser = userRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getPassword());
        if (optionalUser.isEmpty()){
            if(userRepository.findByUsername(loginDto.getUsername()).isPresent()){
                defaultResponse.setStatus(Boolean.FALSE);
                defaultResponse.setMessage("Wrong Password!!!");
            }else {
                defaultResponse.setStatus(Boolean.FALSE);
                defaultResponse.setMessage("Username Unregistered!!! Please, register before");
            }
        }else {
            loginDtoResponse.setUserId(optionalUser.get().getId());
            loginDtoResponse.setName(optionalUser.get().getName());
            loginDtoResponse.setUsername(optionalUser.get().getUsername());
            loginDtoResponse.setRoleId(optionalUser.get().getRoleId());
            loginDtoResponse.setRoleName(optionalUser.get().getRole().getRoleName());
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Succeeded Login, Hallo " + optionalUser.get().getRole().getRoleName());
            defaultResponse.setData(loginDtoResponse);
        }
        return defaultResponse;
    }

    @PostMapping("/logout/{userId}")
    public DefaultResponse LogoutUser(@PathVariable ("userId") Integer userId){
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<User> optionalUser = userRepository.findById(userId);
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
            try{
                if(userRepository.findByUsername(userDtoRegister.getUsername()).isEmpty()){
                    userRepository.save(user);
                    defaultResponse.setStatus(Boolean.TRUE);
                    defaultResponse.setData(userDtoRegister);
                    defaultResponse.setMessage("Succeeded to save data, you registered as " + optionalRole.get().getRoleName() + " (Role Id : " + userDtoRegister.getRoleId() + ")");
                }
                else {
                    defaultResponse.setStatus(Boolean.FALSE);
                    defaultResponse.setMessage("Failed to save data, username was exists. Please use other username");
                }
            }catch(Exception e){
                defaultResponse.setStatus(Boolean.FALSE);
                defaultResponse.setMessage("Failed to save data, role for this Id still empty yet in table t_role. Please choose the others");
            }
        }
        return defaultResponse;
    }
}
