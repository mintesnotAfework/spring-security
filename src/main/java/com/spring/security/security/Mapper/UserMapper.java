package com.spring.security.security.Mapper;

import com.spring.security.security.dto.UserDto;
import com.spring.security.security.model.Users;

public class UserMapper {

    public static Users mapToUser(UserDto userDto){
        Users user = new Users();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return user;
    }
    public static UserDto mapToUserDto(Users user){
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        return userDto;
    }

}
