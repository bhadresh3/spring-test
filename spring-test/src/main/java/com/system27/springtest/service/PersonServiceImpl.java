package com.system27.springtest.service;

import com.system27.springtest.Repo.PersonRepo;
import com.system27.springtest.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepo personRepo;


    @Override
    public List<Person> findALl() {
        return (List<Person>) personRepo.findAll();
    }

    @Override
    public Person CreatePerson(Person person) {
        return personRepo.save(person);
    }
}
