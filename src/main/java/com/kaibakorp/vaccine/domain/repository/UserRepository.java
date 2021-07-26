package com.kaibakorp.vaccine.domain.repository;

import com.kaibakorp.vaccine.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
    public User findByCpf(String cpf);
}
