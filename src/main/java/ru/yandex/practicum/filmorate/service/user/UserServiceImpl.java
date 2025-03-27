package ru.yandex.practicum.filmorate.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.dto.RequestUserDto;
import ru.yandex.practicum.filmorate.dto.ResponseUserDto;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.user.UserStorage;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserStorage userStorage;

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    public UserServiceImpl(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public ResponseUserDto create(RequestUserDto requestUserDto) {
        User user = convertToUser(requestUserDto);
        if (user.getName() == null) {
            user.setName("common");
        }
        return convertToOutcomeUserDto(userStorage.create(user));
    }

    @Override
    public ResponseUserDto update(RequestUserDto requestUserWithIdDto) {
        User user = convertToUser(requestUserWithIdDto);

        User updatedUser = userStorage.update(user).orElseThrow(() -> {
            final String message = "Пользователь с id=: " + requestUserWithIdDto.id() + " не найден";
            log.info(message);
            return new UserNotFoundException(message);
        });

        return convertToOutcomeUserDto(updatedUser);
    }

    @Override
    public List<ResponseUserDto> getAll() {
        return userStorage.getAll().stream()
                .map(this::convertToOutcomeUserDto)
                .toList();
    }

    private User convertToUser(RequestUserDto requestUserDto) {
        return new User(
                requestUserDto.id(),
                requestUserDto.email(),
                requestUserDto.login(),
                requestUserDto.name(),
                requestUserDto.birthday()
        );
    }

    private ResponseUserDto convertToOutcomeUserDto(User user) {
        return new ResponseUserDto(
                user.getId(),
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday()
        );
    }
}
