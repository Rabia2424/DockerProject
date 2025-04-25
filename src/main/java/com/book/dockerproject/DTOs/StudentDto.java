package com.book.dockerproject.DTOs;

import com.book.dockerproject.Models.Student;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class StudentDto {
    private Long id;
    private String name;
    private String surname;
    private List<String> lectureNames;

    public StudentDto convertToDTO(Student student) {
        StudentDto dto = new StudentDto();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setSurname(student.getSurname());

        List<String> lectureNames = student.getStudentLectures()
                .stream()
                .map(sl -> sl.getLecture().getName())
                .collect(Collectors.toList());
        dto.setLectureNames(lectureNames);

        return dto;
    }
}

