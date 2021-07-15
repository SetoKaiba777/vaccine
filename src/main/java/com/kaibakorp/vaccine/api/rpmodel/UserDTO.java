package com.kaibakorp.vaccine.api.rpmodel;

import com.kaibakorp.vaccine.domain.model.User;
import org.hibernate.validator.constraints.br.CPF;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class UserDTO {

    @Size(max=255)
    private String name;

    @CPF
    private String cpf;

    @Email
    @Size(max=255)
    private String email;

    private LocalDate bornDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public User toEntity(ModelMapper modelMapper) {
        return modelMapper.map(this, User.class);
    }
}

