package com.kaibakorp.vaccine.api.conversion;

import com.kaibakorp.vaccine.api.rpmodel.UpdateUserDTO;
import com.kaibakorp.vaccine.api.rpmodel.UserDTO;
import com.kaibakorp.vaccine.api.rpmodel.UserResponse;
import com.kaibakorp.vaccine.domain.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConversionUser {

    @Autowired
    private ModelMapper modelMapper;

    public User toEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

    public UserResponse toResponse(User user){
        return modelMapper.map(user,UserResponse.class);
    }

    public User updateUserToEntity(UpdateUserDTO UpdateUser){
        return modelMapper.map(UpdateUser,User.class);
    }

    public List<UserResponse> list(List<User> users){
        return users.stream().
                map(user -> this.toResponse(user)).
                collect(Collectors.toList());
    }
}


