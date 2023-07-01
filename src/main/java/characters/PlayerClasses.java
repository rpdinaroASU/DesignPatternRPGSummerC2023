package characters;

public enum PlayerClasses {
    Warrior(1.5,1,1.5,1, true, false),
    Thief(1.5,1,1,1.5, true, false),
    Witch(1.5,1.5,1,1, false, true),
    Battlemage(1.25,1.25,1.25,1.25, true, true),
    Paladin(1.35,1.1,1.4,1.15, true, true),
    Ranger(1.4,1.05,1.35,1.2, true, false),
    Necromancer(1.1,1.5,1.15,1.25, false, true),
    Monk(1.3,1.2,1.35,1.15, true, false);
    private final double healthBonus;
    private final double manaBonus;
    private final double staminaBonus;
    private final double goldBonus;
    private final boolean isPhysical;
    private final boolean isMagical;

    /**
     * A player class
     * @param healthBonus the bonus of health
     * @param manaBonus the bonus of mana
     * @param staminaBonus the bonus of stamina
     * @param goldBonus the bonus of gold
     * @param isPhysical if a physical class
     * @param isMagical if a magical class
     */
    PlayerClasses(double healthBonus, double manaBonus, double staminaBonus,
                  double goldBonus, boolean isPhysical, boolean isMagical) {

        this.healthBonus = healthBonus;
        this.manaBonus = manaBonus;
        this.staminaBonus = staminaBonus;
        this.goldBonus = goldBonus;
        this.isPhysical = isPhysical;
        this.isMagical = isMagical;
    }

    /**
     * Returns the health bonus
     * @return the health bonus
     */
    public double getHealthBonus() {
        return healthBonus;
    }

    /**
     * Returns the mana bonus
     * @return the mana bonus
     */
    public double getManaBonus() {
        return manaBonus;
    }

    /**
     * Returns the stamina bonus
     * @return the stamina bonus
     */
    public double getStaminaBonus() {
        return staminaBonus;
    }

    /**
     * Returns the gold bonus
     * @return the gold bonus
     */
    public double getGoldBonus() {
        return goldBonus;
    }

    /**
     * Returns true if physical class
     * @return true if physical class
     */
    public boolean isPhysical() {
        return isPhysical;
    }

    /**
     * Returns true if magical class
     * @return true if magical class
     */
    public boolean isMagical() {
        return isMagical;
    }
}
