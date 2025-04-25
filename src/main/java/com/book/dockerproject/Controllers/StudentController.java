package com.book.dockerproject.Controllers;
import com.book.dockerproject.DTOs.StudentDto;
import com.book.dockerproject.Models.Student;
import com.book.dockerproject.Repositories.StudentRepository;
import com.book.dockerproject.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<StudentDto>> getAll(){
        List<StudentDto> students = studentService.getAll();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            StudentDto student = studentService.getStudentById(id);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody Student student){
        try {
            Student newStudent = studentService.save(student);
            return ResponseEntity.status(HttpStatus.CREATED).body(newStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student could not be added!");
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> studentUpdate(@PathVariable Long id, @RequestBody Student student){
        try {
            StudentDto updatedStudent = studentService.update(id, student);
            return ResponseEntity.ok(updatedStudent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> studentDelete(@PathVariable Long id){
        try {
            studentService.delete(id);
            return ResponseEntity.ok("Student deleted succcessfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
