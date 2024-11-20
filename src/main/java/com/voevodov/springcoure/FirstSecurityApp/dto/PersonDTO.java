package com.voevodov.springcoure.FirstSecurityApp.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonDTO {

    @NotEmpty(message = "The name should not be blank")
    @Size(min = 2, max = 100, message = "The name should be between 2 and 100 characters long")
    private String username;

    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    private int yearOfBirth;


    private String password;

    public @NotEmpty(message = "The name should not be blank") String getUsername() {
        return username;
    }

    public void setUsername(  String username) {
        this.username = username;
    }

    @Min(value = 1900, message = "Year of birth must be greater than 1900")
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth( int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
