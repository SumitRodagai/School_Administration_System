package com.school.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.school.model.Performance;
import com.school.service.PerformanceService;
import com.school.service.SyllabusService;

@Controller
public class PerformanceController {

    @Autowired
    private PerformanceService performanceService;

    @Autowired
    private SyllabusService syllabusService;

    // Performance form
    @GetMapping("/performance")
    public String performanceForm(Model model) {

        model.addAttribute("subjects", syllabusService.getAllSyllabus());

        return "performance";
    }

    // Save all marks
    @PostMapping("/savePerformance")
    public String savePerformance(
            @RequestParam("studentId") Long studentId,
            @RequestParam("subject") List<String> subjects,
            @RequestParam("marks") List<Integer> marks) {

        for (int i = 0; i < subjects.size(); i++) {

            Performance p = new Performance();

            p.setStudentId(studentId);
            p.setSubject(subjects.get(i));
            p.setMarks(marks.get(i));

            performanceService.savePerformance(p);
        }

        return "redirect:/performanceList";
    }

    // Performance list
    @GetMapping("/performanceList")
    public String performanceList(@RequestParam(required = false) Long studentId,
                                  Model model) {

        if(studentId == null){
            return "performance_list";
        }

        List<Performance> list = performanceService.getPerformanceByStudent(studentId);

        int totalObtained = 0;

        for(Performance p : list){
            totalObtained += p.getMarks();
        }

        int totalMarks = list.size() * 100;

        double percentage = 0;

        if(totalMarks != 0){
            percentage = (double) totalObtained / totalMarks * 100;
        }

        model.addAttribute("performanceList", list);
        model.addAttribute("totalObtained", totalObtained);
        model.addAttribute("totalMarks", totalMarks);
        model.addAttribute("percentage", percentage);

        return "performance_list";
    }
    
    
 // EDIT PAGE
    @GetMapping("/editPerformance/{id}")
    public String editPerformance(@PathVariable Long id, Model model){

        Performance performance = performanceService.getPerformanceById(id);

        model.addAttribute("performance", performance);

        return "edit_performance";
    }


    // UPDATE
    @PostMapping("/updatePerformance")
    public String updatePerformance(@ModelAttribute Performance performance){

        performanceService.savePerformance(performance);

        return "redirect:/performanceList?studentId=" + performance.getStudentId();
    }


    // DELETE
    @GetMapping("/deletePerformance/{id}")
    public String deletePerformance(@PathVariable Long id){

        Performance p = performanceService.getPerformanceById(id);

        Long studentId = p.getStudentId();

        performanceService.deletePerformance(id);

        return "redirect:/performanceList?studentId=" + studentId;
    }
    // Select report page
    @GetMapping("/performanceReport")
    public String performanceReport() {

        return "performance_report";
    }

    // Show report for student
    @GetMapping("/viewReport")
    public String viewReport(@RequestParam("studentId") Long studentId, Model model) {

        List<Performance> list = performanceService.getPerformanceByStudent(studentId);

        int totalObtained = 0;

        for (Performance p : list) {
            totalObtained += p.getMarks();
        }

        int totalMarks = list.size() * 100;

        double percentage = 0;

        if (totalMarks != 0) {
            percentage = (double) totalObtained / totalMarks * 100;
        }

        model.addAttribute("performanceList", list);
        model.addAttribute("studentId", studentId);
        model.addAttribute("totalObtained", totalObtained);
        model.addAttribute("totalMarks", totalMarks);
        model.addAttribute("percentage", percentage);

        return "performance_list";
    }
    
    
}