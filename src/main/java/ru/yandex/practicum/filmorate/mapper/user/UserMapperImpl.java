package ru.yandex.practicum.filmorate.mapper.user;

import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.dto.user.RequestUserDto;
import ru.yandex.practicum.filmorate.dto.user.ResponseUserDto;
import ru.yandex.practicum.filmorate.model.User;

@Component
public class UserMapperImpl implements UserMapper {
    @Override
    public User convertRequestUserDtoToUser(RequestUserDto requestUserDto) {
        return new User(
                requestUserDto.id(),
                requestUserDto.email(),
                requestUserDto.login(),
                requestUserDto.name(),
                requestUserDto.birthday()
        );
    }

    @Override
    public ResponseUserDto convertUserToResponseUserDto(User user) {
        return new ResponseUserDto(
                user.getId(),
                user.getEmail(),
                user.getLogin(),
                user.getName(),
                user.getBirthday()
        );
    }
}
