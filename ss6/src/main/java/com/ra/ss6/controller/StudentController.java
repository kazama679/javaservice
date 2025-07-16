package com.ra.ss6.controller;

import com.ra.ss6.model.entity.Classes;
import com.ra.ss6.model.entity.Student;
import com.ra.ss6.service.ClassesService;
import com.ra.ss6.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private ClassesService classesService;

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping("/class/{classId}")
    public List<Student> getByClassId(@PathVariable Long classId) {
        return studentService.findByClassId(classId);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Student student) {
        if (student.getClasses() != null) {
            Optional<Classes> cls = classesService.findById(student.getClasses().getClassId());
            if (cls.isPresent()) {
                student.setClasses(cls.get());
                return ResponseEntity.ok(studentService.save(student));
            }
        }
        return ResponseEntity.badRequest().body("Invalid class ID");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Student updated) {
        return studentService.findById(id)
                .map(st -> {
                    st.setFullName(updated.getFullName());
                    st.setAddress(updated.getAddress());
                    st.setBirthday(updated.getBirthday());
                    st.setGender(updated.getGender());
                    st.setClasses(updated.getClasses());
                    return ResponseEntity.ok(studentService.save(st));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
