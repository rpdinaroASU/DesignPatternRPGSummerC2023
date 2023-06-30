package characters;

/**
 * {@code @Author} Ryan Dinaro
 * @version 6/30
 * This class represents a base character archetype.
 */
public class CharacterBase implements Character{
    private int healthPoints = 0;
    private double manaStored = 0;
    private double healthCap = 0;
    private double manaCap = 0;
    private double staminaCap = 0;
    private double staminaStored = 0;
    private int characterLevel = 0;
    private final int itemSlotCount = 3;
    private final int attackSlotCount = 4;
    private final Item[] itemSlots = new Item[itemSlotCount];
    private final Attack[] attackSlots = new Attack[attackSlotCount];
    private boolean magicType;
    private boolean physicalType;
    private boolean currentType;

    /**
     * This default constructor creates an empty object to be modified.
     */
    public CharacterBase() {};

    /**
     * This constructor initiates a CharacterBase object with stat caps.
     * @param healthCap Maximum health a Player can have
     * @param manaCap Maximum mana a Player can have
     * @param staminaCap Maximum stamina a Player can have
     */
    public CharacterBase(double healthCap, double manaCap,
                         double staminaCap) throws IllegalArgumentException {
        if(healthCap < 0 || manaCap < 0 || staminaCap < 0) {
            throw new IllegalArgumentException("All values must be positive");
        }
        this.healthCap = healthCap;
        this.manaCap = manaCap;
        this.staminaCap = staminaCap;
    }

    /**
     * reduces the health by a specified amount.
     * @param damage the damage taken
     * @return the current health
     */
    @Override
    public double reduceHealth(double damage) {
        healthPoints -= damage;
        return healthPoints;
    }

    /**
     * Reduce mana by specified amount.
     * @param actionCost the cost of the spell
     * @return the current mana
     */
    @Override
    public double reduceMana(double actionCost) {
        manaStored -= actionCost;
        return manaStored;
    }

    /**
     * Reduce the stamina by the specified amount.
     * @param actionCost the cost of the action
     * @return the current stamina
     */
    @Override
    public double reduceStamina(double actionCost) {
        staminaStored -= actionCost;
        return staminaStored;
    }

    /**
     * Returns the item in the slot number.
     * @param slotNumber the slot number being retrieved
     * @return the item in the item slot
     */
    @Override
    public Item getItemSlots(int slotNumber) throws IllegalArgumentException {
        if(slotNumber<= this.itemSlotCount) {
            String message = "You only have " + this.itemSlotCount
                    + " slots. Pick a number between 1 - "
                    + this.itemSlotCount + ".";
            throw new IllegalArgumentException(message);
        }
        return itemSlots[slotNumber];
    }

    /**
     * Return the attack in the attack slot.
     * @param slotNumber the slot number being retrieved
     * @return the attack in the attack slot
     */
    @Override
    public Attack getAttackSlots(int slotNumber)
            throws IllegalArgumentException {
        if(slotNumber<= this.attackSlotCount) {
            String message = "You only have " + this.attackSlotCount
                    + " slots. Pick a number between 1 - "
                    + this.attackSlotCount + ".";
            throw new IllegalArgumentException(message);
        }
        return attackSlots[slotNumber];
    }

    /**
     * set the slot to the item specified.
     * @param item the item to set
     * @param slotNumber the slot to set it too
     */
    @Override
    public void setItemSlot(Item item, int slotNumber)
            throws IllegalArgumentException {
        if(slotNumber<= this.itemSlotCount) {
            String message = "You only have " + this.itemSlotCount
                    + " slots. Pick a number between 1 - "
                    + this.itemSlotCount + ".";
            throw new IllegalArgumentException(message);
        }
        itemSlots[slotNumber] = item;
    }

    /**
     * set the attack slot to the attack specified.
     * @param attack The attack being set
     * @param slotNumber The slot the attack is to be assigned
     */
    @Override
    public void setAttackSlot(Attack attack, int slotNumber)
            throws IllegalArgumentException {
        if(slotNumber<= this.attackSlotCount) {
            String message = "You only have " + this.attackSlotCount
            + " slots. Pick a number between 1 - " + this.attackSlotCount + ".";
            throw new IllegalArgumentException(message);
        }
        attackSlots[slotNumber] = attack;
    }

    /**
     * Returns the health cap.
     * @return the health cap of the character
     */
    @Override
    public double getHealthCap() {
        return healthCap;
    }

    /**
     * sets the characters stats based on level.
     * Uses formulas to make a round last a few turns
     * @throws IllegalArgumentException if level is invalid
     */
    @Override
    public void setStatCaps() throws IllegalArgumentException {
        if(characterLevel < 1) {
            throw new IllegalArgumentException("Level is negative, "
                    + "must be greater than 0");
        }
        final double curvatureCoefficient = .047;
        final double shiftHorizontal = 50;
        final double exponent = 2;
        final double healthShiftVertical = 110;
        this.healthCap = (int) Math.round(curvatureCoefficient
                * Math.pow(this.getCharacterLevel()+shiftHorizontal,
                        exponent)-healthShiftVertical);
        final double manaShiftVertical = 110;
        this.manaCap = this.getCharacterLevel()+manaShiftVertical;
        final double staminaShiftVertical = 110;
        this.staminaCap = this.getCharacterLevel()+staminaShiftVertical;
        heal();
    }

    /**
     * Heals the player to full health.
     * Used after a floor is cleared and level ups.
     */
    @Override
    public void heal() {
        this.healthPoints = (int) this.healthCap;
        this.manaStored = (int) this.manaCap;
        this.staminaStored = (int) this.staminaCap;
    }

    /**
     * Returns the mana cap.
     * @return the mana cap of the character
     */
    @Override
    public double getManaCap() {
        return manaCap;
    }


    /**
     * Returns the stamina cap.
     * @return the stamina cap of the character
     */
    @Override
    public double getStaminaCap() {
        return staminaCap;
    }


    /**
     * The level of the character.
     * @return the level of the character
     */
    @Override
    public int getCharacterLevel() {
        return characterLevel;
    }

    /**
     * Sets the level of the character.
     * @param characterLevel the level of the character
     */
    @Override
    public void setCharacterLevel(int characterLevel)
            throws IllegalArgumentException {
        if(characterLevel <= 0) {
            String message = "Character's level is too low, must be over 0";
            throw new IllegalArgumentException(message);
        }
        this.characterLevel = characterLevel;
    }

    /**
     * Returns true if physical type.
     * @return if physical type
     */
    @Override
    public boolean isPhysicalType(){
        return physicalType;
    }

    /**
     * Returns true if magic type.
     * @return if magic type
     */
    @Override
    public boolean isMagicType() {
        return magicType;
    }

    /**
     * Sets the magic type.
     * @param magicType the magic type
     */
    @Override
    public void setMagicType(boolean magicType) {
        this.magicType = magicType;
    }

    /**
     * Sets physical type.
     * @param physicalType the physical type
     */
    @Override
    public void setPhysicalType(boolean physicalType) {
        this.physicalType = physicalType;
    }

    /**
     * Returns the number of item slots.
     * @return the number of item slots
     */
    protected int getItemSlotCount() {
        return this.itemSlotCount;
    }

    /**
     * Returns the number of attack slots.
     * @return Returns the number of attack slots
     */
    protected int getAttackSlotCount() {
        return this.attackSlotCount;
    }
}
