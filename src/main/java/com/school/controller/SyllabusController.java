package com.school.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.school.model.Syllabus;
import com.school.service.SyllabusService;

@Controller
public class SyllabusController {

    @Autowired
    private SyllabusService syllabusService;

    @GetMapping("/syllabus")
    public String syllabusForm(Model model) {

        model.addAttribute("syllabus", new Syllabus());

        return "syllabus";
    }

    @PostMapping("/saveSyllabus")
    public String saveSyllabus(@ModelAttribute Syllabus syllabus) {

        syllabusService.saveSyllabus(syllabus);

        return "redirect:/syllabusList";
    }

    @GetMapping("/syllabusList")
    public String syllabusList(Model model) {

        model.addAttribute("syllabusList", syllabusService.getAllSyllabus());

        return "syllabus_list";
    }
}