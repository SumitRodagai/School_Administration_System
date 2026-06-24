package com.school.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.school.model.Attendance;
import com.school.model.Employee;
import com.school.service.AttendanceService;

@Controller
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    // ======================
    // Attendance Form
    // ======================
    @GetMapping("/attendance")
    public String showAttendanceForm(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("TEACHER") || emp.getRole().equals("ADMIN"))){
            return "redirect:/index";
        }

        model.addAttribute("attendance", new Attendance());

        return "attendance";
    }

    // ======================
    // SAVE
    // ======================
    @PostMapping("/saveAttendance")
    public String saveAttendance(@ModelAttribute Attendance attendance,
                                 HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("TEACHER") || emp.getRole().equals("ADMIN"))){
            return "redirect:/index";
        }

        attendanceService.saveAttendance(attendance);

        return "redirect:/attendanceList";
    }

    // ======================
    // LIST
    // ======================
    @GetMapping("/attendanceList")
    public String attendanceList(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("TEACHER") || emp.getRole().equals("ADMIN"))){
            return "redirect:/index";
        }

        model.addAttribute("attendanceList", attendanceService.getAllAttendance());

        return "attendance_list";
    }

    // ======================
    // EDIT
    // ======================
    @GetMapping("/editAttendance/{id}")
    public String editAttendance(@PathVariable Long id,
                                 Model model,
                                 HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Attendance attendance = attendanceService.getAttendanceById(id);

        model.addAttribute("attendance", attendance);

        return "edit_attendance";
    }

    // ======================
    // UPDATE
    // ======================
    @PostMapping("/updateAttendance")
    public String updateAttendance(@ModelAttribute Attendance attendance,
                                   HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        attendanceService.saveAttendance(attendance);

        return "redirect:/attendanceList";
    }

    // ======================
    // DELETE
    // ======================
    @GetMapping("/deleteAttendance/{id}")
    public String deleteAttendance(@PathVariable Long id,
                                   HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        attendanceService.deleteAttendance(id);

        return "redirect:/attendanceList";
    }
}