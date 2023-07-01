package characters;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CharacterBaseTest {

    CharacterBase base;
    CharacterBase base2;
    @BeforeEach
    void setUp() {
        base = new CharacterBase(10,10,10);
        base2 = new CharacterBase(-1,10,10);
    }

    @Test
    void reduceHealth() {
    }

    @Test
    void reduceMana() {
    }

    @Test
    void reduceStamina() {
    }

    @Test
    void getItemSlots() {
    }

    @Test
    void getAttackSlots() {
    }

    @Test
    void setItemSlot() {
    }

    @Test
    void setAttackSlot() {
    }

    @Test
    void getHealthCap() {
    }

    @Test
    void setStatCaps() {
    }

    @Test
    void heal() {
    }

    @Test
    void getManaCap() {
    }

    @Test
    void getStaminaCap() {
    }

    @Test
    void getCharacterLevel() {
    }

    @Test
    void setCharacterLevel() {
    }

    @Test
    void isPhysicalType() {
    }

    @Test
    void isMagicType() {
    }

    @Test
    void setMagicType() {
    }

    @Test
    void setPhysicalType() {
    }

    @Test
    void getItemSlotCount() {
    }

    @Test
    void getAttackSlotCount() {
    }
}