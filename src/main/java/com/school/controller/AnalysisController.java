package com.school.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.school.model.Employee;
import com.school.service.StudentService;
import com.school.service.FeePaymentService;
import com.school.service.AttendanceService;
import com.school.service.PerformanceService;

@Controller
public class AnalysisController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FeePaymentService feePaymentService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private PerformanceService performanceService;

    @GetMapping("/analysis")
    public String dashboard(Model model, HttpSession session) {

        // Session check
        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        // Only ADMIN or PRINCIPAL allowed
        if(!(emp.getRole().equals("ADMIN") || emp.getRole().equals("PRINCIPAL"))){
            return "redirect:/index";
        }

        model.addAttribute("students", studentService.getStudentCount());
        model.addAttribute("fees", feePaymentService.getTotalFees());
        model.addAttribute("attendance", attendanceService.getAttendanceCount());
        model.addAttribute("averageMarks", performanceService.getAverageMarks());

        return "analysis";
    }
}