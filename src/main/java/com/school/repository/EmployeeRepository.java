package com.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{

    Employee findByUsernameAndPassword(String username,String password);

}