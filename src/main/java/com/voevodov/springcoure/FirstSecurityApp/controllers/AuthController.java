package com.voevodov.springcoure.FirstSecurityApp.controllers;

import com.voevodov.springcoure.FirstSecurityApp.dto.AuthenticationDTO;
import com.voevodov.springcoure.FirstSecurityApp.dto.PersonDTO;
import com.voevodov.springcoure.FirstSecurityApp.model.Person;
import com.voevodov.springcoure.FirstSecurityApp.security.JWTUtil;
import com.voevodov.springcoure.FirstSecurityApp.services.RegistrationService;
import com.voevodov.springcoure.FirstSecurityApp.util.PersonValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;
    private final PersonValidator personValidator;
    private final JWTUtil jwtUtil;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(RegistrationService registrationService, PersonValidator personValidator, JWTUtil jwtUtil, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.registrationService = registrationService;
        this.personValidator = personValidator;
        this.jwtUtil = jwtUtil;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public Map<String, String> performRegistration(@RequestBody @Valid PersonDTO personDTO,
                                                   BindingResult bindingResult) {

        Person person = modelMapper.map(personDTO, Person.class);

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return Map.of("message", "Error!");

        registrationService.register(person);

        String token = jwtUtil.generateToken(person.getUsername());

        return Map.of("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, String> perfomLogin(@RequestBody AuthenticationDTO authenticationDTO){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(), authenticationDTO.getPassword());

        try{
            authenticationManager.authenticate(authenticationToken);
        } catch (BadCredentialsException e) {
            return Map.of("message", "Invalid username or password!");
        }

        String token = jwtUtil.generateToken(authenticationDTO.getUsername());
        return Map.of("jwt-token", token);
    }

    public Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
