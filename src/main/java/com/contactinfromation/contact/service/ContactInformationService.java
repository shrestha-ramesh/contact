package com.contactinfromation.contact.service;


import com.contactinfromation.contact.model.*;
import com.contactinfromation.contact.transformation.PersonTransformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ContactInformationService {
    private static final Logger LOGGER = Logger.getLogger(ContactInformationService.class.getName());
    private final PersonTransformation modelValue;
    private ExecutorService executor;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private ServiceImp serviceImp;
    @PreDestroy
    public void shutDownExecutorService(){
        executor.shutdown();
        try{
            if(!executor.awaitTermination(1, TimeUnit.MINUTES)){
                executor.shutdownNow();
            }
        }catch (InterruptedException e){
            executor.shutdownNow();
        }
    }
    public ContactInformationService(PersonTransformation modelValue, ExecutorService executor){
        this.modelValue = modelValue;
        this.executor = executor;
    }
    public PersonAddresses getPersonAddresses(){
        Future<PersonAddresses> personAddresses = executor.submit(modelValue::getPersonAddresses);
        PersonAddresses newpersonAddresseses=null;
        try{
            newpersonAddresseses = personAddresses.get();
        }catch (InterruptedException | ExecutionException e){
            System.out.println(e.getMessage());
        }
        return newpersonAddresseses;
    }
    public Persons getPersonDetail(){
        System.out.println("Submitting Person Callables to ExecutorService");
        Future<Persons> person1 = executor.submit(modelValue::getPersons);
        Future<PersonContactNumber> personContactNumber1 = executor.submit(modelValue::getPersonContactNumber);
        Future<PersonOccupation> personOccupation1 = executor.submit(modelValue::getPersonOccupation);
        Future<PersonAddresses> personAddress1 = executor.submit(modelValue::getPersonAddresses);

        Persons persons = getUnchecked(person1);
        PersonContactNumber personContactNumber = getUnchecked(personContactNumber1);
        PersonOccupation personOccupation = getUnchecked(personOccupation1);
        PersonAddresses personAddress=getUnchecked(personAddress1);

        List<PersonAddress> personAddresses = personAddress.getPersonAddressList().stream()
                .filter(zip->zip.getZipCode() == "60660" && persons.getPersonList().get(0).getFirstName() == "Rama")
                .collect(Collectors.toList());

        persons.getPersonList().stream().forEach(person -> {
            person.setPersonContactNumber(personContactNumber);
            person.setPersonAddress(personAddresses);
            person.setPersonOccupation(personOccupation);
        });

        return persons;
    }
    private <T> T getUnchecked(Future<T> future) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void savePersonDetails(Persons persons) {
        for(Person eachPerson : persons.getPersonList()){
            System.out.println(eachPerson);
            setDataToRedis(String.valueOf(eachPerson.getId()), eachPerson,3000l);
        }
    }
    public void setDataToRedis(String key, Object o, Long ttl){
        try {
            redisTemplate.opsForValue().set(key, o, ttl, TimeUnit.SECONDS);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public Person getSave(String id) {
        Person person = getDataFromRedis(id, Person.class);
        System.out.println(person);
        return person;
    }
    public <T> T getDataFromRedis(String key, Class<T> DTOClass){

        try {
            Object o = redisTemplate.opsForValue().get(key);
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(o.toString(), DTOClass);
        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }
}
