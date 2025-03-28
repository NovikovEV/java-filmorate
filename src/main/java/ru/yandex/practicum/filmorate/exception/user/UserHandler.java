package ru.yandex.practicum.filmorate.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.ApiException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserHandler {
    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiException handleUserNotFoundException(UserNotFoundException e) {
        return new ApiException(
                e.getMessage(),
                LocalDateTime.now()
        );
    }
}
