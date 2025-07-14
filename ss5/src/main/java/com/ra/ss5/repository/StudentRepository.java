package com.ra.ss5.repository;

import com.ra.ss5.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFullNameContainingIgnoreCase(String name);
    List<Student> findByAddressContainingIgnoreCase(String address);
    List<Student> findByClassNameContainingIgnoreCase(String className);
}