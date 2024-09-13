package com.spring.security.security.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.spring.security.security.Mapper.UserMapper;
import com.spring.security.security.dto.UserDto;
import com.spring.security.security.model.Users;
import com.spring.security.security.repository.UsersRepository;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

@Service
public class UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserDto getUser(Long id) {
        return UserMapper.mapToUserDto(usersRepository.findById(id).orElseThrow());
    }

    public UserDto updateUser(Long id,UserDto userDto){
        userDto.setId(id);
        Users savedUser = usersRepository.save(UserMapper.mapToUser(userDto));
        return UserMapper.mapToUserDto(savedUser);
    }

    public UserDto addUser(UserDto userDto){
        userDto.setPassword(encoder.encode(userDto.getPassword()));
        Users savedUser = usersRepository.save(UserMapper.mapToUser(userDto));
        return UserMapper.mapToUserDto(savedUser);
    }

    public void deleteUser(Long id){
        usersRepository.deleteById(id);
    }

    public List<UserDto> all() {
        List<Users> users = usersRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for ( Users u :users){
            userDtos.add(UserMapper.mapToUserDto(u));
        }
        return userDtos;
    }

    public String verify(UserDto userDto){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userDto.getUsername());
        }
        throw new AuthenticationCredentialsNotFoundException("authetication failed");
    }
}
