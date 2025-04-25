package com.book.dockerproject.Services;

import com.book.dockerproject.DTOs.LectureDto;
import com.book.dockerproject.DTOs.StudentLectureCreateDto;
import com.book.dockerproject.DTOs.StudentLectureDto;
import com.book.dockerproject.Exceptions.ResourceNotFoundException;
import com.book.dockerproject.Models.Lecture;
import com.book.dockerproject.Models.Student;
import com.book.dockerproject.Models.StudentLecture;
import com.book.dockerproject.Repositories.StudentLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentLectureService {
    @Autowired
    private StudentLectureRepository repository;

    @Autowired
    private StudentService studentService;

    @Autowired
    private LectureService lectureService;

    StudentLectureDto SLDto = new StudentLectureDto();

    public List<StudentLectureDto> getAll(){
        List<StudentLecture> studentLectures = repository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return studentLectures.stream()
                .map(sl -> SLDto.convertToDTO(sl))
                .collect(Collectors.toList());
    }

    public StudentLectureDto getById(Long id){
        StudentLecture studentLecture = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudentLecture not found with id: " + id));
        return SLDto.convertToDTO(studentLecture);
    }

    public StudentLecture findById(Long id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudentLecture not found with id: " + id));
    }

    public StudentLectureDto save(StudentLectureCreateDto dto){
        Student student = studentService.convertToStudent(studentService.getStudentById(dto.getStudentId()));
        Lecture lecture = lectureService.convertToLecture(lectureService.getLectureById(dto.getLectureId()));

        StudentLecture studentLecture = new StudentLecture();
        studentLecture.setSemester(dto.getSemester());
        studentLecture.setRegister_date(dto.getRegister_date());
        studentLecture.setStudent(student);
        studentLecture.setLecture(lecture);
        repository.save(studentLecture);
        return SLDto.convertToDTO(studentLecture);
    }

    public StudentLectureDto update(Long id, StudentLectureCreateDto dto){
        Student student = studentService.convertToStudent(studentService.getStudentById(dto.getStudentId()));
        Lecture lecture = lectureService.convertToLecture(lectureService.getLectureById(dto.getLectureId()));
            StudentLecture studentLecture = findById(id);
            studentLecture.setSemester(dto.getSemester());
            studentLecture.setRegister_date(dto.getRegister_date());
            studentLecture.setStudent(student);
            studentLecture.setLecture(lecture);
            repository.save(studentLecture);
            return SLDto.convertToDTO(studentLecture);
    }

    public void delete(Long id){
        StudentLecture studentLecture = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudentLecture not found with id: " + id));
        repository.delete(studentLecture);
    }

}
