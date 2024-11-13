package com.api.scm.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {
    @NotBlank(message = "Username is Required")
    @Size(min=3,message="Min 3 Characters is required")
    private String name;
    @Email(message = "Invalid Email Address")
    @NotBlank(message="Email is Required")
    private String email;
    @NotBlank(message="Password is Required")
    @Size(min=6,message = "Min 6 Characters is Required")
    private String password;
    @NotBlank(message = "About is Required")
    private String about;
    @Size(min=8,max=12,message="Invalid PhoneNumber")
    private String phoneNumber;

}
