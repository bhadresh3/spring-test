package com.system27.springtest.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system27.springtest.domain.Person;
import com.system27.springtest.service.PersonService;
import com.system27.springtest.util.PersonCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mvcMock;

    @MockBean
    private PersonService personServiceMock;

    private final Person createdPerson = Person.builder()
            .id(1)
            .name("Ben")
            .build();

    private final Person invalidPerson = Person.builder()
            .id(1)
            .build();

    @BeforeEach
    public void setUp(){
        BDDMockito.when(personServiceMock.findALl()).thenReturn(Collections.singletonList(createdPerson));
        BDDMockito.when(personServiceMock.CreatePerson(PersonCreator.createPersonToBeSaved()))
                .thenReturn(createdPerson);
        BDDMockito.when(personServiceMock.CreatePerson(invalidPerson))
                .thenReturn(null);
    }

    ObjectMapper Obj = new ObjectMapper();

    String personString = "{\"name\":\"Ben\"}";
    @Test
    @DisplayName("create Person when successful")
    public void create_Person_WhenSuccessful() throws Exception{
        mvcMock.perform(post("/people")
                .content(Obj.writeValueAsString(PersonCreator.createPersonToBeSaved()))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().json(personString));
        verify(personServiceMock).CreatePerson(PersonCreator.createPersonToBeSaved());

    }

    @Test
    @DisplayName("create Person for not valid object")
    public void create_Person_WhenNotValid() throws Exception{
        mvcMock.perform(post("/people")
                .content(Obj.writeValueAsString(invalidPerson))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is(400));
    }

    @Test
    @DisplayName("Get Person")
    public void Get_Person() throws Exception{
        mvcMock.perform(get("/people")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(content().string("[{\"id\":1,\"name\":\"Ben\"}]"));
    }


}
