package com.system27.springtest.Controller;

import com.system27.springtest.domain.Person;
import com.system27.springtest.service.PersonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/people")
@Slf4j
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Person> findAll(){
        return personService.findALl();
    }

    @RequestMapping(method = RequestMethod.POST)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPersons(@RequestBody @Valid Person person){
        return personService.CreatePerson(person);
    }


}
