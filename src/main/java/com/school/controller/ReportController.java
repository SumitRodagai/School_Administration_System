package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.school.model.Employee;
import com.school.service.AttendanceService;
import com.school.service.FeePaymentService;
import com.school.service.PerformanceService;
import com.school.service.StudentService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReportController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private FeePaymentService feePaymentService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private PerformanceService performanceService;


    // Reports dashboard
    @GetMapping("/reports")
    public String reportsPage(HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        // ADMIN or PRINCIPAL allowed
        if(!(emp.getRole().equals("PRINCIPAL") || emp.getRole().equals("ADMIN"))){
            return "redirect:/index";
        }

        return "reports";
    }


    // Student Admission Report
    @GetMapping("/studentReport")
    public String studentReport(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("PRINCIPAL") || emp.getRole().equals("ADMIN"))){
            return "redirect:/index";
        }

        model.addAttribute("students", studentService.getAllStudents());

        return "student_report";
    }


    // Attendance Report
    @GetMapping("/attendanceReport")
    public String attendanceReport(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("PRINCIPAL") || emp.getRole().equals("ADMIN"))){
            return "redirect:/index";
        }

        model.addAttribute("attendanceList", attendanceService.getAllAttendance());

        return "attendance_report";
    }


    // Performance Report
    @GetMapping("/performanceReportPage")
    public String performanceReport(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!emp.getRole().equals("PRINCIPAL")){
            return "redirect:/index";
        }

        model.addAttribute("performanceList", performanceService.getAllPerformance());

        return "performance_report";
    }


    // Fee Report
    @GetMapping("/feeReport")
    public String feeReport(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("PRINCIPAL") || emp.getRole().equals("ADMIN"))){
            return "redirect:/index";
        }

        model.addAttribute("fees", feePaymentService.getAllPayments());

        return "fee_report";
    }

}