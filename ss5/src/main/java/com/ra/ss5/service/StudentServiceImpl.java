package com.ra.ss5.service;
import com.ra.ss5.model.entity.Student;
import com.ra.ss5.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Override
    public List<Student> getAll() {
        return repository.findAll();
    }

    @Override
    public Student getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Student insert(Student student) {
        return repository.save(student);
    }

    @Override
    public Student update(Long id, Student student) {
        if (repository.existsById(id)) {
            student.setStudentId(id);
            return repository.save(student);
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<Student> searchByName(String name) {
        return repository.findByFullNameContainingIgnoreCase(name);
    }

    @Override
    public List<Student> searchByAddress(String address) {
        return repository.findByAddressContainingIgnoreCase(address);
    }

    @Override
    public List<Student> searchByClassName(String className) {
        return repository.findByClassNameContainingIgnoreCase(className);
    }
}