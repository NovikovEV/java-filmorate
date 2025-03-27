package ru.yandex.practicum.filmorate.model;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private Integer id;
    private String email;
    private String login;
    private String name;
    private LocalDate birthday;

    public User(Integer id, String email, String login, String name, LocalDate birthday) {
        this.id = id;
        this.email = email;
        this.login = login;
        this.name = name;
        this.birthday = birthday;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof User user)) {
            return false;
        }

        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(login, user.login) && Objects.equals(name, user.name) && Objects.equals(birthday, user.birthday);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + email.hashCode();
        result = 31 * result + login.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + birthday.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}