package com.example.demo.Service;

import java.util.Optional;

import com.example.demo.Entity.Student;

public interface StudentService {
Student savestudent(Student student);
Optional<Student>findByUsername(String string);
Student saveStudent(Student student);
}