package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.Attendance;
import com.school.repository.AttendanceRepository;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository repo;

    // Save attendance
    public void saveAttendance(Attendance attendance){
        repo.save(attendance);
    }

    // Get all attendance
    public List<Attendance> getAllAttendance(){
        return repo.findAll();
    }

    // Count attendance records (dashboard)
    public long getAttendanceCount(){
        return repo.count();
    }

    // Get by ID (EDIT)
    public Attendance getAttendanceById(Long id){
        return repo.findById(id).orElse(null);
    }

    // Delete attendance
    public void deleteAttendance(Long id){
        repo.deleteById(id);
    }
}