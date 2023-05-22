package com.example.SchoolSchedule.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "lesson_number")
public class LessonNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Lob
    @Column(name = "begin_end_time", nullable = false)
    private String beginEndTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBeginEndTime() {
        return beginEndTime;
    }

    public void setBeginEndTime(String beginEndTime) {
        this.beginEndTime = beginEndTime;
    }

}
