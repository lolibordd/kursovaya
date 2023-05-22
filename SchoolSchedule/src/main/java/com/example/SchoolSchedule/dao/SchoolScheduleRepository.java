package com.example.SchoolSchedule.dao;

import com.example.SchoolSchedule.entities.SchoolSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolScheduleRepository extends JpaRepository<SchoolSchedule, Integer> {
}