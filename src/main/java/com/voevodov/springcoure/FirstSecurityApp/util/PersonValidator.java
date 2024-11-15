package com.voevodov.springcoure.FirstSecurityApp.util;

import com.voevodov.springcoure.FirstSecurityApp.model.Person;
import com.voevodov.springcoure.FirstSecurityApp.services.PersonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {

    PersonDetailsService personDetailsService;

    @Autowired
    public PersonValidator(PersonDetailsService personDetailsService) {
        this.personDetailsService = personDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        System.out.println("person: " + person);

        try {
            personDetailsService.loadUserByUsername(person.getUsername());
        }
        catch (UsernameNotFoundException ignored) {
            return;
        }

        errors.rejectValue("userName", "", "This userName already exists");

    }
}
