package com.kaibakorp.vaccine.domain.service;


import com.kaibakorp.vaccine.api.rpmodel.UserResponse;
import com.kaibakorp.vaccine.domain.exception.DontFoundEntityException;
import com.kaibakorp.vaccine.domain.exception.ServiceException;
import com.kaibakorp.vaccine.domain.model.User;
import com.kaibakorp.vaccine.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<UserResponse> list(ModelMapper modelMapper){
        List<User> users = userRepository.findAll();
        return users.stream().
                    map(user -> user.toResponse(modelMapper)).
                    collect(Collectors.toList());
        }

    public User addUser(User user){
        User userExisits = userRepository.findByEmail(user.getEmail());
        if(userExisits!=null && !userExisits.equals(user)){
            throw new ServiceException("This email already in data system");
        }
        return userRepository.save(user);
    }

    public ResponseEntity<UserResponse> updateUser(Long id, User user,ModelMapper modelMapper){
        if(!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        user.setId(id);
        user = userRepository.save(user);
        return ResponseEntity.ok(user.toResponse(modelMapper));
    }

    public ResponseEntity<User> removeUser(Long id){
        if(!userRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
