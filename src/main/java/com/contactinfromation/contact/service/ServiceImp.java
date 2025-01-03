package com.contactinfromation.contact.service;

import com.contactinfromation.contact.model.Person;

public class ServiceImp {

    private ContactInformationService contactInformationService;


    public ServiceImp(ContactInformationService contactInformationService){
        this.contactInformationService = contactInformationService;
    }

    public void getPersonDetails (){
        contactInformationService.getPersonDetail();
    }

}
