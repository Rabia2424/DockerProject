package com.book.dockerproject.Repositories;
import com.book.dockerproject.Models.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
        @Modifying
        @Transactional
        @Query(value="delete from Student u where u.id = :id")
        void deleteById(Long id);
}
