package com.spring.security.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.security.security.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    Users findByUsername(String username);
}
