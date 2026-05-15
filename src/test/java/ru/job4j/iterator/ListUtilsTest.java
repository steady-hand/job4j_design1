package ru.job4j.iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ListUtilsTest {
    private List<Integer> input;

    @BeforeEach
    void setUp() {
        input = new ArrayList<>(Arrays.asList(1, 3));
    }

    @Test
    void whenAddBefore() {
        ListUtils.addBefore(input, 1, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenAddAfter() {
        ListUtils.addAfter(input, 0, 2);
        assertThat(input).hasSize(3).containsSequence(1, 2, 3);
    }

    @Test
    void whenRemoveIfEven() {
        ListUtils.removeIf(input, number -> number % 2 != 0);
        assertThat(input).hasSize(0);
    }

    @Test
    void whenReplaceIfOddToZero() {
        ListUtils.replaceIf(input, number -> number % 2 != 0, 0);
        assertThat(input).hasSize(2).containsSequence(0, 0);
    }

    @Test
    void whenRemoveAllElements() {
        List<Integer> elementsToRemove = Arrays.asList(1, 4, 10);
        ListUtils.removeAll(input, elementsToRemove);
        assertThat(input).hasSize(1).containsSequence(3);
    }
}