
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import characters.Enemy;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EnemyTest {
    private static Enemy enemy;
    @BeforeAll
    static void setUp() throws IllegalAccessException {
        final int playerLevel = 10;
        final int difficulty = 1;
        final int goldBonus = 1;
        enemy = new Enemy(playerLevel,difficulty,goldBonus);
    }

    @Test
    void getExperienceGiven() {
        final double experinceExpected = 4.8;
        assertEquals(experinceExpected,enemy.getExperienceGiven());
    }
}
