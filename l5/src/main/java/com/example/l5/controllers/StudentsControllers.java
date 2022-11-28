package com.example.l5.controllers;

import com.example.l5.converters.StudentsConverter;
import com.example.l5.dto.StudentDto;
import com.example.l5.entitys.Student;
import com.example.l5.exceptions.ResourceNotFoundException;
import com.example.l5.services.StudentsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsControllers {

    private final StudentsServices studentsServices;
    private final StudentsConverter studentsConverter;


    @GetMapping("/init")
    public void initStudents() {
        studentsServices.init();
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentsServices.findAll().stream().map(studentsConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        Student student = studentsServices.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found, id: " + id));
        return studentsConverter.entityToDto(student);
    }

    @PostMapping
    public StudentDto saveNewStudent(@RequestBody StudentDto studentDto) {
        Student student = studentsConverter.dtoToEntity(studentDto);
        student = studentsServices.save(student);
        return studentsConverter.entityToDto(student);
    }

    @PutMapping
    public StudentDto supdateStudent(@RequestBody StudentDto studentDto) {
        Student student = studentsServices.update(studentDto);
        return studentsConverter.entityToDto(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentsServices.deleteById(id);
    }
}
