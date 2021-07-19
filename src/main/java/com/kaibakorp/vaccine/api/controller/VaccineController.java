package com.kaibakorp.vaccine.api.controller;

import com.kaibakorp.vaccine.api.conversion.ConversionVaccine;
import com.kaibakorp.vaccine.api.rpmodel.VaccineDTO;
import com.kaibakorp.vaccine.api.rpmodel.VaccineResponse;
import com.kaibakorp.vaccine.domain.model.User;
import com.kaibakorp.vaccine.domain.model.Vaccine;
import com.kaibakorp.vaccine.domain.repository.UserRepository;
import com.kaibakorp.vaccine.domain.service.VaccineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private UserRepository userRepository;

    private ConversionVaccine conversionVaccine;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineResponse addVac(@RequestBody @Valid VaccineDTO input) {
        Vaccine vaccine = conversionVaccine.toEntity(input, userRepository);
        return conversionVaccine.toResponse(vaccineService.addVac(vaccine),modelMapper);
    }
}
