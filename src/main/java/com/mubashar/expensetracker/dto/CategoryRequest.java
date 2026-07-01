package com.mubashar.expensetracker.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

public record CategoryRequest(@NotBlank String name, Long userId) {
}
