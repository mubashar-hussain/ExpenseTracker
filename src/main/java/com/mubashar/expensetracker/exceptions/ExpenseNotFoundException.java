package com.mubashar.expensetracker.exceptions;

public class ExpenseNotFoundException extends  RuntimeException{

    public ExpenseNotFoundException(String message){
        super(message);
    }
}
