package com.example.demo.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.Entity.Student;
import com.example.demo.Repo.StudentRepo;
@Service
public class StudentServiceimpl implements StudentService {
	private final StudentRepo studentrepo;
	
	public StudentServiceimpl(StudentRepo  studentrepo) {
	this.studentrepo=studentrepo;

}

	@Override
	public Student saveStudent(Student student) {
		// TODO Auto-generated method stub
		return studentrepo.save(student);
	}

	@Override
	public Optional<Student> findByUsername(String username) {
		// TODO Auto-generated method stub
		return studentrepo.findByUsername(username);
	}

	@Override
	public Student savestudent(Student student) {
		// TODO Auto-generated method stub
		return null;
	}
}