package com.book.dockerproject.Controllers;
import com.book.dockerproject.DTOs.StudentLectureCreateDto;
import com.book.dockerproject.DTOs.StudentLectureDto;
import com.book.dockerproject.Models.StudentLecture;
import com.book.dockerproject.Services.StudentLectureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/registers")
public class StudentLectureController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private StudentLectureService studentLectureService;

    @GetMapping()
    public ResponseEntity<?> showRegisterList() {
        List<StudentLectureDto> registrations = studentLectureService.getAll();
        return ResponseEntity.ok(registrations);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            StudentLectureDto studentLecture = studentLectureService.getById(id);
            return ResponseEntity.ok(studentLecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRegister(@RequestBody StudentLectureCreateDto dto){
        try {
            StudentLectureDto newStudentLecture = studentLectureService.save(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(newStudentLecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student Lecture could not be added!");
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> registerUpdate(@PathVariable Long id, @RequestBody StudentLectureCreateDto dto){
        try {
            StudentLectureDto updatedStudentLecture = studentLectureService.update(id, dto);
            return ResponseEntity.ok(updatedStudentLecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> registerDelete(@PathVariable Long id){
        try {
            studentLectureService.delete(id);
            return ResponseEntity.ok("Student Lecture deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

