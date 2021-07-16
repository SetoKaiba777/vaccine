package com.kaibakorp.vaccine.api.conversion;

import com.kaibakorp.vaccine.api.rpmodel.UserDTO;
import com.kaibakorp.vaccine.api.rpmodel.UserResponse;
import com.kaibakorp.vaccine.domain.model.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ConversionUser {
    public static User toEntity(UserDTO userDTO, ModelMapper modelMapper) {
        return modelMapper.map(userDTO, User.class);
    }

    public static UserResponse toResponse(User user, ModelMapper modelMapper){
        return modelMapper.map(user,UserResponse.class);
    }
    public List<UserResponse> list(List<User> users,ModelMapper modelMapper){
        return users.stream().
                map(user -> this.toResponse(user,modelMapper)).
                collect(Collectors.toList());
    }
}


