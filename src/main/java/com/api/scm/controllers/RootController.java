package com.api.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.api.scm.entities.User;
import com.api.scm.helpers.Helper;
import com.api.scm.services.UserService;
@ControllerAdvice
public class RootController {
    private Logger logger = LoggerFactory.getLogger(UserController.class);
@Autowired
private UserService userService;
@ModelAttribute
public void addLoggedInUserInformation(Model model,Authentication authentication){
    if(authentication==null){
        return;
    }
    System.out.println("Adding Logged In User Information to the model.");
    String username = Helper.getEmailOfLoggedInUser(authentication);
    logger.info("User Logged in {} : ",username);
    User user = userService.getUserByEmail(username);
   
    System.out.println(user.getName());
    System.out.println(user.getEmail());
    System.out.println(user.getPhoneNumber());
    model.addAttribute("loggedInUser", user);

}

}
