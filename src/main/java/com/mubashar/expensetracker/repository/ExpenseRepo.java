package com.mubashar.expensetracker;

import com.mubashar.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense,Long> {
}
