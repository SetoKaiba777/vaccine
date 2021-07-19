package com.kaibakorp.vaccine.api.rpmodel;

import com.kaibakorp.vaccine.domain.model.User;

import javax.persistence.*;
import java.time.LocalDate;

public class VaccineResponse {

    private Long id;
    private String vaccine;
    private Long userId;
    private LocalDate vacDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getVacDate() {
        return vacDate;
    }

    public void setVacDate(LocalDate vacDate) {
        this.vacDate = vacDate;
    }
}
