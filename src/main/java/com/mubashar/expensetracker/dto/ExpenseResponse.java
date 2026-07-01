package com.mubashar.expensetracker.dto;

import com.mubashar.expensetracker.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExpenseResponse(BigDecimal amount, String title, LocalDate localDate, String description, Category category){
}
