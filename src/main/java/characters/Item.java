package characters;

public enum Item {
    PLACE_HOLDER(0,0,0,0,0);
    private final int damage;
    private final int intelligence;
    private final int defence;
    private final int resist;
    private final int value;
    Item(int damage, int intelligence, int defence, int resist, int value) {
        this.damage = damage;
        this.intelligence = intelligence;
        this.defence = defence;
        this.resist = resist;
        this.value = value;
    }

    public int getDamage() {
        return damage;
    }

    public int getDefence() {
        return defence;
    }

    public int getResist() {
        return resist;
    }

    public int getValue() {
        return value;
    }

    public int getIntelligence() {
        return intelligence;
    }
}
