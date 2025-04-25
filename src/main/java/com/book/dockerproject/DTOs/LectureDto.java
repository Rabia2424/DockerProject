package com.book.dockerproject.DTOs;

import com.book.dockerproject.Models.Lecture;
import com.book.dockerproject.Models.Student;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class LectureDto {
    private Long id;
    private String name;
    private int credit;
    private int studentCount;

    public LectureDto convertToDTO(Lecture lecture) {
        LectureDto dto = new LectureDto();
        dto.setId(lecture.getId());
        dto.setName(lecture.getName());
        dto.setCredit(lecture.getCredit());

        dto.setStudentCount(lecture.getStudentLectures().size());

        return dto;
    }
}

