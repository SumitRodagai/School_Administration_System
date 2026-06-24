package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.Performance;
import com.school.repository.PerformanceRepository;

@Service
public class PerformanceService {

    @Autowired
    private PerformanceRepository performanceRepository;

    // Save performance
    public void savePerformance(Performance performance) {
        performanceRepository.save(performance);
    }

    // Get all performance records
    public List<Performance> getAllPerformance() {
        return performanceRepository.findAll();
    }

    // Count performance records (for dashboard)
    public long getPerformanceCount() {
        return performanceRepository.count();
    }
    

    // Calculate average marks
    public double getAverageMarks() {

        List<Performance> list = performanceRepository.findAll();

        int total = 0;
        int count = 0;

        for (Performance p : list) {
            total += p.getMarks();
            count++;
        }

        if (count == 0) {
            return 0;
        }

        return (double) total / count;
    }
    
    public List<Performance> getByStudentId(Long studentId){
        return performanceRepository.findByStudentId(studentId);
    }
    
    public List<Performance> getPerformanceByStudent(Long studentId){
        return performanceRepository.findByStudentId(studentId);
    }
    
    public Performance getPerformanceById(Long id){
        return performanceRepository.findById(id).orElse(null);
    }

    public void deletePerformance(Long id){
        performanceRepository.deleteById(id);
    }
}