package com.mubashar.expensetracker.controller;

import com.mubashar.expensetracker.dto.ExpenseRequest;
import com.mubashar.expensetracker.dto.ExpenseResponse;
import com.mubashar.expensetracker.entity.Expense;
import com.mubashar.expensetracker.service.ExpenseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    @PostMapping("/addExpense")
    public ResponseEntity<ExpenseResponse> addExpense(@RequestBody ExpenseRequest expenseRequest){

        Expense expense = expenseService.addExpense(expenseRequest);

         ExpenseResponse expenseResponse = new ExpenseResponse(expense.getAmount(),expense.getTitle(),expense.getLocalDate(),expense.getDescription(),expense.getCategory());

         return ResponseEntity
                 .status(HttpStatus.CREATED)
                 .body(expenseResponse);
    }

    @GetMapping("/allExpense")
    public ResponseEntity<List<ExpenseResponse>> getAllExpense(@RequestParam int pageNo , @RequestParam int pageSize){


        List<ExpenseResponse> expenseList = expenseService.getAllExpense(PageRequest.of(pageNo, pageSize));

        return ResponseEntity.ok(expenseList);

    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long id){
        ExpenseResponse expenseResponse = expenseService.expenseById(id);

        return ResponseEntity.status(HttpStatus.OK).body(expenseResponse);

    }

     @PutMapping("/expense/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest expenseRequest){

        ExpenseResponse expenseResponse = expenseService.updateExpense(id, expenseRequest);


        return ResponseEntity.status(HttpStatus.OK).body(expenseResponse);

    }
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id){

        expenseService.deleteExpense(id);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }


}
