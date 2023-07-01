package characters;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    static Enemy enemy;
    @BeforeAll
    static void setUp() throws IllegalAccessException {
        enemy = new Enemy(10,1,1);
        System.out.println(enemy.getEnemyName());
    }

    @Test
    void getExperienceGiven() {
        assertEquals(4.8,enemy.getExperienceGiven());
    }

    @Test
    void getGoldGiven() {
        assertEquals(80,enemy.getGoldGiven());
    }
}