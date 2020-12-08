package com.system27.springtest.Controller;


import com.system27.springtest.domain.Person;
import com.system27.springtest.service.PersonService;
import com.system27.springtest.util.PersonCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class PersonControllerIntegrationTest {
    @MockBean
    private PersonService personServiceMock;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final Person personToBeCreated = PersonCreator.createPersonToBeSaved();
    private final Person createdPerson = PersonCreator.createValidPerson();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(personServiceMock.CreatePerson(PersonCreator.createPersonToBeSaved()))
                .thenReturn(createdPerson);
    }

    @Test
    @DisplayName("create Person when successful")
    public void create_Person_WhenSuccessful() throws Exception{

        ResponseEntity<Person> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/people", personToBeCreated, Person.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
        assertEquals(responseEntity.getBody(), createdPerson);

    }

}
