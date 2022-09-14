package com.example.perpustakaan.controller.user.crud;

import com.example.perpustakaan.controller.user.converter.EntityToDto;
import com.example.perpustakaan.model.dto.UserDto;
import com.example.perpustakaan.model.dto.DefaultResponse;
import com.example.perpustakaan.model.entity.User;
import com.example.perpustakaan.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;


    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    EntityToDto entityToDto = new EntityToDto();

    @GetMapping("/list-user")
    public List<UserDto> getListUser() {
        List<UserDto> list = new ArrayList<>();
        for (User i : userRepository.findAll()) {
            list.add(entityToDto.convertEntityToDto(i));
        }
        return list;
    }

    @PostMapping("/profile")
    public DefaultResponse<UserDto> profile(@RequestBody UserDto userDto) {
        DefaultResponse<UserDto> defaultResponse = new DefaultResponse<>();
        Optional<User> optionalUser = userRepository.findById(userDto.getId());
        if (optionalUser.isEmpty()) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Data wasn't found");
        } else {
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setData(entityToDto.convertEntityToDto(optionalUser.get()));
            defaultResponse.setMessage("Data was found");
        }
        return defaultResponse;
    }

    @DeleteMapping("/delete/{id}")
    public DefaultResponse deleteById(@PathVariable("id") Integer id) {
        DefaultResponse defaultResponse = new DefaultResponse();
        Optional<User> optionalAdmin = userRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            userRepository.delete(optionalAdmin.get());
            defaultResponse.setStatus(Boolean.TRUE);
            defaultResponse.setMessage("Succeeded delete data");
        } else {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to delete data, data was not found");
        }
        return defaultResponse;
    }

    @PutMapping("/update/{id}")
    public DefaultResponse updateById(@PathVariable Integer id, @RequestBody UserDto userDto) {
        DefaultResponse defaultResponse = new DefaultResponse();
        try {
            Optional<User> optionalUser = userRepository.findById(id);
            User user = optionalUser.get();
            if (optionalUser.isPresent()) {
                user.setUsername(userDto.getUsername());
                user.setPassword(userDto.getPassword());
                userRepository.save(user);
                defaultResponse.setStatus(Boolean.TRUE);
                defaultResponse.setData(userDto);
                defaultResponse.setMessage("Succeeded update data");
            }
        } catch (Exception e) {
            defaultResponse.setStatus(Boolean.FALSE);
            defaultResponse.setMessage("Failed to update data, Id was not found");
        }
        return defaultResponse;
    }
}
