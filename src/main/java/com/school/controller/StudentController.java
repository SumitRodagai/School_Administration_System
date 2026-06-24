package com.school.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.school.model.Student;
import com.school.model.Employee;
import com.school.service.AttendanceService;
import com.school.service.FeePaymentService;
import com.school.service.PerformanceService;
import com.school.service.StudentService;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;
    
    @Autowired
    private FeePaymentService feePaymentService;

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private PerformanceService performanceService;

    // Dashboard after login
    @GetMapping("/index")
    public String dashboard(HttpSession session, Model model){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        model.addAttribute("role", emp.getRole());

        model.addAttribute("studentCount", studentService.getStudentCount());
        model.addAttribute("feeCount", feePaymentService.getFeeCount());
        model.addAttribute("attendanceCount", attendanceService.getAttendanceCount());
        model.addAttribute("performanceCount", performanceService.getPerformanceCount());

        return "index";
    }


    // Admission page
    @GetMapping("/admission")
    public String admissionForm(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        // Only ADMIN and CLERK allowed
        if(!(emp.getRole().equals("ADMIN") || emp.getRole().equals("CLERK"))){
            return "redirect:/index";
        }

        model.addAttribute("student", new Student());

        return "admission";
    }


    // Save student
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Student student,
                              HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("ADMIN") || emp.getRole().equals("CLERK"))){
            return "redirect:/index";
        }

        studentService.saveStudent(student);

        return "redirect:/students";
    }


    // View students
    @GetMapping("/students")
    public String viewStudents(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("ADMIN") || emp.getRole().equals("CLERK"))){
            return "redirect:/index";
        }

        model.addAttribute("students", studentService.getAllStudents());

        return "students";
    }


    // Edit student
    @GetMapping("/editStudent/{id}")
    public String editStudent(@PathVariable Long id,
                              Model model,
                              HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Student student = studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "edit_student";
    }


    // Update student
    @PostMapping("/updateStudent")
    public String updateStudent(@ModelAttribute Student student,
                                HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        studentService.saveStudent(student);

        return "redirect:/students";
    }


    // Delete student
    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable Long id,
                                HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        studentService.deleteStudent(id);

        return "redirect:/students";
    }
    
    @GetMapping("/searchStudent")
    public String searchStudent(@RequestParam("name") String name,
                                Model model,
                                HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        model.addAttribute("students", studentService.searchStudents(name));

        return "students";
    }

}