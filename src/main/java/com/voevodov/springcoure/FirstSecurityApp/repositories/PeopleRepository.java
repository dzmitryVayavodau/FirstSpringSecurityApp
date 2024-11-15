package com.voevodov.springcoure.FirstSecurityApp.repositories;

import com.voevodov.springcoure.FirstSecurityApp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByUserName(String userName);
}
