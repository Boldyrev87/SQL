package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getInvalidUserInfo() {
        Faker faker = new Faker(new Locale("en"));
        String login = faker.name().username();
        String password = faker.internet().password();
        return new AuthInfo(login, password);
    }
}

