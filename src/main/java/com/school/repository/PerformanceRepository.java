package com.school.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.school.model.Performance;

public interface PerformanceRepository extends JpaRepository<Performance, Long>{

    List<Performance> findByStudentId(Long studentId);

}