package com.voevodov.springcoure.FirstSecurityApp.services;

import com.voevodov.springcoure.FirstSecurityApp.model.Person;
import com.voevodov.springcoure.FirstSecurityApp.repositories.PeopleRepository;
import com.voevodov.springcoure.FirstSecurityApp.security.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PersonDetailsService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("user name = " + userName);
        Optional<Person> person = peopleRepository.findByUserName(userName);


        if (person.isEmpty())
            throw new UsernameNotFoundException("User " + userName + " not found");


        return new PersonDetails(person.get());
    }
}
