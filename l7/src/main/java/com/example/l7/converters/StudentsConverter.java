package com.example.l7.converters;

import com.example.l7.dto.StudentDto;
import com.example.l7.entitys.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentsConverter {
    public Student dtoToEntity(StudentDto studentDto) {
        return new Student(studentDto.getId(), studentDto.getName(), studentDto.getAge());
    }

    public StudentDto entityToDto(Student student) {
        return new StudentDto(student.getId(), student.getName(), student.getAge());
    }
}
