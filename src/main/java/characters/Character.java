package characters;

public interface Character {
    public double doAction(int actionNumber);
    public double reduceHealth(double damage);
    public double reduceMana(double actionCost);
    public double reduceStamina(double actionCost);
    public Item[] getItemSlots();
    public Attack[] getAttackSlots();
    public Item[] setItemSlot(Item item, int SlotNumber);
    public Attack[] getAttackSlots(Attack attack, int SlotNumber);
}
