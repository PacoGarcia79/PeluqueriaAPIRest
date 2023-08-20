package com.spring.login;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@SpringBootTest
public class TestConfiguration {

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user1 = User.builder()
                .username("empleado")
                .password("123456") // La contraseña encriptada si es necesario
                .roles("EMPLEADO")
                .build();

        UserDetails user2 = User.builder()
                .username("cliente")
                .password("123456")
                .roles("CLIENTE")
                .build();

        return new InMemoryUserDetailsManager(user1, user2);
    }
}
