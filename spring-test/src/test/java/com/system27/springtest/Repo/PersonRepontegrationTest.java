package com.system27.springtest.Repo;


import com.system27.springtest.domain.Person;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PersonRepontegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonRepo personRepo;

    private final Person createPerson = Person.builder()
            .id(1)
            .name("Ben")
            .build();
    @Test
    public void test(){

        // save test data
        entityManager.persist(createPerson);
        // Find an inserted record
        Optional<Person> createdPerson = personRepo.findById(1);

        assertThat(createdPerson.get().getName(), is(equalTo("Ben")));
    }

}
