package characters;

import java.util.HashSet;

/**
 * {@code @Author} Ryan Dinaro
 * @version 6/29/2023
 * This class represents a player.
 */
public class Player extends CharacterBase {


    private int gold = 0;
    private double experiencePoints = 0;
    private boolean currentType;
    private final HashSet<Attack> attackPool;
    private final HashSet<Item> itemPool;


    /**
     * This constructor initiates a player object with stat caps.
     * @param healthCap Maximum health a Player can have
     * @param manaCap Maximum mana a Player can have
     * @param staminaCap Maximum stamina a Player can have
     */
    public Player(double healthCap, double manaCap, double staminaCap)
            throws IllegalArgumentException {
        super(healthCap,manaCap,staminaCap);
        if(healthCap < 0 || manaCap < 0 || staminaCap < 0) {
            throw new IllegalArgumentException("All values must be positive");
        }
        attackPool = new HashSet<Attack>();
        itemPool = new HashSet<Item>();
        this.setCharacterLevel(1);
        this.setStatCaps();
    }

    /**
     * Add gold to PLayers account.
     * @param goldGained the amount gained
     * @return the balance of player gold
     */
    public double addGold(int goldGained){
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
     * Adds experience to players account.
     * @param experiencePoints the amount of experience to be added
     * @return total experience balance
     */
    public double addExperience(double experiencePoints){
        this.experiencePoints += experiencePoints;
        return this.experiencePoints;
    }

    /**
     * Check if player can level up.
     */
    public void levelUp() {
        final double curvatureCoefficient = .1;
        final double verticalShift = 20;
        double experienceToLevelFormula = curvatureCoefficient
                * Math.pow(this.getCharacterLevel(),2) + verticalShift;
        if(experienceToLevelFormula<experiencePoints) {
            attackPool.addAll(Attack.getMovePool(this.getCharacterLevel(),
                    this.isPhysicalType()));
            itemPool.addAll(Item.getItemPool(this.getCharacterLevel()));
            experiencePoints-=experienceToLevelFormula;
            setCharacterLevel(getCharacterLevel()+1);
            this.setStatCaps();
        }
        if(isMagicType()&&isPhysicalType()) {
            currentType = !currentType;
        }

    }
}
