package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.school.model.ClassSchedule;
import com.school.service.ClassScheduleService;
import com.school.service.SyllabusService;

@Controller
public class ClassScheduleController {

    @Autowired
    private ClassScheduleService classScheduleService;

    @Autowired
    private SyllabusService syllabusService;   // ✅ FIX ADDED

    // ======================
    // SHOW FORM
    // ======================
    @GetMapping("/schedule")
    public String showScheduleForm(Model model){

        model.addAttribute("subjects", syllabusService.getAllSyllabus());

        return "schedule";
    }

    // ======================
    // SAVE
    // ======================
    @PostMapping("/saveSchedule")
    public String saveSchedule(@ModelAttribute ClassSchedule schedule) {

        classScheduleService.saveSchedule(schedule);

        return "redirect:/scheduleList";
    }

    // ======================
    // LIST
    // ======================
    @GetMapping("/scheduleList")
    public String scheduleList(Model model) {

        model.addAttribute("schedules", classScheduleService.getAllSchedules());

        return "schedule_list";
    }

    // ======================
    // EDIT
    // ======================
    @GetMapping("/editSchedule/{id}")
    public String editSchedule(@PathVariable Long id, Model model){

        model.addAttribute("schedule", classScheduleService.getScheduleById(id));
        model.addAttribute("subjects", syllabusService.getAllSyllabus());

        return "edit_schedule";
    }

    // ======================
    // UPDATE
    // ======================
    @PostMapping("/updateSchedule")
    public String updateSchedule(@ModelAttribute ClassSchedule schedule){

        classScheduleService.saveSchedule(schedule);

        return "redirect:/scheduleList";
    }

    // ======================
    // DELETE
    // ======================
    @GetMapping("/deleteSchedule/{id}")
    public String deleteSchedule(@PathVariable Long id){

        classScheduleService.deleteSchedule(id);

        return "redirect:/scheduleList";
    }
}