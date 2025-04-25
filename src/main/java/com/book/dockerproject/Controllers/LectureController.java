package com.book.dockerproject.Controllers;
import com.book.dockerproject.DTOs.LectureDto;
import com.book.dockerproject.Models.Lecture;
import com.book.dockerproject.Models.Student;
import com.book.dockerproject.Repositories.LectureRepository;
import com.book.dockerproject.Services.LectureService;
import com.book.dockerproject.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lectures")
public class LectureController {
    @Autowired
    private LectureService lectureService;

    @GetMapping()
    public ResponseEntity<List<LectureDto>> getAll(){
        List<LectureDto> lectures = lectureService.getAll();
        return new ResponseEntity<>(lectures, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id){
        try {
            LectureDto lecture = lectureService.getLectureById(id);
            return ResponseEntity.ok(lecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity<?> addLecture(@RequestBody Lecture lecture){
        try {
            Lecture newLecture = lectureService.save(lecture);
            return ResponseEntity.status(HttpStatus.CREATED).body(newLecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lecture could not be added!");
        }
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> lectureUpdate(@PathVariable Long id, @RequestBody Lecture lecture){
        try {
            LectureDto updatedLecture = lectureService.update(id, lecture);
            return ResponseEntity.ok(updatedLecture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<?> lectureDelete(@PathVariable Long id){
        try {
            lectureService.delete(id);
            return ResponseEntity.ok("Lecture deleted succcessfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
