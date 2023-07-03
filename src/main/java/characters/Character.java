package characters;

public interface Character {
    double reduceHealth(double damage) throws IllegalArgumentException;
    double reduceMana(double actionCost);
    double reduceStamina(double actionCost);

    Attack getAttackSlots(int slotNumber) throws IllegalArgumentException;
    void setAttackSlot(Attack attack, int slotNumber)
            throws IllegalArgumentException;
    double getHealthCap();
    double getManaCap();
    double getStaminaCap();
    int getCharacterLevel();
    void setCharacterLevel(int characterLevel) throws IllegalArgumentException;
    boolean isPhysicalType();
    boolean isMagicType();
    void setMagicType(boolean magicType);
    void setPhysicalType(boolean physicalType);
    void setStatCaps(double healthBonus, double manaBonus, double staminaBonus)
            throws IllegalArgumentException;
    void heal();
    int getAttackSlotCount();
    double getHealthPoints();
    void setHealthPoints(double healthPoints);
    double getStaminaPoints();

    double getManaPoints();

    void setManaPoints(double manaPoints);
    void setStaminaPoints(double staminaPoints);

    int getMoveCount();
}
