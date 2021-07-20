package com.kaibakorp.vaccine.api.rpmodel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UpdateVaccineDTO {

    @Size(max=255)
    private String vaccine;

    @Email
    @Size(max=255)
    private String userEmail;

    private LocalDate vacDate;

    public String getVaccine() {
        return vaccine;
    }

    public void setVaccine(String vaccine) {
        this.vaccine = vaccine;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public LocalDate getVacDate() {
        return vacDate;
    }

    public void setVacDate(LocalDate vacDate) {
        this.vacDate = vacDate;
    }
}
