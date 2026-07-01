package com.mubashar.expensetracker.dto;

import java.time.LocalDateTime;

public record ApiError(LocalDateTime timeStamp, String errorMessage, String message,int status,String path) {
}
