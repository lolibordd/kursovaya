package com.example.SchoolSchedule.dao;

import com.example.SchoolSchedule.entities.LessonNumber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonNumberRepository extends JpaRepository<LessonNumber, Integer> {
    LessonNumber getLessonNumberById(int lessonNumber_id);
}