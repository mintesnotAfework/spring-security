package com.spring.security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.security.security.model.UserPrinciple;
import com.spring.security.security.model.Users;
import com.spring.security.security.repository.UsersRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = usersRepository.findByUsername(username);
        if ( user == null){
            throw new UsernameNotFoundException("username not found");
        }
        return new UserPrinciple(user);
    }
}
