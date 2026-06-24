package com.school.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.model.Syllabus;
import com.school.repository.SyllabusRepository;

@Service
public class SyllabusService {

    @Autowired
    private SyllabusRepository syllabusRepository;

    public void saveSyllabus(Syllabus syllabus) {
        syllabusRepository.save(syllabus);
    }

    public List<Syllabus> getAllSyllabus() {
        return syllabusRepository.findAll();
    }
}