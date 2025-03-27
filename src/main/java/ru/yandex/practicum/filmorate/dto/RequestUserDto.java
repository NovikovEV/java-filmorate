package ru.yandex.practicum.filmorate.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;

/**
 * DTO for {@link User}
 */
public record RequestUserDto(
        Integer id,
        @Email(message = "Не верный формат e-mail", regexp = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$") @NotEmpty(message = "поле e-mail пустое") @NotBlank String email,
        @NotEmpty(message = "Поле login пустое") @NotBlank(message = "Поле login пустое") @Pattern(message = "В поле login есть пробелы", regexp = "^[0-9A-Za-z]{6,16}$") String login,
        String name,
        @JsonFormat(pattern = "yyyy-MM-dd") @Past LocalDate birthday
) {
}