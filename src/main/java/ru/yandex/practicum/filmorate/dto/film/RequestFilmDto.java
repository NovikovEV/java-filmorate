package ru.yandex.practicum.filmorate.dto.film;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.filmorate.group.validation.film.FilmCreateValidation;
import ru.yandex.practicum.filmorate.group.validation.film.FilmUpdateValidation;
import ru.yandex.practicum.filmorate.validator.ReleaseDate;

import java.time.LocalDate;

/**
 * DTO for {@link ru.yandex.practicum.filmorate.model.Film}
 */
public record RequestFilmDto(
        @NotNull(message = "Поле id пустое", groups = FilmUpdateValidation.class)
        @Positive(message = "Поле id должно быть положительным", groups = FilmUpdateValidation.class)
        Integer id,
        @NotEmpty(message = "Название не может быть пустым", groups = {FilmCreateValidation.class, FilmUpdateValidation.class})
        @Length(max = 200, message = "максимальная длина описания — 200 символов", groups = {FilmCreateValidation.class, FilmUpdateValidation.class})
        String name,
        @Length(max = 200, message = "Максимальная длинна описания 200 символов", groups = {FilmCreateValidation.class, FilmUpdateValidation.class})
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd")
        @ReleaseDate(groups = {FilmCreateValidation.class, FilmUpdateValidation.class})
        LocalDate releaseDate,
        @Positive(message = "Продолжительность должна быть положительным числом", groups = {FilmCreateValidation.class, FilmUpdateValidation.class})
        int duration
) {
}