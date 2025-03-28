package ru.yandex.practicum.filmorate.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ApiException(
        String message,
        @JsonFormat(pattern = "yyyy-MM-dd/HH:mm:ss")
        LocalDateTime timeStamp
) {
}
