package com.authentication.service.authenticationService.controller;

import com.authentication.service.authenticationService.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome";
    }


    @GetMapping("/get-token/{id}")
    public String createToken(@PathVariable("id") int userId){
        return tokenService.createToken(userId);
    }

}
