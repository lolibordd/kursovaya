package com.example.SchoolSchedule.dao;

import com.example.SchoolSchedule.entities.DayOfTheWeek;
import com.example.SchoolSchedule.entities.LessonNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DayOfTheWeekRepository extends JpaRepository<DayOfTheWeek, Integer> {
    DayOfTheWeek getDayOfTheWeekById(int dayOfTheWeek_id);
}