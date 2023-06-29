package characters;

import items.Item;

public interface Player {
    public double addGold(int gain);
    public double removeGold(int gain);
    public double addItem(Item foundItem, int slotNumber);
    public double addItem(int removeItem);
}
