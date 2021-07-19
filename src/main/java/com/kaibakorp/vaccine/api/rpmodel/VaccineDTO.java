package com.kaibakorp.vaccine.api.rpmodel;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class VaccineDTO {

    @NotBlank
    @Size(max=255)
    private String vaccine;

    @NotBlank
    @Email
    @Size(max=255)
    private String userEmail;

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
}
