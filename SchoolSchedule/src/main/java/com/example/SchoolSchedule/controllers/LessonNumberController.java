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
public class LessonNumberController {
    private LessonNumberRepository lessonNumberRepository;

    @GetMapping("/lessonNumber")
    public String showLessonNumber(Model model){
        List<LessonNumber> lessonNumbers = lessonNumberRepository.findAll();
        model.addAttribute("lessonNumber", lessonNumbers);
        return "lessonNumber";
    }

    @PostMapping("/addLessonNumber")
    public String addLessonNumber(
            @RequestParam("begin_end_time") String begin_end_time,
            Model model) {
        LessonNumber lessonNumber = new LessonNumber();
        lessonNumber.setBeginEndTime(begin_end_time);
        lessonNumberRepository.save(lessonNumber);
        return "redirect:/lessonNumber";
    }

    @GetMapping("/editLessonNumber")
    public String editLessonNumber(@RequestParam int id, Model model){
        Optional<LessonNumber> optionalLessonNumber = lessonNumberRepository.findById(id);
        if(optionalLessonNumber.isEmpty()){
            return "redirect:/lessonNumber";
        }
        model.addAttribute("lessonNumber", optionalLessonNumber.get());
        return "editLessonNumber";
    }

    @PostMapping("/updateLessonNumber")
    public String updateLessonNumbers(
            @RequestParam int id,
            @RequestParam("begin_end_time") String begin_end_time,
            Model model) {
        Optional<LessonNumber> optionalLessonNumber = lessonNumberRepository.findById(id);
        optionalLessonNumber.ifPresent(ln -> {
            ln.setBeginEndTime(begin_end_time);
            lessonNumberRepository.save(ln);
        });
        return "redirect:/lessonNumber";
    }

}
