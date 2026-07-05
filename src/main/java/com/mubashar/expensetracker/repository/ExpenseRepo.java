package com.mubashar.expensetracker.repository;

import com.mubashar.expensetracker.dto.ExpenseResponse;
import com.mubashar.expensetracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ExpenseRepo extends JpaRepository<Expense,Long> {
    Page<ExpenseResponse> findByTitle(String search, Pageable pageable);

}
