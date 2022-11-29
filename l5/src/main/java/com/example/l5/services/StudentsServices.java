package com.example.l5.services;

import com.example.l5.dto.StudentDto;
import com.example.l5.entitys.Student;
import com.example.l5.exceptions.ResourceNotFoundException;
import com.example.l5.repositorys.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class StudentsServices {

    private final StudentsRepository studentsRepository;

    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return studentsRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    @Transactional
    public Student save(Student student) {
        return studentsRepository.save(student);
    }

    @Transactional
    public Student update(StudentDto studentDto) {
        Student student = studentsRepository.findById(studentDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Not found"));
        student.setName(studentDto.getName());
        student.setMark(studentDto.getMark());
        return student;
    }

    @Transactional
    public void deleteById(Long id) {
        studentsRepository.deleteById(id);
    }

    public void init() {
        String name = "student";

        for (int i = 0; i < 1000; i++) {
            Student student = new Student();
            student.setName(name + i);
            student.setMark(randomMark());
            studentsRepository.save(student);
        }
    }

    private int randomMark() {
        Random random = new Random();
        return random.nextInt(5);
    }

}
