package com.ra.ss5.service;

import com.ra.ss5.model.entity.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    Student getById(Long id);
    Student insert(Student student);
    Student update(Long id, Student student);
    void delete(Long id);
    List<Student> searchByName(String name);
    List<Student> searchByAddress(String address);
    List<Student> searchByClassName(String className);
}