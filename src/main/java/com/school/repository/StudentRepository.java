package com.school.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

    List<Student> findByNameContainingIgnoreCase(String name);

}