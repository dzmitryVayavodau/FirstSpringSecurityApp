package com.voevodov.springcoure.FirstSecurityApp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Name shouldn't be empty")
    @Size(min = 2, max = 100, message = "Name should be more than 1 and less than 101 characters")
    @Column(name = "username")
    private String username;

    @Min(value = 1900, message = "Year of birth should be more 1900")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "password")
    private String password;

    public Person() {
    }

    public Person(String userName, int yearOfBirth) {
        this.username = userName;
        this.yearOfBirth = yearOfBirth;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Name shouldn't be empty") @Size(min = 2, max = 100, message = "Name should be more than 1 and less than 101 characters") String getUserName() {
        return username;
    }

    public void setUserName(@NotEmpty(message = "Name shouldn't be empty") @Size(min = 2, max = 100, message = "Name should be more than 1 and less than 101 characters") String userName) {
        this.username = userName;
    }

    @Min(value = 1900, message = "Year of birth should be more 1900")
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(@Min(value = 1900, message = "Year of birth should be more 1900") int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", userName='" + username + '\'' +
                ", yearOfBirth=" + yearOfBirth +
                ", password='" + password + '\'' +
                '}';
    }
}
