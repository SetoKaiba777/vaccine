package com.kaibakorp.vaccine.api.conversion;

import com.kaibakorp.vaccine.api.rpmodel.UpdateUserDTO;
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

    public static User updateUserToEntity(UpdateUserDTO UpdateUser, ModelMapper modelMapper){
        return modelMapper.map(UpdateUser,User.class);
    }

    public static List<UserResponse> list(List<User> users,ModelMapper modelMapper){
        return users.stream().
                map(user -> modelMapper.map(user,UserResponse.class)).
                collect(Collectors.toList());
    }
}


