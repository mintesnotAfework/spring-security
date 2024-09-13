package com.spring.security.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.security.security.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
}
