package com.mubashar.expensetracker.service;

import com.mubashar.expensetracker.ExpenseRepo;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final ExpenseRepo expenseRepo;

    public ExpenseService(ExpenseRepo expenseRepo){
        this.expenseRepo = expenseRepo;
    }




}
