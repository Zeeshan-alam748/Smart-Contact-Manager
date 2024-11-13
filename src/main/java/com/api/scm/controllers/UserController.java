package com.api.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;


import com.api.scm.entities.User;
import com.api.scm.helpers.Helper;
import com.api.scm.services.UserService;



@Controller
@RequestMapping("/user")
public class UserController {


    // user dashboard page
@RequestMapping(value="/dashboard")
public String userDashboard() {
    
    return "user/dashboard";
}
 // user Profile page
 @RequestMapping(value="/profile")
 public String userProfile() {
  
     return "user/profile";
 }
}