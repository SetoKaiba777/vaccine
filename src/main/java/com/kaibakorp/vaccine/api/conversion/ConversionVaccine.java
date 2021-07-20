package com.kaibakorp.vaccine.api.conversion;

import com.kaibakorp.vaccine.api.rpmodel.*;
import com.kaibakorp.vaccine.domain.exception.DontFoundEntityException;
import com.kaibakorp.vaccine.domain.model.User;
import com.kaibakorp.vaccine.domain.model.Vaccine;
import com.kaibakorp.vaccine.domain.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ConversionVaccine {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Vaccine toEntity(VaccineDTO vaccineDTO) {
        User user = userRepository.findByEmail(vaccineDTO.getUserEmail());
        if(user==null){
            throw new DontFoundEntityException("Don't found this user e-mail");
        }
        var vaccine = new Vaccine();
        vaccine.setUser(user);
        vaccine.setVaccine(vaccineDTO.getVaccine());
        return vaccine;
    }

    public VaccineResponse toResponse(Vaccine vaccine){
        return modelMapper.map(vaccine, VaccineResponse.class);
    }

    public Vaccine updateVacToEntity(UpdateVaccineDTO updateVaccine){
        return modelMapper.map(updateVaccine,Vaccine.class);
    }

    public List<VaccineResponse> list(List<Vaccine> vaccines){
        return vaccines.stream().
                map(vaccine -> this.toResponse(vaccine)).
                collect(Collectors.toList());
    }
}
