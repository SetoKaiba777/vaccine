package com.kaibakorp.vaccine.api.controller;

import com.kaibakorp.vaccine.api.rpmodel.UserDTO;
import com.kaibakorp.vaccine.api.rpmodel.UserResponse;
import com.kaibakorp.vaccine.domain.model.User;
import com.kaibakorp.vaccine.domain.repository.UserRepository;
import com.kaibakorp.vaccine.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserService userService;


    @GetMapping
    public List<UserResponse> listUser(){
        return userService.list(modelMapper);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@Valid @RequestBody UserDTO input) {
        User user = input.toEntity(modelMapper);
        return userService.addUser(user).toResponse(modelMapper);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO input) {
        User user = input.toEntity(modelMapper);
        return userService.updateUser(id, user,modelMapper);
    }

}
