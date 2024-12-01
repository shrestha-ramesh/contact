package com.contactinfromation.contact.stepdefinations;

import com.contactinfromation.contact.model.PersonAddresses;
import com.contactinfromation.contact.model.PersonContactNumber;
import com.contactinfromation.contact.model.PersonOccupation;
import com.contactinfromation.contact.model.Persons;
import org.mockito.ArgumentMatcher;
import org.mockito.stubbing.Answer;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class CustomMatchers {
//    public static <T> Answer<Future<T>> answerWithCompletedFuture(T value) {
//        return invocation -> CompletableFuture.completedFuture(value);
//    }
    public static <T>ArgumentMatcher<Callable<T>> callableFor(Class<T> clazz){
        return callable->{
            if(callable == null){
                return false;
            }
            return clazz.isInstance(clazz);
        };
//        System.out.println("This is matchers");
//        return callable -> { // Match based on the callable class type without invoking it
//             String callableTypeName = callable.getClass().getName(); return callableTypeName.contains(clazz.getSimpleName()); };
//        return callable -> callable.getClass().getName().contains(clazz.getSimpleName());
//        return callable->{
//             return callable != null && callable.getClass().getName().contains(clazz.getSimpleName());
//        };
//        return callable -> {
//            try {
//                return clazz.isInstance(callable.call());
//            } catch (Exception e) {
//                return false;
//            }
//        };
//
//        return callable -> {
////             Check if callable class name contains the expected class type name
//             if (callable == null) {
//                 System.out.println("Callable is null");
//                 return false;
//             }
//             String callableTypeName = callable.getClass().getName();
//             boolean matches = callableTypeName.contains(clazz.getSimpleName());
//             System.out.println("Checking callable type: " + callableTypeName + " against " + clazz.getSimpleName() + " -> " + matches);
//             return matches;
//        };
    }
}
//class PersonsCallable implements Callable<Persons> {
//    @Override
//    public Persons call()throws Exception {
//        return new Persons();
//    }
//}
//class PersonContactNumberCallable implements Callable<PersonContactNumber> {
//    @Override
//    public PersonContactNumber call()throws Exception {
//        return new PersonContactNumber();
//    }
//}

