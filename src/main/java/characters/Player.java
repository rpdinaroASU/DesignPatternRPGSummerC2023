package characters;

public class Player implements Character{
    private Item[] itemSlots = new Item[3];
    private Attack[] attackSlots = new Attack[4];
    private int healthPoints = 0;
    private int gold = 0;
    private double experiencePoints = 0;
    private double manaStored = 0;
    private double healthCap = 0;
    private double manaCap = 0;
    private double staminaCap = 0;
    private double staminaStored = 0;

    public Player(double healthCap, double manaCap, double staminaCap) {
        this.healthCap = healthCap;
        this.manaCap = manaCap;
        this.staminaCap = staminaCap;
    }
    public double addGold(int gainGained){
        return this.gold += gainGained;
    }
    public double removeGold(int gainLost){
        return this.gold -= gainLost;
    }
    public double addExperience(double experiencePoints){
        return this.experiencePoints += experiencePoints;
    }


    @Override
    public double reduceHealth(double damage) {
        return healthPoints -= damage;
    }

    @Override
    public double reduceMana(double actionCost) {
        return manaStored -= actionCost;
    }

    @Override
    public double reduceStamina(double actionCost) {
        return staminaStored -= actionCost;
    }

    @Override
    public Item[] getItemSlots() {
        return itemSlots;
    }

    @Override
    public Attack[] getAttackSlots() {
        return attackSlots;
    }

    @Override
    public void setItemSlot(Item item, int SlotNumber) {
        itemSlots[SlotNumber] = item;
    }

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
