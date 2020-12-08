package com.system27.springtest.util;

import com.system27.springtest.domain.Person;

public class PersonCreator {

    public static Person createPersonToBeSaved() {
        return Person.builder()
                .name("Ben")
                .build();
    }

    public static Person createValidPerson() {
        return Person.builder()
                .id(1)
                .name("Ben")
                .build();
    }

    public static Person createdValidUpdatedPerson() {
        return Person.builder()
                .id(1)
                .name("Bob")
                .build();
    }

}
