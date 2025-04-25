package com.book.dockerproject.Services;
import com.book.dockerproject.DTOs.StudentDto;
import com.book.dockerproject.Exceptions.ResourceNotFoundException;
import com.book.dockerproject.Models.Student;
import com.book.dockerproject.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    StudentDto studentDto = new StudentDto();

    public List<StudentDto> getAll(){
        List<Student> students = studentRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return students.stream()
                .map(s -> studentDto.convertToDTO(s))
                .collect(Collectors.toList());
    }

    public StudentDto getStudentById(Long studentId){
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        return studentDto.convertToDTO(student);
    }

    public Student save(Student student){
        return studentRepository.save(student);
    }

    public StudentDto update(Long studentId, Student student){
        Student studentToUpdate = studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + studentId));
        studentToUpdate.setName(student.getName());
        studentToUpdate.setSurname(student.getSurname());
        studentRepository.save(studentToUpdate);
        return studentDto.convertToDTO(studentToUpdate);
    }

    public void delete(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.delete(student);
    }

    public Student convertToStudent(StudentDto studentDto) {
        return new Student(studentDto.getId(), studentDto.getName(), studentDto.getSurname());
    }
}
