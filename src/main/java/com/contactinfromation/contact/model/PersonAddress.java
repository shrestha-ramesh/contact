package com.contactinfromation.contact.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonAddress {
    private String streetName;
    private String apartmentUnit;
    private String city;
    private String state;
    private String zipCode;
}
