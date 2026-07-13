package com.mubashar.expensetracker.repository;

import com.mubashar.expensetracker.dto.ExpenseResponse;
import com.mubashar.expensetracker.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;


public interface ExpenseRepo extends JpaRepository<Expense,Long> {
    Page<ExpenseResponse> findByTitle(String search, Pageable pageable);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
    BigDecimal sumAllAmounts();



}
