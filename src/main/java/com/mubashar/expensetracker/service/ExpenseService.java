package com.mubashar.expensetracker.service;

import com.mubashar.expensetracker.dto.ExpenseRequest;
import com.mubashar.expensetracker.dto.ExpenseResponse;
import com.mubashar.expensetracker.dto.TotalExpenseResponse;
import com.mubashar.expensetracker.exceptions.ExpenseNotFoundException;
import com.mubashar.expensetracker.repository.ExpenseRepo;
import com.mubashar.expensetracker.entity.Expense;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepo expenseRepo;

    public ExpenseService(ExpenseRepo expenseRepo) {

        this.expenseRepo = expenseRepo;
    }


    public List<ExpenseResponse> getAllExpense(Pageable pageable,String search) {
        if (search == null){

            List<Expense> allExpenses =   expenseRepo.findAll(pageable).getContent();

            List<ExpenseResponse> expenseResponses = new ArrayList<>();
            for (Expense exp : allExpenses) {
                expenseResponses.add(new ExpenseResponse(exp.getAmount()
                                , exp.getTitle()
                                , exp.getLocalDate()
                                , exp.getDescription()
                                , exp.getCategory()));

            }
            return expenseResponses;

        }else{
            return expenseRepo.findByTitle(search,pageable).getContent();

        }




    }

    public ExpenseResponse expenseById(Long id) {

      Optional<Expense> expense = expenseRepo.findById(id);
      Expense expense1 = null;
      if(expense.isPresent())
      {
        expense1 =  expense.get();
      }else {

          throw new ExpenseNotFoundException("Expense Id Not Found " + id);
      }

      ExpenseResponse expenseResponse = new ExpenseResponse(expense1.getAmount(),expense1.getTitle(),expense1.getLocalDate(),expense1.getDescription(),expense1.getCategory());

      return  expenseResponse;
    }

    public ExpenseResponse updateExpense(Long id,  ExpenseRequest expenseRequest) {

               Expense  expense1 = expenseRepo.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Id not Found"));

               expense1.setAmount(expenseRequest.amount());
               expense1.setDescription(expenseRequest.description());
               expense1.setCategory(expenseRequest.category());
               expense1.setTitle(expenseRequest.title());
               expense1.setLocalDate(expenseRequest.localDate());

        return new ExpenseResponse(expense1.getAmount(),expense1.getTitle(),expense1.getLocalDate(),expense1.getDescription(),expense1.getCategory());

    }

    public void deleteExpense(Long id) {
        if (expenseRepo.existsById(id)) {
            expenseRepo.deleteById(id);


        } else {
            throw new ExpenseNotFoundException( "Expense not found");
        }
    }

    public Expense addExpense(ExpenseRequest expenseRequest) {

        Expense expense = new Expense();
        expense.setLocalDate(expenseRequest.localDate());
        expense.setCategory(expenseRequest.category());
        expense.setTitle(expenseRequest.title());
        expense.setAmount(expenseRequest.amount());
        expense.setDescription(expenseRequest.description());

        return expenseRepo.save(expense);
    }

   /* public TotalExpenseResponse totalExpense() {

        BigDecimal allAmounts = expenseRepo.sumAllAmounts();

        return allAmounts;
    }*/

    public BigDecimal monthlyExpense(LocalDate monthStart, LocalDate monthEnd) {
        return expenseRepo.sumAmountBetween(monthStart ,monthEnd);
    }
}
