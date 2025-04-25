package com.book.dockerproject.Repositories;

import com.book.dockerproject.Models.StudentLecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentLectureRepository extends JpaRepository<StudentLecture, Long> {
}
