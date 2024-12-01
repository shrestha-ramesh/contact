package com.contactinfromation.contact.transformation;


import com.contactinfromation.contact.model.*;
import org.springframework.stereotype.Service;

@Service
public class PersonTransformation {

    TransformationValue transformationValue =  new TransformationValue();

    public Persons getPersons(){
        try {
        Thread.sleep(4444);
        } catch (InterruptedException e) {
            System.out.println("This is Interruption");
        }
        Persons persons= transformationValue.persons;
        System.out.println("This is Person");
        return persons;
    }
    public PersonAddresses getPersonAddresses(){
        try {
            Thread.sleep(3333);
        } catch (InterruptedException e) {
            System.out.println("This is Interruption");
        }
        PersonAddresses personAddresses = transformationValue.personAddresses;
        System.out.println("This is PersonAddresses");
//        if (personAddress.getZipCode().equals("60660")){
//            throw new RuntimeException("There is zipCode "+personAddress.getZipCode());
//        }
        return personAddresses;
    }
    public PersonContactNumber getPersonContactNumber(){
        try {
            Thread.sleep(2222);
        } catch (InterruptedException e) {
            System.out.println("This is Interruption");
        }
        PersonContactNumber personContactNumber = transformationValue.personContactNumber;
        System.out.println("This is PersonContactNumber");
        return personContactNumber;
    }
    public PersonOccupation getPersonOccupation(){
        try {
            Thread.sleep(1111);
        } catch (InterruptedException e) {
            System.out.println("This is Interruption");
        }
        PersonOccupation personOccupation = transformationValue.personOccupation;
        System.out.println("This is PersonOccupation");
        return personOccupation;
    }
}
