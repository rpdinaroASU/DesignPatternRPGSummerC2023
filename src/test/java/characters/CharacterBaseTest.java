
import characters.CharacterBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CharacterBaseTest {

    private CharacterBase base;

    @BeforeEach
    void setUp() {
        final int health = 10;
        final int mana = 10;
        final int stamina = 10;
        base = new CharacterBase(health,mana,stamina);
    }

    @Test
    void invalidCharacterBase() {
        assertThrows(IllegalArgumentException.class, () -> {
            final int health = -1;
            final int mana = 10;
            final int stamina = 10;
            new CharacterBase(health,mana,stamina);
        });
    }
    @Test
    void reduceHealth() {
        final int reduceAmount = 5;
        final int expected = 5;
        base.reduceHealth(reduceAmount);
        assertEquals(expected,base.getHealthPoints());
    }
    @Test
    void illegalReduceHealth() {
        assertThrows(IllegalArgumentException.class, () -> {
            final int reduceAmount = -5;
            base.reduceHealth(reduceAmount);
        });
    }
    @Test
    void overkillReduceHealth() {
        final int expectedFinalHealth = 0;
        final int damage = 10000;
        assertEquals(expectedFinalHealth,base.reduceHealth(damage));
    }

    @Test
    void overkillReduceMana() {
        final int manaReduction = 10000;
        assertThrows(IllegalArgumentException.class, () -> {
            base.reduceMana(manaReduction);
        });
    }

    @Test
    void overkillReduceStamina() {
        final int staminaReduction = 10000;
        assertThrows(IllegalArgumentException.class, () -> {
            base.reduceStamina(staminaReduction);
        });
    }


    @Test
    void setStatCaps() {
        final double healthBonus = 1;
        final double manaBonus = 1;
        final double staminaBonus = 1;
        base.setStatCaps(healthBonus,manaBonus,staminaBonus);
        final int expectedHealth = 12;
        assertEquals(expectedHealth,base.getHealthCap());
        final double expectedStaminaAndMana = 51;
        assertEquals(expectedStaminaAndMana,base.getStaminaCap());
        assertEquals(expectedStaminaAndMana,base.getManaCap());
        assertEquals(base.getHealthPoints(),base.getHealthCap());
        assertEquals(base.getManaPoints(),base.getManaCap());
        assertEquals(base.getStaminaPoints(),base.getStaminaCap());
    }
    @Test
    void getCharacterLevel() {
        final int expectedPlayerLevel = 1;
        assertEquals(expectedPlayerLevel, base.getCharacterLevel());
    }

    @Test
    void setCharacterLevel() {
        assertThrows(IllegalArgumentException.class, () -> {
            final int invalidLevel = 0;
            base.setCharacterLevel(invalidLevel);
        });
    }
}
