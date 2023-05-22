package com.example.SchoolSchedule.dao;

import com.example.SchoolSchedule.entities.Subject;
import com.example.SchoolSchedule.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher getTeacherById(int teacher_id);
}