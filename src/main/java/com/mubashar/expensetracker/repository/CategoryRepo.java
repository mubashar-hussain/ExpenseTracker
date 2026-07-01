package com.mubashar.expensetracker.repository;

import com.mubashar.expensetracker.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
