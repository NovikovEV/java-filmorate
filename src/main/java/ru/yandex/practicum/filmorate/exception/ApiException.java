package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ApiException(
        String message,
        HttpStatus httpStatus,
        LocalDateTime timeStamp
) {
}
