package ru.yandex.practicum.filmorate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;
import ru.yandex.practicum.filmorate.validator.ReleaseDate;

import java.time.LocalDate;

/**
 * DTO for {@link ru.yandex.practicum.filmorate.model.Film}
 */
public record IncomeFilmDto(
        @NotEmpty(message = "Название не может быть пустым") @Length(max = 200, message = "максимальная длина описания — 200 символов") String name,
        String description,
        @JsonFormat(pattern = "yyyy-MM-dd") @ReleaseDate LocalDate releaseDate,
        @Positive(message = "Продолжительность должна быть положительным числом") int duration
) {
}