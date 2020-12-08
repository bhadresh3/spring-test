package com.system27.springtest.service;

import com.system27.springtest.domain.Person;

import java.util.List;

public interface PersonService {
    List<Person> findALl();

    Person CreatePerson(Person person);
}
