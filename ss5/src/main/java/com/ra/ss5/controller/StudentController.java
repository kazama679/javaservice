package com.ra.ss5.controller;
import com.ra.ss5.model.entity.Student;
import com.ra.ss5.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("students", studentService.getAll());
        return "students/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        return "students/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Student student) {
        studentService.insert(student);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("student", studentService.getById(id));
        return "students/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id, @ModelAttribute Student student) {
        studentService.update(id, student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        studentService.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        List<Student> result = studentService.getAll().stream()
                .filter(s -> s.getFullName().toLowerCase().contains(keyword.toLowerCase()) ||
                        s.getAddress().toLowerCase().contains(keyword.toLowerCase()) ||
                        s.getClassName().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
        model.addAttribute("students", result);
        return "students/list";
    }
}