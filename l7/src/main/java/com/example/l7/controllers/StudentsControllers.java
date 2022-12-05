package com.example.l7.controllers;

import com.example.l7.converters.StudentsConverter;
import com.example.l7.dto.StudentDto;
import com.example.l7.entitys.Student;
import com.example.l7.exceptions.ResourceNotFoundException;
import com.example.l7.services.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentsControllers {

    private final StudentsService studentsService;
    private final StudentsConverter studentsConverter;

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentsService.findAll().stream().map(studentsConverter::entityToDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public StudentDto getStudentById(@PathVariable Long id) {
        Student student = studentsService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student not found, id: " + id));
        return studentsConverter.entityToDto(student);
    }

    @PostMapping
    public StudentDto saveNewStudent(@RequestBody StudentDto studentDto) {
        Student student = studentsConverter.dtoToEntity(studentDto);
        student = studentsService.save(student);
        return studentsConverter.entityToDto(student);
    }

    @PutMapping
    public StudentDto updateStudent(@RequestBody StudentDto studentDto) {
        Student student = studentsService.update(studentDto);
        return studentsConverter.entityToDto(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentsService.deleteById(id);
    }
}
