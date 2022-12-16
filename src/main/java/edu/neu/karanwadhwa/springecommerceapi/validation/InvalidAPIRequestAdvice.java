package edu.neu.karanwadhwa.springecommerceapi.validation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class InvalidAPIRequestAdvice {

    @ResponseBody
    @ExceptionHandler(InvalidAPIRequestException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ResponseEntity<Map<String, String>> invalidAPIRequestHandler(InvalidAPIRequestException ex){
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", ex.getMessage());

        return new ResponseEntity<>(responseMap,HttpStatus.FORBIDDEN);
    }
}
