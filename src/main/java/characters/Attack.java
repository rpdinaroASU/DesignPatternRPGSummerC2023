package characters;

public enum Attack {

    /*
     * The enums that follow are the physical attacks learned.
     */
    SWIPE("Swipe", 2, 0, 1, 1, 1, true),
    BASH("Bash", 5, 0, 2, 1, 2,true),
    STING("Sting", 8, 0, 2, 2, 3,true),
    PUMMEL("Pummel", 15, 0, 3, 2, 5,true),
    THRUST("Thrust", 18, 0, 4, 3, 7,true),
    CLOBBER("Clobber", 20, 0, 7, 0, 10,true),
    SEARING_STRIKE("Searing Strike", 22, 5, 5, 4, 11,true),
    ICE_SLASH("Ice Slash", 25, 7, 5, 5, 12,true),
    SHOCK_BLAST("Shock Blast", 30, 10, 6, 5, 14,true),
    RAGING_STORM("Raging Storm Strike", 35, 10, 7, 6, 15,true),
    EARTH_SHAKER("The Earth Shaker", 40, 10, 8, 7, 16,true),
    SMITE("Smite", 50, 10, 10, 10, 20,true),
    HELLFIRE_BLADE("Hellfire Blade", 55, 15, 10, 12, 22, true),
    TEMPEST_FURY("Tempest Fury", 60, 20, 11, 12, 23, true),
    ECLIPSE_EDGE("Eclipse Edge", 65, 25, 12, 13, 24,true),
    LIGHTNING_LASH("Lightning Lash", 70, 30, 13, 14, 25,true),
    VOID_PULSE("Void Pulse", 80, 35, 15, 15, 27,true),
    ASTRAL_AEGIS("Astral Aegis", 90, 40, 17, 16, 28,true),
    NETHER_NOVA("Nether Nova", 95, 45, 19, 18, 29,true),
    DANCE_OF_DEATH("The Dance of Death", 100, 50, 20, 10, 35,true),
    /*
     * These are the magical attacks learned as a mage grows
     */
    SPARK("Spark", 0, 2, 0, 1, 1, false),
    ZAP("Zap", 0, 5, 0, 2, 2, false),
    FLASH("Flash", 0, 8, 0, 2, 3, false),
    // Elemental Spells
    FLAME_SPIRIT("Flame Spirit", 0, 15, 0, 4, 5, false),
    WATER_WHIRL("Water Whirl", 0, 18, 0, 5, 6, false),
    EARTH_PULSE("Earth Pulse", 0, 20, 0, 6, 7, false),
    AIR_VORTEX("Air Vortex", 0, 22, 0, 7, 8, false),
    FROST_BITE("Frost Bite", 0, 25, 0, 8, 9, false),
    LIGHTNING_BOLT("Lightning Bolt", 0, 30, 0, 9, 10, false),
    // Astral Spells
    MOON_BEAM("Moon Beam", 10, 35, 5, 10, 12, false),
    STAR_SHOWER("Star Shower", 15, 40, 7, 11, 14, false),
    GALAXY_WAVE("Galaxy Wave", 20, 45, 9, 12, 16, false),
    COMET_STRIKE("Comet Strike", 25, 50, 10, 13, 18, false),
    NEBULA_CHAIN("Nebula Chain", 30, 55, 12, 14, 20, false),
    SOLAR_NOVA("Solar Nova", 35, 60, 15, 15, 22, false),
    // Deep Spiritual Horrors
    DARK_RITUAL("Dark Ritual", 40, 70, 15, 17, 24, false),
    ELDERS_CURSE("Elder's Curse", 45, 80, 18, 18, 26, false),
    VOID_WAIL("Void Wail", 50, 85, 20, 19, 28, false),
    ABYSSAL_TOUCH("Abyssal Touch", 55, 90, 22, 20, 30, false),
    AWAKEN_THE_ETERNAL_ONE("Awaken the Eternal One", 60, 100, 25, 25, 35, false),

    /*
     *  Below is a little Easter egg for anyone who makes it to level 100,
     *  Notice the damage type is not what you would expect,
     *  just wishing death grants physical damage,
     *  just asking someone politely to stop will cause enough magical damage to kill them.
     */
    ASKING_POLITELY("Asking Politely", 0,10000,0,0,100,true),
    WISHING_DEATH("Wishing Death", 10000,0,0,0,100,false);
    private final String attackName;
    private final double maxAttack;
    private final double maxMagicDamage;
    private final double staminaCost;
    private final double manaCost;
    private final int levelAppears;
    private final boolean isPhysical;

    Attack(String attackName, double maxAttack, double maxMagicDamage, double staminaCost, double manaCost, int levelAppears, boolean isPhysical) {
        this.attackName = attackName;
        this.maxAttack = maxAttack;
        this.maxMagicDamage = maxMagicDamage;
        this.staminaCost = staminaCost;
        this.manaCost = manaCost;
        this.levelAppears = levelAppears;
        this.isPhysical = isPhysical;
    }

    public double getMaxAttack() {
        return maxAttack;
    }

    public double getMaxMagicDamage() {
        return maxMagicDamage;
    }

    public double getStaminaCost() {
        return staminaCost;
    }

    public double getManaCost() {
        return manaCost;
    }

    public int getLevelAppears() {
        return levelAppears;
    }

    public String getAttackName() {
        return attackName;
    }

    public boolean isPhysical() {
        return isPhysical;
    }
}
