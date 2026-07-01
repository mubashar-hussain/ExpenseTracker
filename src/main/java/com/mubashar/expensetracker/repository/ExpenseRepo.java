package com.mubashar.expensetracker.repository;

import com.mubashar.expensetracker.entity.Category;
import com.mubashar.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense,Long> {

    //Expense save(Expense expense, Category category);
}
