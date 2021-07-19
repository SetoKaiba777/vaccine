package com.kaibakorp.vaccine.api.controller;

import com.kaibakorp.vaccine.api.rpmodel.UserDTO;
import com.kaibakorp.vaccine.api.rpmodel.UserResponse;
import com.kaibakorp.vaccine.domain.exception.DontFoundEntityException;
import com.kaibakorp.vaccine.domain.exception.ServiceException;
import com.kaibakorp.vaccine.domain.model.User;
import com.kaibakorp.vaccine.domain.model.Vaccine;
import com.kaibakorp.vaccine.domain.repository.VaccineRepository;
import com.kaibakorp.vaccine.domain.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vaccine-reg")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private VaccineRepository vaccineRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vaccine addVac(@RequestBody Vaccine input) {
        return vaccineRepository.save(input);
    }

}
