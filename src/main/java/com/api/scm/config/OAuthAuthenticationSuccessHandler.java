package com.api.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.api.scm.entities.Providers;
import com.api.scm.entities.User;
import com.api.scm.helpers.AppConstants;
import com.api.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
     private UserRepo userRepo;
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
       logger.info("OAuthAuthenticationSuccesHandler");

       DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
       logger.info(user.getName());
       user.getAttributes().forEach((key,value)->{
        logger.info("{} : {}",key,value);
       });
       logger.info(user.getAuthorities().toString());

       // Data stored in Database
       String email = user.getAttribute("email").toString();
       String name = user.getAttribute("name").toString();
       String picture = user.getAttribute("picture").toString();

       // create user and save the user in database
       User user1 = new User();
       user1.setEmail(email);
       user1.setName(name);
       user1.setProfilePic(picture);
       user1.setPassword("password");
       user1.setProvider(Providers.GOOGLE);
       user1.setEnabled(true);
       user1.setAbout("This is Google Signup Testing");
       user1.setEmailVerified(true);
       user1.setPhoneNumber("7488123305");
       user1.setPhoneVerified(true);
       user1.setUserId(UUID.randomUUID().toString());
       user1.setRoleList(List.of(AppConstants.ROLE_USER));
       user1.setProviderUserId(user.getName());
      
      User user2 = userRepo.findByEmail(email).orElse(null);
      if(user2 ==null){
        userRepo.save(user1);
        logger.info("User Saved Successfully !" + email);
      }
     
       
     
       new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");


        
    }

}
