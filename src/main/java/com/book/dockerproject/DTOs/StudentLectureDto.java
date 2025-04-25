package com.book.dockerproject.DTOs;
import com.book.dockerproject.Models.StudentLecture;
import lombok.Data;

import java.util.Date;

@Data
public class StudentLectureDto {
    private Long id;
    private Date register_date;
    private String semester;
    private String studentName;
    private String lectureName;

    public StudentLectureDto convertToDTO(StudentLecture studentLecture) {
        StudentLectureDto dto = new StudentLectureDto();
        dto.setId(studentLecture.getId());
        dto.setSemester(studentLecture.getSemester());
        dto.setRegister_date(studentLecture.getRegister_date());

        dto.setStudentName(studentLecture.getStudent().getName() + " " + studentLecture.getStudent().getSurname());
        dto.setLectureName(studentLecture.getLecture().getName());

        return dto;
    }
}
