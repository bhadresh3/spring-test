package com.system27.springtest.Service;

import com.system27.springtest.domain.Person;
import com.system27.springtest.service.PersonServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class PersonServiceIntegrationTest {

    @Autowired
    private PersonServiceImpl personService;


    private final Person createPerson = Person.builder()
            .id(1)
            .name("Ben")
            .build();

    private final Person createInvalidPerson = Person.builder()
            .id(2)
            .build();
    @Test
    public void testCreatePersonHappyPath() {

        Person person = personService.CreatePerson(createPerson);

        // Verify the addition
        assertNotNull(person);
        assertNotNull(person.getId());
        assertEquals("Ben", person.getName());
    }


    @Test
    public void testCreatePersonSadPath() {

       Exception exception =  assertThrows(RuntimeException.class,
               () ->{personService.CreatePerson(createInvalidPerson);});
        assertTrue(exception.getMessage().contains("javax.persistence.RollbackException: Error while committing the transaction"));
    }
}
