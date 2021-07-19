package com.kaibakorp.vaccine.domain.repository;

import com.kaibakorp.vaccine.domain.model.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VaccineRepository extends JpaRepository<Vaccine,Long> {
}
