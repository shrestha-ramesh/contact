package com.contactinfromation.contact.stepdefinations;

import com.contactinfromation.contact.controller.ContactInformationController;
import com.contactinfromation.contact.model.Person;
import com.contactinfromation.contact.model.Persons;
import com.contactinfromation.contact.service.ContactInformationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@ContextConfiguration
public class ContactInformationStepDefinitions {

    @MockBean
    private ContactInformationService contactInformationService;

    @InjectMocks
    private ContactInformationController contactInformationController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private Persons persons;

    @Given("the server is running")
    public void theServerIsRunning() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @When("I send a GET request to {string} with request parameter firstName {string}")
    public void iSendAGETRequestToWithRequestParam(String url, String firstName) throws Exception {
        Person person1 = Person.builder().firstName(firstName).build();
        persons = Persons.builder().personList(Collections.singletonList(person1)).build();
        when(contactInformationService.getPersonDetail()).thenReturn(persons);

        MvcResult result = mockMvc.perform(get(url).param("firstName", firstName))
                .andExpect(status().isOk())
                .andReturn();

        persons = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Persons.class);
    }

    @When("I send a GET request to {string}")
    public void iSendAGETRequestTo(String url) throws Exception {
        Person person2 = Person.builder().lastName("Shrestha").build();
        persons = Persons.builder().personList(Collections.singletonList(person2)).build();
        when(contactInformationService.getPersonDetail()).thenReturn(persons);

        MvcResult result = mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();

        persons = new ObjectMapper().readValue(result.getResponse().getContentAsString(), Persons.class);
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int statusCode) throws Exception {
        Assertions.assertEquals(statusCode, mockMvc.perform(get("/person/details/Shrestha"))
                .andReturn().getResponse().getStatus());
    }

    @And("the response should contain person details with first name {string}")
    public void theResponseShouldContainPersonDetailsWithFirstName(String firstName) {
        Assertions.assertNotNull(persons);
        Assertions.assertTrue(persons.getPersonList().get(0).getFirstName().contains(firstName));
    }

    @And("the response should contain person details with last name {string}")
    public void theResponseShouldContainPersonDetailsWithLastName(String lastName) {
        Assertions.assertNotNull(persons);
        Assertions.assertTrue(persons.getPersonList().get(0).getLastName().contains(lastName));
    }
}
