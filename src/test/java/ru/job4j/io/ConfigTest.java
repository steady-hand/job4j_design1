package ru.job4j.io;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ConfigTest {
    @Test
    void whenPairWithoutComment() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("hibernate.connection.password")).isEqualTo("password");
    }
    @Test
    void whenValueContainsEqualsSign() {
        String path = "data/app.properties";
        Config config = new Config(path);
        config.load();
        // Проверяем, что split("=", 2) отработал корректно и не отрезал часть URL
        assertThat(config.value("hibernate.connection.url"))
                .isEqualTo("jdbc:postgresql://127.0.0.1:5432/trackstudio");
    }
}