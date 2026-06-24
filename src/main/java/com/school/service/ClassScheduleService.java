package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.ClassSchedule;
import com.school.repository.ClassScheduleRepository;

@Service
public class ClassScheduleService {

    @Autowired
    private ClassScheduleRepository classScheduleRepository;

    // Save Schedule
    public void saveSchedule(ClassSchedule schedule) {
        classScheduleRepository.save(schedule);
    }

    // Get All Schedules
    public List<ClassSchedule> getAllSchedules() {
        return classScheduleRepository.findAll();
    }

    // Get Schedule by ID (for Edit)
    public ClassSchedule getScheduleById(Long id) {
        return classScheduleRepository.findById(id).orElse(null);
    }

    // Delete Schedule
    public void deleteSchedule(Long id) {
        classScheduleRepository.deleteById(id);
    }
}