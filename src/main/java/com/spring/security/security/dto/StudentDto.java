package com.spring.security.security.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String name;
    private String email;
    private int age; 

    public String toString(){
        return this.name + " " + this.age + " " + this.email;
    }

}
