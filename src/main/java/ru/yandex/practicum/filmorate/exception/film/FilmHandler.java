package ru.yandex.practicum.filmorate.exception.film;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.yandex.practicum.filmorate.exception.ApiException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class FilmHandler {
    @ExceptionHandler(value = FilmNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiException handleFilmNotFoundException(FilmNotFoundException e) {
        return new ApiException(
                e.getMessage(),
                LocalDateTime.now()
        );
    }
}
