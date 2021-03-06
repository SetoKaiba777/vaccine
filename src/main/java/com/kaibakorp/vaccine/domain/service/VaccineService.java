package com.kaibakorp.vaccine.domain.service;

import com.kaibakorp.vaccine.domain.exception.DontFoundEntityException;
import com.kaibakorp.vaccine.domain.model.User;
import com.kaibakorp.vaccine.domain.model.Vaccine;
import com.kaibakorp.vaccine.domain.repository.UserRepository;
import com.kaibakorp.vaccine.domain.repository.VaccineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class VaccineService {

    @Autowired
    private VaccineRepository vaccineRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Vaccine> findAll() {
        return vaccineRepository.findAll();
    }

    public List<Vaccine> userVac(Long id) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new DontFoundEntityException("User don't found"));
        return user.getVaccines();
    }

    public Vaccine addVac(Vaccine vaccine) {
        User user = userRepository.findById(vaccine.getUser().getId()).
                orElseThrow(() -> new DontFoundEntityException("User don't found"));
        vaccine.setUser(user);
        vaccine.setVacDate(LocalDate.now());
        user.getVaccines().add(vaccine);
        return vaccineRepository.save(vaccine);
    }

    public Vaccine updateVac(Long id, Vaccine vaccine) {
        if (!vaccineRepository.existsById(id)) {
            throw new DontFoundEntityException("Don't found this registry");
        }
        Vaccine vacnow = vaccineRepository.findById(id).get();
        checkUpdateFields(vacnow, vaccine);
        vaccine = vaccineRepository.save(vaccine);
        return vaccine;
    }

    public void removeVac(Long id) {
        if (!vaccineRepository.existsById(id)) {
            throw new DontFoundEntityException("Don't found this registry");
        }
        Vaccine vac = vaccineRepository.findById(id).get();
        vaccineRepository.delete(vac);
    }

    private void checkUpdateFields(Vaccine vacnow, Vaccine vac){
        vac.setId(vacnow.getId());
        if(vac.getUser()==null){
            vac.setUser(vacnow.getUser());
        }
        if(vac.getVaccine()==null){
            vac.setVaccine(vacnow.getVaccine());
        }
        if(vac.getVacDate()==null)
           vac.setVacDate(vacnow.getVacDate());
        }
}

