package com.mubashar.expensetracker.controller;

import com.mubashar.expensetracker.dto.ExpenseRequest;
import com.mubashar.expensetracker.dto.ExpenseResponse;
import com.mubashar.expensetracker.dto.TotalExpenseResponse;
import com.mubashar.expensetracker.entity.Expense;
import com.mubashar.expensetracker.service.ExpenseService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {

        this.expenseService = expenseService;
    }

    @PostMapping("/addExpense")
    public ResponseEntity<ExpenseResponse> addExpense(@RequestBody ExpenseRequest expenseRequest) {

        Expense expense = expenseService.addExpense(expenseRequest);

        ExpenseResponse expenseResponse = new ExpenseResponse(expense.getAmount(), expense.getTitle(), expense.getLocalDate(), expense.getDescription(), expense.getCategory());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(expenseResponse);
    }

    @GetMapping("/allExpense")
    public ResponseEntity<List<ExpenseResponse>> getAllExpense(@RequestParam(required = false, defaultValue = "1") int pageNo,
                                                               @RequestParam(required = false, defaultValue = "3") int pageSize,
                                                               @RequestParam(required = false, defaultValue = "id") String sortBy,
                                                               @RequestParam(required = false, defaultValue = "ASC") String sortDir,
                                                               @RequestParam(required = false) String search) {

        Sort sort = null;
        if (sortDir.equalsIgnoreCase("ASC")) {

            sort = Sort.by(sortBy).ascending();

        } else {
            sort = sort.by(sortBy).descending();
        }
        List<ExpenseResponse> expenseList = expenseService.getAllExpense(PageRequest.of(pageNo, pageSize, sort), search);

        return ResponseEntity.ok(expenseList);

    }

    @GetMapping("/expense/{id}")
    public ResponseEntity<ExpenseResponse> getExpenseById(@PathVariable Long id) {
        ExpenseResponse expenseResponse = expenseService.expenseById(id);

        return ResponseEntity.status(HttpStatus.OK).body(expenseResponse);

    }
   /* @GetMapping("/totalExpense")
     public ResponseEntity<TotalExpenseResponse> totalExpense(){

        BigDecimal amount  = expenseService.totalExpense();
        return ResponseEntity.ok(amount);

    }
*/


    @PutMapping("/expense/{id}")
    public ResponseEntity<ExpenseResponse> updateExpense(@PathVariable Long id, @RequestBody ExpenseRequest expenseRequest) {

        ExpenseResponse expenseResponse = expenseService.updateExpense(id, expenseRequest);

        return ResponseEntity.status(HttpStatus.OK).body(expenseResponse);

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }
    @GetMapping("/monthlyExpense")
    public ResponseEntity<BigDecimal> getMonthlyExpense(@RequestParam LocalDate from,@RequestParam LocalDate to){
      return   ResponseEntity.ok(expenseService.monthlyExpense(from, to));
    }

    // Monthly expenses
    //Category wise total
}
