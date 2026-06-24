package com.school.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.school.model.Employee;
import com.school.model.FeePayment;
import com.school.service.FeePaymentService;

@Controller
public class FeePaymentController {

    @Autowired
    private FeePaymentService feePaymentService;

    // Fee payment form
    @GetMapping("/fees")
    public String feeForm(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        // Only ADMIN and CLERK allowed
        if(!(emp.getRole().equals("ADMIN") || emp.getRole().equals("CLERK"))){
            return "redirect:/index";
        }

        model.addAttribute("payment", new FeePayment());

        return "fee_payment";
    }

    // Save fee payment
    @PostMapping("/saveFee")
    public String saveFee(@ModelAttribute FeePayment payment,
                          HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("ADMIN") || emp.getRole().equals("CLERK"))){
            return "redirect:/index";
        }

        feePaymentService.savePayment(payment);

        return "redirect:/feeList";
    }

    // Fee list
    @GetMapping("/feeList")
    public String viewFees(Model model, HttpSession session){

        if(session.getAttribute("user") == null){
            return "redirect:/";
        }

        Employee emp = (Employee) session.getAttribute("user");

        if(!(emp.getRole().equals("ADMIN") || emp.getRole().equals("CLERK"))){
            return "redirect:/index";
        }

        model.addAttribute("fees", feePaymentService.getAllPayments());

        return "fee_list";
    }
}