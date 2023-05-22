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
public class SchoolScheduleController {

    private SchoolScheduleRepository schoolScheduleRepository;
    private DayOfTheWeekRepository dayOfTheWeekRepository;
    private LessonNumberRepository lessonNumberRepository;
    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;

    @GetMapping("/lesson")
    public String showLesson(Model model){
        List<SchoolSchedule> lessons = schoolScheduleRepository.findAll();
        model.addAttribute("lesson", lessons);
        return "lesson";
    }

    @PostMapping("/addLesson")
    public String addLesson(
            @RequestParam("dayOfTheWeek_id") Integer dayOfTheWeek_id,
            @RequestParam("lessonNumber_id") Integer lessonNumber_id,
            @RequestParam("grade") String grade,
            @RequestParam("subject_id") Integer subject_id,
            @RequestParam("teacher_id") Integer teacher_id,
            @RequestParam("classroom") String classroom,
            Model model) {
        DayOfTheWeek dayOfTheWeek = dayOfTheWeekRepository.getDayOfTheWeekById(dayOfTheWeek_id);
        LessonNumber lessonNumber = lessonNumberRepository.getLessonNumberById(lessonNumber_id);
        Subject subject = subjectRepository.getSubjectById(subject_id);
        Teacher teacher = teacherRepository.getTeacherById(teacher_id);
        SchoolSchedule schoolSchedule = new SchoolSchedule();
        schoolSchedule.setDayOfTheWeek(dayOfTheWeek);
        schoolSchedule.setLessonNumber(lessonNumber);
        schoolSchedule.setGrade(grade);
        schoolSchedule.setSubject(subject);
        schoolSchedule.setTeacher(teacher);
        schoolSchedule.setClassroom(classroom);
        schoolScheduleRepository.save(schoolSchedule);
        return "redirect:/lesson";
    }
    @GetMapping("/deleteLesson")
    public String deleteLesson(@RequestParam int id) {
        schoolScheduleRepository.deleteById(id);
        return "redirect:/lesson";
    }

    @GetMapping("/editLesson")
    public String editLesson(@RequestParam int id, Model model){
        Optional<SchoolSchedule> optionalSchoolSchedule = schoolScheduleRepository.findById(id);
        if(optionalSchoolSchedule.isEmpty()){
            return "redirect:/lesson";
        }
        model.addAttribute("lessons", optionalSchoolSchedule.get());
        return "editLesson";
    }

    @PostMapping("/updateLesson")
    public String updateLesson(
            @RequestParam int id,
            @RequestParam("dayOfTheWeek_id") Integer dayOfTheWeek_id,
            @RequestParam("lessonNumber_id") Integer lessonNumber_id,
            @RequestParam("grade") String grade,
            @RequestParam("subject_id") Integer subject_id,
            @RequestParam("teacher_id") Integer teacher_id,
            @RequestParam("classroom") String classroom,
            Model model) {
        DayOfTheWeek dayOfTheWeek = dayOfTheWeekRepository.getDayOfTheWeekById(dayOfTheWeek_id);
        LessonNumber lessonNumber = lessonNumberRepository.getLessonNumberById(lessonNumber_id);
        Subject subject = subjectRepository.getSubjectById(subject_id);
        Teacher teacher = teacherRepository.getTeacherById(teacher_id);
        Optional<SchoolSchedule> optionalSchoolSchedule = schoolScheduleRepository.findById(id);
        optionalSchoolSchedule.ifPresent(ss -> {
            ss.setDayOfTheWeek(dayOfTheWeek);
            ss.setLessonNumber(lessonNumber);
            ss.setGrade(grade);
            ss.setSubject(subject);
            ss.setTeacher(teacher);
            ss.setClassroom(classroom);
            schoolScheduleRepository.save(ss);
        });
        return "redirect:/lesson";
    }

}
