package com.contactinfromation.contact.transformation;

import com.contactinfromation.contact.model.*;

import java.util.*;

import static java.util.Arrays.asList;

public class TransformationValue {

    Person person3 = Person.builder().id(3).firstName("Ramesh").lastName("Shrestha").build();
    Person person1= Person.builder().id(1).firstName("Rama").lastName("Shrestha").build();
    Person person2 = Person.builder().id(2).firstName("Suraj").lastName("Shrestha").build();

    Persons persons = Persons.builder().personList(asList(person1,person2,person3)).build();

    PersonAddress personAddress1 = PersonAddress.builder()
            .streetName("5917 N Kenmore Ave").apartmentUnit("325").city("Chicago")
            .state("Illinois").zipCode("60660").build();
    PersonAddress personAddress2 = PersonAddress.builder()
            .streetName("5920 N Kenmore Ave").apartmentUnit("316").city("Chicago")
            .state("Illinois").zipCode("60660").build();
    PersonAddress personAddress3 = PersonAddress.builder()
            .streetName("1121 N Waverly Place Ave").apartmentUnit("801").city("Milwaukee")
            .state("Wisconsin").zipCode("53202").build();
    PersonAddress personAddress4 = PersonAddress.builder()
            .streetName("5730 N Sheridan Road").apartmentUnit("103").city("Chicago")
            .state("Illinois").zipCode("60660").build();
    PersonAddress personAddress5 = PersonAddress.builder()
            .streetName("5800 N Sheridan Road").apartmentUnit("805").city("Chicago")
            .state("Illinois").zipCode("60660").build();


    PersonAddresses personAddresses = PersonAddresses.builder()
            .personAddressList(asList(personAddress1,personAddress2,personAddress3,personAddress4,personAddress5)).build();
    PersonContactNumber personContactNumber  = PersonContactNumber.builder()
            .mobileNumber(asList("7738652893","45345345")).build();
    PersonOccupation personOccupation = PersonOccupation.builder().
            occupation(asList("RideShare","Software Engineer")).build();
}
