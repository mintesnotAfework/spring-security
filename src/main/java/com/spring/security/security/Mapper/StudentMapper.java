package com.spring.security.security.Mapper;

import com.spring.security.security.dto.StudentDto;
import com.spring.security.security.model.Student;

public class StudentMapper {
    public static Student mapToStudent(StudentDto studentDto){
        Student student = new Student();
        student.setAge(studentDto.getAge());
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setId(studentDto.getId());

        return student;
    }
    public static StudentDto mapToStudentDto(Student student){
        StudentDto studentDto = new StudentDto();
        studentDto.setAge(student.getAge());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setId(student.getId());

        return studentDto;
    }
}
