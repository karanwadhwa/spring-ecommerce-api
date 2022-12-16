package edu.neu.karanwadhwa.springecommerceapi.validation;

public class InvalidAPIRequestException extends RuntimeException {
    public InvalidAPIRequestException(String errorMessage){
        super(errorMessage);
    }
}
