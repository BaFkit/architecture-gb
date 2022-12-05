package com.example.l7.repositorys;

import com.example.l7.entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Long> {
}
