package com.spring.security.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.spring.security.security.dto.UserDto;
import com.spring.security.security.service.UserService;

import java.util.List;
import java.util.NoSuchElementException;
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
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> all() {
        return new ResponseEntity<List<UserDto>>(userService.all(),HttpStatus.OK);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {
        return userService.verify(userDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        try{
            return new ResponseEntity<UserDto>(userService.getUser(id),HttpStatus.OK);
        }
        catch(NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.addUser(userDto),HttpStatus.CREATED);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.updateUser(id, userDto),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
