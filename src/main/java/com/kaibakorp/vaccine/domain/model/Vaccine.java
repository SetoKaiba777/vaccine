package com.kaibakorp.vaccine.domain.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="VACCINE-REG")
public class Vaccine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Vaccine")
    private String vaccine;

    @ManyToOne
    private User user;

    @Column(name="Vac_date")
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

    public LocalDate getVacDate() {
        return vacDate;
    }

    public void setVacDate(LocalDate vacDate) {
        this.vacDate = vacDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
