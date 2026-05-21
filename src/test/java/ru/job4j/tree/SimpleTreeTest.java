package ru.job4j.tree;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SimpleTreeTest {
    @Test
    void whenTreeIsBinaryThenReturnTrue() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        /* Добавляем по два ребенка — это разрешено */
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(2, 4);
        tree.add(2, 5);

        assertThat(tree.isBinary()).isTrue();
    }

    @Test
    void whenTreeIsNotBinaryThenReturnFalse() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        /* Добавляем три ребенка к одному родителю (узлу 1) — это нарушение */
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);

        assertThat(tree.isBinary()).isFalse();
    }
}