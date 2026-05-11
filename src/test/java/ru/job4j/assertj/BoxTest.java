package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Tetrahedron");
    }

    @Test
    void getNumberOfVertices() {
        Box box = new Box(4, 10);
        int count = box.getNumberOfVertices();
        assertThat(count).isEqualTo(4);
    }

    @Test
    void isExist() {
        Box box = new Box(4, 10);
        boolean count = box.isExist();
        assertThat(count).isEqualTo(true);
    }

    @Test
    void getArea() {
        Box box = new Box(4, 10);
        double area = box.getArea();
        assertThat(area).isEqualTo(173.2, offset(0.1));
    }
}