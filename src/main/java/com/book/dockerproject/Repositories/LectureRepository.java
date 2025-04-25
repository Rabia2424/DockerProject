package com.book.dockerproject.Repositories;

import com.book.dockerproject.Models.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
