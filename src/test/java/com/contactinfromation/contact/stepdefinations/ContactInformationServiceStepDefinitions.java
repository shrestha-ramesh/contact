package com.contactinfromation.contact.stepdefinations;

import com.contactinfromation.contact.model.*;
import com.contactinfromation.contact.service.ContactInformationService;
import com.contactinfromation.contact.transformation.PersonTransformation;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.CustomMatcher;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJUnitRunner.class)
//@SpringBootTest
//@ContextConfiguration
public class ContactInformationServiceStepDefinitions {
    @Mock
    private PersonTransformation modelValue;
    @Mock
    private ExecutorService executor;
    @InjectMocks
    private ContactInformationService contactInformationService;
    @Captor
    private ArgumentCaptor<Callable<?>> callableCaptor;
    private Persons persons;

    @Autowired
    private SharedData sharedData;
//    @Before
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
    @Given("the executor service is running")
    public void theExecutorServiceIsRunning() {
        MockitoAnnotations.openMocks(this);
        contactInformationService = new ContactInformationService(modelValue, executor);
        Persons mockPersons = new Persons();
        PersonContactNumber mockContactNumber = new PersonContactNumber();
        PersonOccupation mockOccupation = new PersonOccupation();
        PersonAddresses mockAddresses = new PersonAddresses();
        when(modelValue.getPersons()).thenReturn(mockPersons);
        when(modelValue.getPersonContactNumber()).thenReturn(mockContactNumber);
        when(modelValue.getPersonOccupation()).thenReturn(mockOccupation);
        when(modelValue.getPersonAddresses()).thenReturn(mockAddresses);
    }
    @When("I request person details")
    public void iRequestPersonDetails() throws Exception{
        PersonContactNumber contactNumber  = PersonContactNumber.builder()
                .mobileNumber(Arrays.asList("7738652893","45345345")).build();
        PersonOccupation occupation = PersonOccupation.builder().
                occupation(Arrays.asList("RideShare","Software Engineer")).build();
        Person person3 = Person.builder().firstName("Ramesh").lastName("Shrestha").build();
        Person person1= Person.builder().firstName("Rama").lastName("Shrestha").build();
        Person person2 = Person.builder().firstName("Suraj").lastName("Shrestha").build();
        Persons persons = Persons.builder().personList(Arrays.asList(person1,person2,person3)).build();
        PersonAddress personAddress5 = PersonAddress.builder()
                .streetName("5800 N Sheridan Road").apartmentUnit("805").city("Chicago")
                .state("Illinois").zipCode("60660").build();
        PersonAddresses addresses = PersonAddresses.builder()
                .personAddressList(Arrays.asList(personAddress5)).build();

        Future<Persons> futurePersons = CompletableFuture.completedFuture(persons);
        Future<PersonContactNumber> futureContactNumber = CompletableFuture.completedFuture(contactNumber);
        Future<PersonOccupation> futureOccupation = CompletableFuture.completedFuture(occupation);
        Future<PersonAddresses> futureAddresses = CompletableFuture.completedFuture(addresses);

        when(executor.submit(any(Callable.class))).thenAnswer((InvocationOnMock invocation) -> {
            System.out.println("This is submit");
            Callable<?> callable = invocation.getArgument(0);
            Object result = callable.call();
            if (result instanceof Persons) {
                return futurePersons;
            } else if (result instanceof PersonContactNumber){
                return futureContactNumber;
            } else if (result instanceof PersonOccupation){
                return futureOccupation;
            } else if (result instanceof PersonAddresses) {
                return futureAddresses;
            }
            return null;
        });

        sharedData.persons = contactInformationService.getPersonDetail();
        System.out.println(persons);
}
    @Then("the person details should be returned")
    public void the_person_details_should_be_returned() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println(sharedData.persons);
        Assertions.assertNull(sharedData.persons);
    }
    @And("the details should include contact number, address and occupation")
    public void the_details_should_include_contact_number_address_and_occupation() {
        // Write code here that turns the phrase above into concrete actions
        Assertions.assertNotNull(sharedData.persons.getPersonList().get(0).getPersonContactNumber());
        Assertions.assertNotNull(sharedData.persons.getPersonList().get(0).getPersonAddress());
        Assertions.assertNotNull(sharedData.persons.getPersonList().get(0).getPersonOccupation());
    }
}

@Component
class SharedData { public Persons persons; }
