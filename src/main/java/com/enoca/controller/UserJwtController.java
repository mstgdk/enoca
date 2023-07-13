package com.enoca.controller;

import com.enoca.DTO.messages.ResponseMessage;
import com.enoca.DTO.request.RegisterRequest;
import com.enoca.DTO.request.SignInRequest;
import com.enoca.DTO.response.EnocaResponse;
import com.enoca.DTO.response.SignInResponse;
import com.enoca.security.jwt.JwtUtils;
import com.enoca.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController

public class UserJwtController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/register")
    public ResponseEntity<EnocaResponse> registerUser(@Valid
                                                   @RequestBody RegisterRequest registerRequest) {
        userService.saveUser(registerRequest);

        EnocaResponse response = new EnocaResponse();
        response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // !!! SIGNIN
    @PostMapping("/signin")
    public ResponseEntity<SignInResponse> authenticate(@Valid @RequestBody SignInRequest signInRequest){

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(signInRequest.getEmail(), signInRequest.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // !!! Kullanıcı bu aşamada valide edildi ve token üretimine geçiliyor
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateJwtToken(userDetails);

        // !!! JWT Token client tarafına gönderiliyor
        SignInResponse signInResponse= new SignInResponse(jwtToken);

        return new ResponseEntity<>(signInResponse,HttpStatus.OK);

    }
}
