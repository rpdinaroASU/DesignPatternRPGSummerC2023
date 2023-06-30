package characters;

public interface Character {
    public double reduceHealth(double damage);
    public double reduceMana(double actionCost);
    public double reduceStamina(double actionCost);
    public Item[] getItemSlots();
    public Attack[] getAttackSlots();
    public void setItemSlot(Item item, int SlotNumber);
    public void setAttackSlot(Attack attack, int SlotNumber);
}
