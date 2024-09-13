package com.spring.security.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.spring.security.security.filter.JwtFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain SecuritytFilterChain(HttpSecurity http) throws Exception{
        http.csrf(custome -> custome.disable());
        http.authorizeHttpRequests(request -> request.requestMatchers("/api/user/register","/api/user/login").permitAll());
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        // http.httpBasic(Customizer.withDefaults());
        // http.formLogin(Customizer.withDefaults());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails user1 = User
    //                             .withDefaultPasswordEncoder()
    //                             .username("henok")
    //                             .password("password")
    //                             .roles("ADMIN")
    //                             .build();
    //     UserDetails user2 = User
    //                             .withDefaultPasswordEncoder()
    //                             .username("aster")
    //                             .password("wolda")
    //                             .roles("USER")
    //                             .build();
    //     return new InMemoryUserDetailsManager(user1,user2);
    // }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager AuthenticationManger(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }
}
