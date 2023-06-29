package characters;

public enum Attack {
    ASKING_POLITELY(0,10000,0,0);
    private final double attack;
    private final double magicDamage;
    private final double staminaCost;
    private final double manaCost;

    Attack(double attack, double magicDamage, double staminaCost, double manaCost) {

        this.attack = attack;
        this.magicDamage = magicDamage;
        this.staminaCost = staminaCost;
        this.manaCost = manaCost;
    }

    public double getAttack() {
        return attack;
    }

    public double getMagicDamage() {
        return magicDamage;
    }

    public double getStaminaCost() {
        return staminaCost;
    }

    public double getManaCost() {
        return manaCost;
    }
}
