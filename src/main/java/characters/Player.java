package characters;

/**
 * {@code @Author} Ryan Dinaro
 * @version 6/29/2023
 * This class represents a player.
 */
public class Player implements Character{
    private final int ITEM_SLOT_COUNT = 3;
    private final int ATTACK_SLOT_COUNT = 4;
    private final Item[] itemSlots = new Item[ITEM_SLOT_COUNT];
    private final Attack[] attackSlots = new Attack[ATTACK_SLOT_COUNT];
    private int healthPoints = 0;
    private int gold = 0;
    private double experiencePoints = 0;
    private double manaStored = 0;
    private double healthCap = 0;
    private double manaCap = 0;
    private double staminaCap = 0;
    private double staminaStored = 0;

    /**
     * This constructor initiates a player object with stat caps.
     * @param healthCap Maximum health a Player can have
     * @param manaCap Maximum mana a Player can have
     * @param staminaCap Maximum stamina a Player can have
     */
    public Player(double healthCap, double manaCap, double staminaCap) {
        this.healthCap = healthCap;
        this.manaCap = manaCap;
        this.staminaCap = staminaCap;
    }

    /**
     * Add gold to PLayers account.
     * @param goldGained the amount gained
     * @return the balance of player gold
     */
    public double addGold(int goldGained){
        return this.gold += goldGained;
    }

    /**
     * Remove gold from player account.
     * @param goldLost the amount of gold lost
     * @return the balance of player gold
     */
    public double removeGold(int goldLost){
        return this.gold -= goldLost;
    }

    /**
     * Adds experience to players account.
     * @param experiencePoints the amount of experience to be added
     * @return total experience balance
     */
    public double addExperience(double experiencePoints){
        return this.experiencePoints += experiencePoints;
    }

    /**
     * reduces the health by a specified amount.
     * @param damage the damage taken
     * @return the current health
     */
    @Override
    public double reduceHealth(double damage) {
        return healthPoints -= damage;
    }

    /**
     * Reduce mana by specified amount.
     * @param actionCost the cost of the spell
     * @return the current mana
     */
    @Override
    public double reduceMana(double actionCost) {
        return manaStored -= actionCost;
    }

    /**
     * Reduce the stamina by the specified amount.
     * @param actionCost the cost of the action
     * @return the current stamina
     */
    @Override
    public double reduceStamina(double actionCost) {
        return staminaStored -= actionCost;
    }

    /**
     * Returns the item in the slot number.
     * @param SlotNumber the slot number being retrieved
     * @return the item in the item slot
     */
    @Override
    public Item getItemSlots(int SlotNumber) {
        return itemSlots[SlotNumber];
    }

    /**
     * Return the attack in the attack slot.
     * @param SlotNumber the slot number being retrieved
     * @return the attack in the attack slot
     */
    @Override
    public Attack getAttackSlots(int SlotNumber) {
        return attackSlots[SlotNumber];
    }

    /**
     * set the slot to the item specified.
     * @param item the item to set
     * @param SlotNumber the slot to set it too
     */
    @Override
    public void setItemSlot(Item item, int SlotNumber) {
        itemSlots[SlotNumber] = item;
    }

    /**
     * set the attack slot to the attack specified
     * @param attack
     * @param SlotNumber
     */
    @Override
    public void setAttackSlot(Attack attack, int SlotNumber) {
        attackSlots[SlotNumber] = attack;
    }

    
    public double getHealthCap() {
        return healthCap;
    }

    public void setHealthCap(double healthCap) {
        this.healthCap = healthCap;
    }

    public double getManaCap() {
        return manaCap;
    }

    public void setManaCap(double manaCap) {
        this.manaCap = manaCap;
    }

    public double getStaminaCap() {
        return staminaCap;
    }

    public void setStaminaCap(double staminaCap) {
        this.staminaCap = staminaCap;
    }
}
