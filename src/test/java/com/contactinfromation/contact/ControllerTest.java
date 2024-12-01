package com.contactinfromation.contact;

import com.contactinfromation.contact.service.ContactInformationService;
import com.contactinfromation.contact.transformation.PersonTransformation;


//@SpringBootTest
public class ControllerTest {
    public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(100);
        long startTime = System.currentTimeMillis();

//        PersonTransformation modelValue = new PersonTransformation();
//        ContactInformationService contactInformationService = new ContactInformationService(modelValue);
//        Future<PersonDetail> submit = null;
//                        contactInformationService.getPersonDetail();

        for(int i=0; i<10; i++){
            try{
//                submit = executor.submit(contactInformationService::getPersonDetail);
//                contactInformationService.getPersonDetail();
                System.out.println("This is i value "+i);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        long endTime = System.currentTimeMillis();

        long executionTime = endTime - startTime;
        System.out.println("This is total time "+executionTime);

//        for(int i=0; i< 2; i++){
//            try {
//                PersonDetail personDetail= contactInformationService.getPersonDetail();
//            } catch (Exception e){
//                System.out.println("This is exception occur "+i);
//            }
//        }

    }
//
//    @Autowired
//    private ContactInformationService contactInformationService;
//
//    @Test
//    void testGetPersonDetail(){
//        for (int i=0; i< 10; i++){
//            try{
//                contactInformationService.getPersonDetail();
//            }catch (Exception e){
//                System.out.println("This is exception occur "+i);
//            }
//        }
//    }
//
}


