package com.book.dockerproject.Services;

import com.book.dockerproject.DTOs.LectureDto;
import com.book.dockerproject.DTOs.StudentDto;
import com.book.dockerproject.Exceptions.ResourceNotFoundException;
import com.book.dockerproject.Models.Lecture;
import com.book.dockerproject.Models.Student;
import com.book.dockerproject.Repositories.LectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;

    LectureDto lectureDto = new LectureDto();
    public List<LectureDto> getAll(){
        List<Lecture> lectures = lectureRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return lectures.stream()
                .map(l -> lectureDto.convertToDTO(l))
                .collect(Collectors.toList());
    }

    public LectureDto getLectureById(Long lectureId){
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ResourceNotFoundException("Lecture not found with id: " + lectureId));
        return lectureDto.convertToDTO(lecture);
    }

    public Lecture save(Lecture lecture){
        return lectureRepository.save(lecture);
    }

    public LectureDto update(Long lectureId, Lecture lecture){
        Lecture lectureToUpdate = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ResourceNotFoundException("Lecture not found with id: " + lectureId));
        lectureToUpdate.setName(lecture.getName());
        lectureToUpdate.setCredit(lecture.getCredit());
        lectureRepository.save(lectureToUpdate);
        return lectureDto.convertToDTO(lectureToUpdate);
    }

    public void delete(Long lectureId){
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new ResourceNotFoundException("Lecture not found with id: " + lectureId));
        lectureRepository.delete(lecture);
    }

    public Lecture convertToLecture(LectureDto lectureDto) {
        return new Lecture(lectureDto.getId(), lectureDto.getName(), lectureDto.getCredit());
    }
}
