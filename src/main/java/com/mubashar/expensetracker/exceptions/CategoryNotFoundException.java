package com.mubashar.expensetracker.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String message){

        super(message);
    }
}
