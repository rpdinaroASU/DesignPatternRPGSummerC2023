package characters;

/**
 * {@code @Author} Ryan Dinaro
 * @version 6/30
 * This class represents a base character archetype.
 */
public class CharacterBase implements Character{
    private double healthPoints = 0;
    private double manaStored = 0;
    private double healthCap = 0;
    private double manaCap = 0;
    private double staminaCap = 0;
    private double staminaStored = 0;
    private int characterLevel = 1;
    private static final int ITEMSLOTCOUNT = 3;
    private static final int ATTACKSLOTCOUNT = 4;
    private final Item[] itemSlots = new Item[ITEMSLOTCOUNT];
    private final Attack[] attackSlots = new Attack[ATTACKSLOTCOUNT];
    private boolean magicType;
    private boolean physicalType;

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
    public double reduceHealth(double damage) throws IllegalArgumentException {
        if(damage<0) {
            throw new IllegalArgumentException("Damage cannot be less than 0");
        }
        healthPoints -= damage;
        if(healthPoints<0) {
            healthPoints = 0;
        }
        return healthPoints;
    }

    /**
     * Reduce mana by specified amount.
     * @param actionCost the cost of the spell
     * @return the current mana
     */
    @Override
    public double reduceMana(double actionCost) {
        if(actionCost<0) {
            String message = "Mana cost cannot be less than 0";
            throw new IllegalArgumentException(message);
        } else if(actionCost>manaStored) {
            String message = "Mana cannot cost more than mana stored";
            throw new IllegalArgumentException(message);
        }
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
        String message;
        if(actionCost<0) {
            message = "Stamina cost cannot be less than 0";
            throw new IllegalArgumentException(message);
        } else if(actionCost>staminaStored) {
            message = "Stamina cannot cost more than stamina stored";
            throw new IllegalArgumentException(message);
        }
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
        if(slotNumber >= ITEMSLOTCOUNT
                ||slotNumber <= 0) {
            String message = "You only have " + ITEMSLOTCOUNT
                    + " slots. Pick a number between 1 - "
                    + ITEMSLOTCOUNT + ".";
            throw new IllegalArgumentException(message);
        }
        return itemSlots[slotNumber-1];
    }

    /**
     * Return the attack in the attack slot.
     * @param slotNumber the slot number being retrieved
     * @return the attack in the attack slot
     */
    @Override
    public Attack getAttackSlots(int slotNumber)
            throws IllegalArgumentException {
        if(slotNumber >= ATTACKSLOTCOUNT
            || slotNumber <= 0) {
            String message = "You only have " + ATTACKSLOTCOUNT
                    + " slots. Pick a number between 1 - "
                    + ATTACKSLOTCOUNT + ".";
            throw new IllegalArgumentException(message);
        }
        return attackSlots[slotNumber-1];
    }

    /**
     * set the slot to the item specified.
     * @param item the item to set
     * @param slotNumber the slot to set it too
     */
    @Override
    public void setItemSlot(Item item, int slotNumber)
            throws IllegalArgumentException {
        if(slotNumber-1 >= ITEMSLOTCOUNT
        || slotNumber-1 < 0) {
            String message = "You only have " + ITEMSLOTCOUNT
                    + " slots. Pick a number between 1 - "
                    + ITEMSLOTCOUNT + ".";
            throw new IllegalArgumentException(message);
        }
        itemSlots[slotNumber-1] = item;
    }

    /**
     * set the attack slot to the attack specified.
     * @param attack The attack being set
     * @param slotNumber The slot the attack is to be assigned
     */
    @Override
    public void setAttackSlot(Attack attack, int slotNumber)
            throws IllegalArgumentException {
        if(slotNumber-1>= ATTACKSLOTCOUNT) {
            String message = "You only have " + ATTACKSLOTCOUNT
            + " slots. Pick a number between 1 - " + ATTACKSLOTCOUNT + ".";
            throw new IllegalArgumentException(message);
        }
        attackSlots[slotNumber-1] = attack;
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
            throw new IllegalArgumentException("Level "
                    + "must be greater than 0");
        }
        final double curvatureCoefficient = .047;
        final double shiftHorizontal = 50;
        final double exponent = 2;
        final double healthShiftVertical = 110;
        this.healthCap = Math.round(curvatureCoefficient
                * Math.pow(this.getCharacterLevel()+shiftHorizontal,
                        exponent)-healthShiftVertical);
        final double manaShiftVertical = 50;
        this.manaCap = this.getCharacterLevel()+manaShiftVertical;
        final double staminaShiftVertical = 50;
        this.staminaCap = this.getCharacterLevel()+staminaShiftVertical;
        heal();
    }

    /**
     * Heals the player to full health.
     * Used after a floor is cleared and level ups.
     */
    @Override
    public void heal() {
        this.healthPoints = this.healthCap;
        this.manaStored = this.manaCap;
        this.staminaStored = this.staminaCap;
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
    @Override
    public int getItemSlotCount() {
        return ITEMSLOTCOUNT;
    }

    /**
     * Returns the number of attack slots.
     * @return Returns the number of attack slots
     */
    @Override
    public int getAttackSlotCount() {
        return ATTACKSLOTCOUNT;
    }

    /**
     * Returns the current health
     * @return the current health
     */
    @Override
    public double getHealthPoints() {
        return healthPoints;
    }

    /**
     * Sets the current health
     * @param healthPoints the current health
     */
    @Override
    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    /**
     * Returns the current mana
     * @return the current mana
     */
    @Override
    public double getManaPoints() {
        return manaStored;
    }

    /**
     * Sets the current health
     * @param manaPoints the current health
     */
    @Override
    public void setManaPoints(double manaPoints) {
        this.manaStored = manaPoints;
    }
    /**
     * Returns the current stamina
     * @return the current stamina
     */
    @Override
    public double getStaminaPoints() {
        return staminaStored;
    }

    /**
     * Sets the current stamina
     * @param staminaPoints the current stamina
     */
    @Override
    public void setStaminaPoints(double staminaPoints) {
        this.staminaStored = staminaPoints;
    }
}
