package com.school.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.school.model.Employee;
import com.school.service.EmployeeService;

@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model){

        Employee emp = employeeService.login(username,password);

        if(emp == null){
            model.addAttribute("error","Invalid username or password");
            return "login";
        }

        session.setAttribute("user", emp);

        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}