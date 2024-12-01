package com.contactinfromation.contact.model;

import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Person {
    int id;
    private String firstName;
    private String lastName;
    private List<PersonAddress> personAddress;
    private PersonContactNumber personContactNumber;
    private PersonOccupation personOccupation;
}
