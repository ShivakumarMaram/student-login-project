package com.example.demo.Repo;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.Student;
public interface StudentRepo extends JpaRepository<Student,Long>{
Optional<Student>findByUsername(String username);
}