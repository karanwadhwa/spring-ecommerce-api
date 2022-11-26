package edu.neu.karanwadhwa.springecommerceapi.validation;

public class UserNotAllowedException extends RuntimeException {
    public UserNotAllowedException(String usertype){
        super("Cannot perform action on user type: "+usertype);
    }
}
