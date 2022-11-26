package edu.neu.karanwadhwa.springecommerceapi.service;

public class UserNotAllowedException extends RuntimeException {
    UserNotAllowedException(String usertype){
        super("Cannot perform action on user type: "+usertype);
    }
}
