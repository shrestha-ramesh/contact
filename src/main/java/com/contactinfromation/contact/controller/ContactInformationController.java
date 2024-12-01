package com.contactinfromation.contact.controller;


import com.contactinfromation.contact.model.*;
import com.contactinfromation.contact.service.ContactInformationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@Tag(name = "Contact Information Management System",
        description = "Operations pertaining to contact information in the system")
public class ContactInformationController {
    private ContactInformationService contactInformationService;

    public ContactInformationController(ContactInformationService contactInformationService){
        this.contactInformationService = contactInformationService;
    }

    @Operation(summary = "Get person details requestParam",
            description = "Retrieve details of persons")
    @GetMapping("/person/details")
    public Persons getPersonDetail(@RequestParam String firstName){
        long startTime = System.currentTimeMillis();
        Persons personDetailsList = contactInformationService.getPersonDetail();
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        System.out.println(executionTime);
        return personDetailsList;
    }
    @PostMapping("/save")
    public String savePersonsDetails(@RequestBody Persons persons){
        contactInformationService.savePersonDetails(persons);
        return "200";
    }
    @GetMapping("/getSave/{id}")
    public Person getSave(@PathVariable String id){
        Person person = contactInformationService.getSave(id);
        return person;
    }

    @Operation(summary = "Get person details pathVariable",
            description = "Retrieve details of persons")
    @GetMapping("/person/details/{lastName}")
    public Persons getPersonDetailPath(@PathVariable String lastName){
        long startTime = System.currentTimeMillis();
        Persons personDetailsList = contactInformationService.getPersonDetail();
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        System.out.println(executionTime);
        return personDetailsList;
    }
}
