package com.example.l5.repositorys;

import com.example.l5.entitys.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRepository extends JpaRepository<Student, Long> {
}
