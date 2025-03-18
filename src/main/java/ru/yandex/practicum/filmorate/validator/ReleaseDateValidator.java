package ru.yandex.practicum.filmorate.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class ReleaseDateValidator implements ConstraintValidator<ReleaseDate, LocalDate> {
    @Override
    public void initialize(ReleaseDate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate release, ConstraintValidatorContext context) {
        LocalDate firstFilmDate = LocalDate.of(1985, 12, 28);
        return release.isEqual(firstFilmDate) || release.isAfter(firstFilmDate);
    }
}