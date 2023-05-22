package com.example.SchoolSchedule.controllers;

import com.example.SchoolSchedule.dao.*;
import com.example.SchoolSchedule.entities.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class TeacherController {
    private TeacherRepository teacherRepository;

    @GetMapping("/teacher")
    public String showTeacher(Model model){
        List<Teacher> teachers = teacherRepository.findAll();
        model.addAttribute("teacher", teachers);
        return "teacher";
    }

    @PostMapping("/addTeacher")
    public String addTeacher(
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("subjects") String subjects,
            @RequestParam("contact_info") String contact_info,
            Model model) {
        Teacher teacher = new Teacher();
        teacher.setName(name);
        teacher.setSurname(surname);
        teacher.setPatronymic(patronymic);
        teacher.setSubjects(subjects);
        teacher.setContactInfo(contact_info);
        teacherRepository.save(teacher);
        return "redirect:/teacher";
    }
    @GetMapping("/deleteTeacher")
    public String deleteTeacher(@RequestParam int id) {
        teacherRepository.deleteById(id);
        return "redirect:/teacher";
    }

    @GetMapping("/editTeacher")
    public String editTeacher(@RequestParam int id, Model model){
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if(optionalTeacher.isEmpty()){
            return "redirect:/teacher";
        }
        model.addAttribute("teacher", optionalTeacher.get());
        return "editTeacher";
    }

    @PostMapping("/updateTeacher")
    public String updateTeacher(
            @RequestParam int id,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("patronymic") String patronymic,
            @RequestParam("subjects") String subjects,
            @RequestParam("contact_info") String contact_info,
            Model model) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        optionalTeacher.ifPresent(t -> {
            t.setName(name);
            t.setSurname(surname);
            t.setPatronymic(patronymic);
            t.setSubjects(subjects);
            t.setContactInfo(contact_info);
            teacherRepository.save(t);
        });
        return "redirect:/teacher";
    }

}
