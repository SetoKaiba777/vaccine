package com.kaibakorp.vaccine.api.controller;

import com.kaibakorp.vaccine.api.conversion.ConversionUser;
import com.kaibakorp.vaccine.api.rpmodel.UpdateUserDTO;
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
    private UserService userService;

    @Autowired
    private ConversionUser conversionUser;


    @GetMapping
    public List<UserResponse> listUser(){
        List<User> users = userService.findAll();
        return conversionUser.list(users);
    }

    @GetMapping("/{id}")
    public UserResponse listUser(@PathVariable Long id){
        return conversionUser.toResponse(userService.findUser(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse addUser(@Valid @RequestBody UserDTO input) {
        User user = conversionUser.toEntity(input);
        return conversionUser.toResponse(userService.addUser(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserDTO input) {
        User user = conversionUser.updateUserToEntity(input);
        UserResponse upUser = conversionUser.toResponse(userService.updateUser(id,user));
        return ResponseEntity.ok(upUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponse> delete(@PathVariable Long id){
        userService.removeUser(id);
        return ResponseEntity.noContent().build();
    }
}
