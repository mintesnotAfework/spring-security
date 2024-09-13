package com.spring.security.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.security.security.dto.StudentDto;
import com.spring.security.security.model.Student;
import com.spring.security.security.repository.StudentRepository;
import com.spring.security.security.Mapper.StudentMapper;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<StudentDto> getStudents(){
        List<Student> students = this.studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (Student s : students){
            studentDtos.add(StudentMapper.mapToStudentDto(s));
        }
        return studentDtos;
    }

    public StudentDto getStudent(Long id){
        Student student = this.studentRepository.findById(id).orElseThrow();
        return StudentMapper.mapToStudentDto(student);
    }

    public StudentDto addStudent(StudentDto studentDto){
        Student student = this.studentRepository.save(StudentMapper.mapToStudent(studentDto));
        return StudentMapper.mapToStudentDto(student);

    }

    public StudentDto updateStudent(Long id, StudentDto studentDto){
        studentDto.setId(id);
        Student student = this.studentRepository.save(StudentMapper.mapToStudent(studentDto));
        return StudentMapper.mapToStudentDto(student);
    }

    public void deleteStudent(Long id){
        this.studentRepository.deleteById(id);
    }

    public void deleteStudents(){
        this.studentRepository.deleteAll();
    }
}
