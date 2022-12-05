package com.example.l7.services;

import com.example.l7.dto.StudentDto;
import com.example.l7.entitys.Student;
import com.example.l7.exceptions.ResourceNotFoundException;
import com.example.l7.repositorys.StudentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentsService {

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
        student.setAge(studentDto.getAge());
        return student;
    }

    @Transactional
    public void deleteById(Long id) {
        studentsRepository.deleteById(id);
    }
}
