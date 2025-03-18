package ru.yandex.practicum.filmorate.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.yandex.practicum.filmorate.dto.IncomeUserDto;
import ru.yandex.practicum.filmorate.dto.IncomeUserWithIdDto;
import ru.yandex.practicum.filmorate.dto.OutcomeUserDto;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;
    static private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public OutcomeUserDto create(IncomeUserDto incomeUserDto) {
        User user = convertToUser(incomeUserDto);
        if (user.getName() == null) {
            user.setName("common");
        }
        return convertToOutcomeUserDto(userStorage.create(user));
    }

    @Override
    public OutcomeUserDto update(IncomeUserWithIdDto incomeUserWithIdDto) {
        User user = convertToUser(incomeUserWithIdDto);
        Optional<User> optionalUser = userStorage.update(user);

        if (optionalUser.isPresent()) {
            User updatedUser = optionalUser.get();
            return convertToOutcomeUserDto(updatedUser);
        } else {
            final String message = "Пользователь с id=: " + incomeUserWithIdDto.id() + " не найден";
            log.info(message);
            throw new UserNotFoundException(message);
        }
    }

    @Override
    public List<OutcomeUserDto> getAll() {
        return userStorage.getAll().stream()
                .map(this::convertToOutcomeUserDto)
                .toList();
    }

    private User convertToUser(IncomeUserDto incomeUserDto) {
        return new User(
                incomeUserDto.email(),
                incomeUserDto.login(),
                incomeUserDto.name(),
                incomeUserDto.birthday()
        );
    }

    private User convertToUser(IncomeUserWithIdDto incomeUserWithIdDto) {
        return new User(
                incomeUserWithIdDto.id(),
                incomeUserWithIdDto.email(),
                incomeUserWithIdDto.login(),
                incomeUserWithIdDto.name(),
                incomeUserWithIdDto.birthday()
        );
    }

    private OutcomeUserDto convertToOutcomeUserDto(User user) {
        return new OutcomeUserDto(
                user.getId(),
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday()
        );
    }
}
