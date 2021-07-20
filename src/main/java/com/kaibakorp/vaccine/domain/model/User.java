package com.kaibakorp.vaccine.domain.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "Email")
    private String email;

    @Column(name = "Born_date")
    private LocalDate bornDate;

    @OneToMany(mappedBy = "user")
    private List<Vaccine> vaccines= new ArrayList<>();

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Vaccine> getVaccines() {
          return vaccines;
    }

    public void setVaccines(List<Vaccine> vaccines) {
      this.vaccines = vaccines;
    }
}

