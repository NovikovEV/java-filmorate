package ru.yandex.practicum.filmorate.exception.film;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.yandex.practicum.filmorate.exception.ApiException;

import java.time.LocalDateTime;

@ControllerAdvice
public class FilmNotFoundHandler {
    @ExceptionHandler(value = FilmNotFoundException.class)
    public ResponseEntity<Object> handleFilmNotFoundException(FilmNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(
                e.getMessage(),
                httpStatus,
                LocalDateTime.now()
        );

        return new ResponseEntity<>(apiException, httpStatus);
    }
}
