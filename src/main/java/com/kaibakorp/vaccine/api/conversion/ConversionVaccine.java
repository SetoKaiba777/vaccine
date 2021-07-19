package com.kaibakorp.vaccine.api.conversion;

import com.kaibakorp.vaccine.api.rpmodel.*;
import com.kaibakorp.vaccine.domain.exception.DontFoundEntityException;
import com.kaibakorp.vaccine.domain.model.User;
import com.kaibakorp.vaccine.domain.model.Vaccine;
import com.kaibakorp.vaccine.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ConversionVaccine {

    public static Vaccine toEntity(VaccineDTO vaccineDTO, UserRepository userRepository) {
        User user = userRepository.findByEmail(vaccineDTO.getUserEmail());
        if(user==null){
            throw new DontFoundEntityException("Don't found this user e-mail");
        }
        var vaccine = new Vaccine();
        vaccine.setUser(user);
        vaccine.setVaccine(vaccineDTO.getVaccine());
        return vaccine;
    }

    public static VaccineResponse toResponse(Vaccine vaccine, ModelMapper modelMapper){
        return modelMapper.map(vaccine, VaccineResponse.class);
    }

//    public static User updateUserToEntity(UpdateUserDTO UpdateUser, ModelMapper modelMapper){
//        return modelMapper.map(UpdateUser,User.class);
//    }

    public static List<VaccineResponse> list(List<Vaccine> vaccines, ModelMapper modelMapper){
        return vaccines.stream().
                map(vaccine -> modelMapper.map(vaccine,VaccineResponse.class)).
                collect(Collectors.toList());
    }
}
