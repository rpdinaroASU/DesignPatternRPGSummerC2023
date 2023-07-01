
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CharacterBaseTest {

    private CharacterBase base;

    @BeforeEach
    void setUp() {
        int health = 10;
        int mana = 10;
        int stamina = 10;
        base = new CharacterBase(health,mana,stamina);
    }

    @Test
    void invalidCharacterBase() {
        assertThrows(IllegalArgumentException.class, () -> {
            int health = -1;
            int mana = 10;
            int stamina = 10;
            new CharacterBase(health,mana,stamina);
        });
    }
    @Test
    void reduceHealth() {
        int reduceAmount = 5;
        int expected = 5;
        base.reduceHealth(reduceAmount);
        assertEquals(expected,base.getHealthPoints());
    }
    @Test
    void illegalReduceHealth() {
        assertThrows(IllegalArgumentException.class, () -> {
            int reduceAmount = -5;
            base.reduceHealth(reduceAmount);
        });
    }
    @Test
    void overkillReduceHealth() {
        assertEquals(0,base.reduceHealth(1000));
    }

    @Test
    void overkillReduceMana() {
        assertThrows(IllegalArgumentException.class, () -> {
            base.reduceMana(50000);
        });
    }

    @Test
    void overkillReduceStamina() {
        assertThrows(IllegalArgumentException.class, () -> {
            base.reduceStamina(50000);
        });
    }

    @Test
    void getItemSlots() {
        base.setItemSlot(Item.MITHRIL_CUIRASS, 1);
        assertEquals(base.getItemSlots(1), Item.MITHRIL_CUIRASS);
    }
    @Test
    void getItemBellowSlots() {
        assertThrows(IllegalArgumentException.class, () -> {
            base.getItemSlots(0);
        });
    }
    @Test
    void getItemAboveSlots() {
        assertThrows(IllegalArgumentException.class, () -> {
            base.getItemSlots(100);
        });
    }

    @Test
    void setStatCaps() {
        base.setStatCaps();
        assertEquals(12,base.getHealthCap());
        assertEquals(51,base.getStaminaCap());
        assertEquals(51,base.getManaCap());
        assertEquals(base.getHealthPoints(),base.getHealthCap());
        assertEquals(base.getManaPoints(),base.getManaCap());
        assertEquals(base.getStaminaPoints(),base.getStaminaCap());
    }
    @Test
    void getCharacterLevel() {
        assertEquals(1, base.getCharacterLevel());
    }

    @Test
    void setCharacterLevel() {
        assertThrows(IllegalArgumentException.class, () -> {
            base.setCharacterLevel(0);
        });
    }
}
