package com.kaibakorp.vaccine.api.controller;

import com.kaibakorp.vaccine.api.conversion.ConversionVaccine;
import com.kaibakorp.vaccine.api.rpmodel.UpdateVaccineDTO;
import com.kaibakorp.vaccine.api.rpmodel.UserResponse;
import com.kaibakorp.vaccine.api.rpmodel.VaccineDTO;
import com.kaibakorp.vaccine.api.rpmodel.VaccineResponse;
import com.kaibakorp.vaccine.domain.model.Vaccine;
import com.kaibakorp.vaccine.domain.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/vaccine")
public class VaccineController {

    @Autowired
    private VaccineService vaccineService;

    @Autowired
    private ConversionVaccine conversionVaccine;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VaccineResponse addVac(@RequestBody @Valid VaccineDTO input) {
        Vaccine vaccine = conversionVaccine.toEntity(input);
        return conversionVaccine.toResponse(vaccineService.addVac(vaccine));
    }
    @GetMapping
    public List<VaccineResponse> listVac(){
        return conversionVaccine.list(vaccineService.findAll());
    }

    @GetMapping("/{user_id}")
    public List<VaccineResponse> userVac(@PathVariable Long user_id){
        return conversionVaccine.list(vaccineService.userVac(user_id));
    }

    @PutMapping("/{vac_id}")
    public VaccineResponse vacUpdate(@PathVariable Long vac_id, @RequestBody @Valid UpdateVaccineDTO updateVaccineDTO){
        Vaccine vac = conversionVaccine.updateVacToEntity(updateVaccineDTO);
        return conversionVaccine.toResponse(vaccineService.updateVac(vac_id,vac));
    }
    @DeleteMapping("/{vac_id}")
    public ResponseEntity<VaccineResponse> delete(@PathVariable Long vac_id){
        vaccineService.removeVac(vac_id);
        return ResponseEntity.noContent().build();
    }
}
