package com.book.dockerproject.DTOs;

import com.book.dockerproject.Models.StudentLecture;
import lombok.Data;

import java.util.Date;

@Data
public class StudentLectureCreateDto {
    private Long id;
    private Date register_date;
    private String semester;
    private Long studentId;
    private Long lectureId;

}
