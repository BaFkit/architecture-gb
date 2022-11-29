package com.example.l5.converters;

import com.example.l5.dto.StudentDto;
import com.example.l5.entitys.Student;

public class StudentsConverter {
    public Student dtoToEntity(StudentDto studentDto) {
        return new Student(studentDto.getId(), studentDto.getName(), studentDto.getMark());
    }

    public StudentDto entityToDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getMark());
    }
}
