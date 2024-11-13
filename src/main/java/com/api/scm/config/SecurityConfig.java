package com.api.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.api.scm.services.impl.SecurityCustomUserDetailService;


@Configuration
public class SecurityConfig {
// @Bean
// public UserDetailsService userDetailsService(){
//   UserDetails user1 = User
//   .withDefaultPasswordEncoder()
//   .username("zeeshan")
//   .password("zeeshan12")
//   .roles("ADMIN")
//   .build();
//   UserDetails user2 = User
//   .withDefaultPasswordEncoder()
//   .username("faizan")
//   .password("faizan12")
//   .roles("USER")
//   .build();
//     // so Basically it is storing the username and passowrd in the memory not database to keep in memory when user want to login after sometime
//   var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1,user2);
//   return inMemoryUserDetailsManager;
// }
@Autowired
private SecurityCustomUserDetailService userDetailService;
@Autowired
private OAuthAuthenticationSuccessHandler handler;
@Bean
public DaoAuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailService);
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
}
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

    //Configuration
    httpSecurity.authorizeHttpRequests(authorize->{
        authorize.requestMatchers("/user/**").authenticated();
        authorize.anyRequest().permitAll();
    });
    httpSecurity.formLogin( formLogin->{
        formLogin.loginPage("/login")
        .loginProcessingUrl("/authenticate")
        .successForwardUrl("/user/profile")
        // .failureForwardUrl("/login?error=true")
        .usernameParameter("email")
        .passwordParameter("password");
        
    });
    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    httpSecurity.logout(logoutForm->{
        logoutForm.logoutUrl("/do-logout");
        logoutForm.logoutSuccessUrl("/login?logout=true");
    });
    // oauth2 Configure
    httpSecurity.oauth2Login(oauth ->{
        oauth.loginPage("/login")
        .successHandler(handler);
    });
    
    return httpSecurity.build();
}
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
}
}
