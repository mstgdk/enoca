package com.enoca.DTO.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @Size(max=50)
    @NotBlank(message="Please provide your First Name")
    private String name;

    @Size(min=10 ,max=80)
    @Email(message = "Please provide valid e-mail")
    private String email;

    @Size(min=4, max=20, message="Please provide Correct Size of Password")
    @NotBlank(message="Please provide your password")
    private String password;
}
