package characters;

public interface Character {
    double reduceHealth(double damage);
    double reduceMana(double actionCost);
    double reduceStamina(double actionCost);
    Item getItemSlots(int slotNumber) throws IllegalArgumentException;
    Attack getAttackSlots(int slotNumber) throws IllegalArgumentException;
    void setItemSlot(Item item, int slotNumber) throws IllegalArgumentException;
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
    void setStatCaps() throws IllegalArgumentException;
    void heal();
}
