package com.spring.rest.controller.restcontroller;

import com.spring.rest.controller.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Kim", "Callahan"));
        students.add(new Student("Harvey", "Bean"));
        students.add(new Student("Hubert", "Cortez"));
        return students;
    }
}
