package com.example.SchoolSchedule.dao;

import com.example.SchoolSchedule.entities.DayOfTheWeek;
import com.example.SchoolSchedule.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    Subject getSubjectById(int subject_id);
}