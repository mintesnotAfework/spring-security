package com.spring.security.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.security.security.service.StudentService;
import com.spring.security.security.dto.StudentDto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<List<StudentDto>> getStudents() {
        try{
            return new ResponseEntity<>(studentService.getStudents(),HttpStatus.OK); 
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto) {
        try{
            return new ResponseEntity<StudentDto>(studentService.addStudent(studentDto),HttpStatus.CREATED); 
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentd(@PathVariable Long id) {
        try{
            return new ResponseEntity<StudentDto>(studentService.getStudent(id),HttpStatus.OK); 
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> putStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        try{
            return new ResponseEntity<StudentDto>(studentService.updateStudent(id, studentDto),HttpStatus.CREATED); 
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable Long id){
        studentService.deleteStudent(id);
        return new ResponseEntity<Boolean>(HttpStatus.OK);
    }

    @DeleteMapping("/all")
    public ResponseEntity<Boolean> deleteStudents(){
        studentService.deleteStudents();
        return new ResponseEntity<Boolean>(HttpStatus.OK);
    }

}
