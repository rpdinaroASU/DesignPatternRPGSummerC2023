package characters;


/**
 * {@code @Author} Ryan Dinaro
 * @version 6/29/2023
 * This class represents a player.
 */
public class Player extends CharacterBase {


    private int gold = 0;
    private double experiencePoints = 0;
    private boolean currentType;
    private String playerName = "";
    private final double healthBonus;
    private final double manaBonus;
    private final double staminaBonus;
    private final double goldBonus;
    private final PlayerClasses playerClass;
    private double playerDifficulty;

    /**
     * This initializes a player with the class specification
     *
     * @param classes the player class
     */
    public Player(PlayerClasses classes) {
        this.setAttackSlot(Attack.getMovePool(1,this.isPhysicalType()).get(0),1);
        this.playerClass = classes;
        this.goldBonus = classes.getGoldBonus();
        if(classes.getHealthBonus()<1||classes.getHealthBonus()>2
                ||classes.getManaBonus()<1||classes.getManaBonus()>2
                ||classes.getStaminaBonus()<1||classes.getStaminaBonus()>2) {
            throw new IllegalArgumentException("Invalid arguments");
        }
        this.healthBonus = classes.getHealthBonus();
        this.manaBonus = classes.getManaBonus();
        this.staminaBonus = classes.getStaminaBonus();
    }

    /**
     * Add gold to PLayers account.
     * @param goldGained the amount gained
     * @return the balance of player gold
     */
    public double addGold(double goldGained){
        this.gold += goldGained;
        return this.gold;
    }

    /**
     * Remove gold from player account.
     * @param goldLost the amount of gold lost
     * @return the balance of player gold
     */
    public double removeGold(int goldLost){
        this.gold -= goldLost;
        return this.gold;
    }

    /**
     * Return the gold amount of the character
     * @return gold banked
     */
    public double getGold() {
        return gold;
    }

    /**
     * Adds experience to players account.
     *
     * @param experiencePoints the amount of experience to be added
     */
    public void addExperience(double experiencePoints){
        this.experiencePoints += experiencePoints;
    }

    /**
     * Returns the experience needed to level up.
     * @return the experience needed to level up
     */
    public double getExperienceCap() {
        final double curvatureCoefficient = .1;
        final double verticalShift = 20;
        double experienceToLevelFormula = curvatureCoefficient
                * Math.pow(this.getCharacterLevel(),2) + verticalShift;
        return experienceToLevelFormula;
    }

    /**
     * Check if player can level up.
     * @return if leveled up
     */
    public boolean levelUp() {
        boolean flag = false;
        if(getExperienceCap()<=experiencePoints) {
            flag = true;
            experiencePoints-=getExperienceCap();
            setCharacterLevel(getCharacterLevel()+1);
            this.setStatCaps(healthBonus, manaBonus, staminaBonus);
        }
        /*
         * Swap type learned every battle to effectively randomize
         * attack learned for mixed classes
         */
        if(isMagicType()&&isPhysicalType()) {
            currentType = !currentType;
        }
        return flag;
    }

    /**
     * Sets the player name to the argument.
     * @param playerName the player name
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
    /**
     * Returns the players name.
     * @return player name
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * Returns the gold bonus.
     * @return the gold bonus
     */
    public double getGoldBonus() {
        return goldBonus;
    }

    /**
     * Retrieves the player class.
     * @return the player class
     */
    public PlayerClasses getPlayerClass() {
        return playerClass;
    }

    /**
     * Sets the difficulty level
     * @param difficulty the difficulty level
     */
    public void setDifficulty(double difficulty) {
        this.playerDifficulty = difficulty;
    }

    /**
     * Returns the player difficulty
     * @return the player difficulty
     */
    public double getPlayerDifficulty() {
        return this.playerDifficulty;
    }

    /**
     * Returns the experience points in bank
     * @return the experience points in bank
     */
    public double getExperiencePoints() {
        return experiencePoints;
    }
}
