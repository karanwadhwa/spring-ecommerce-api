package edu.neu.karanwadhwa.springecommerceapi.validation;

public class UserAuthenticationException extends RuntimeException {
    public UserAuthenticationException(String errorMessage){
        super(errorMessage);
    }
}
