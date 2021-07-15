package com.kaibakorp.vaccine.api.exceptionhandler;

import com.kaibakorp.vaccine.domain.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleServiceExcpetion(ServiceException ex, WebRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        Problem problem = problemSet(ex.getMessage(), status);
        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){

        var fields = new ArrayList<Problem.Field>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()){
            String name = ((FieldError) error).getField();
            String msg = error.getDefaultMessage();

            fields.add(new Problem.Field(name, msg));
        }
        Problem problem = problemSet("One or more fields are wrong filled",status);
        problem.setFields(fields);
        return super.handleExceptionInternal(ex,problem, headers,status,request);
    }

    private Problem problemSet(String msg, HttpStatus status){
        var problem = new Problem();
        problem.setStatus(status.value());
        problem.setTitle(msg);
        problem.setDateHour(OffsetDateTime.now());
        return problem;
    }
}
