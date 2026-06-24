package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.Student;
import com.school.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    // Save student
    public void saveStudent(Student student) {
        repo.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // Get student count (used in dashboard)
    public long getStudentCount() {
        return repo.count();
    }
    
    public Student getStudentById(Long id){
        return repo.findById(id).orElse(null);
    }

    public void deleteStudent(Long id){
        repo.deleteById(id);
    }
    
    public List<Student> searchStudents(String name){
        return repo.findByNameContainingIgnoreCase(name);
    }
    
}