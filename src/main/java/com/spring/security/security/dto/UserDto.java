package com.spring.security.security.dto;

import org.springframework.stereotype.Component;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDto {
    private Long id;
    private String username;
    private String password;

    public String toString(){
        return this.username + " " + this.password;
    }

}
