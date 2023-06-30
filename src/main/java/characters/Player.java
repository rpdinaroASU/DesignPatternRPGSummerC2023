package characters;

/**
 * {@code @Author} Ryan Dinaro
 * @version 6/29/2023
 * This class represents a player.
 */
public class Player extends CharacterBase {


    private int gold = 0;
    private double experiencePoints = 0;


    /**
     * This constructor initiates a player object with stat caps.
     * @param healthCap Maximum health a Player can have
     * @param manaCap Maximum mana a Player can have
     * @param staminaCap Maximum stamina a Player can have
     */
    public Player(double healthCap, double manaCap, double staminaCap) throws IllegalArgumentException {
        super(healthCap,manaCap,staminaCap);
        if(healthCap < 0 || manaCap < 0 || staminaCap < 0) {
            throw new IllegalArgumentException("All values must be positive");
        }
        this.setCharacterLevel(1);
        this.setStatCaps();
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
     * Check if player can level up.
     */
    public void levelUp() {
        double experienceToLevelFormula = .1 * Math.pow(this.getCharacterLevel(),2) +20;
        if(experienceToLevelFormula<experiencePoints) {
            experiencePoints-=experienceToLevelFormula;
            setCharacterLevel(getCharacterLevel()+1);
            this.setStatCaps();
        }

    }
}
