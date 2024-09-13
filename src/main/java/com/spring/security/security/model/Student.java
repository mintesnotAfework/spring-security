






























package com.spring.security.security.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Table(name = "student")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ctry_seq")
    @SequenceGenerator(name = "ctry_seq", sequenceName = "ctry_seq", initialValue = 1, allocationSize=1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "email")
    private String email;

    public String toString(){
        return this.name + " " + this.age + " " + this.email;
    }
}
