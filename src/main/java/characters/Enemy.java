package characters;

import java.util.List;
import java.util.Random;

/**
 * {@code @Author} Ryan Dinaro
 * @version 6/30
 * This class represents an enemy character
 */
public class Enemy extends CharacterBase{
    private final String enemyName;
    private final double experienceGiven;
    private final double goldGiven;

    /**
     * This constructor creates an enemy's name, stats, move pool, and item pool.
     * @param playerLevel the level of the player, must be over 1
     * @param difficulty the level of difficulty 1 - 3
     * @throws IllegalAccessException when params are out of bounds
     */
    public Enemy(double playerLevel, int difficulty, double goldMultiplier) throws IllegalAccessException {
        super();
        this.enemyName = EnemyName.randomEnemyName().toString();
        setStats(playerLevel, difficulty);
        experienceGiven = .6*this.getCharacterLevel();
        goldGiven = goldMultiplier*10*this.getCharacterLevel();
    }

    /**
     * Sets the level of the Enemy based on player level and difficulty.
     * @param playerLevel the players level >= 1
     * @param difficulty the difficulty of the game 1 - 3
     * @throws IllegalAccessException params do not fall in the correct bounds
     */
    private void setStats(double playerLevel, int difficulty) throws IllegalAccessException {
        if(difficulty<=0||difficulty>3) {
            throw new IllegalAccessException("difficulty must be between 1 - 3");
        }
        if(playerLevel<=0) {
            throw new IllegalAccessException("Player Level must be greater than 0");
        }

        setEnemyLevel(playerLevel, difficulty);
        setEnemyType();
        this.setStatCaps();
        setAttacks();
        setItems();
    }

    /**
     * Sets the level of the enemy for varying difficulty.
     * Level 1 - 9 enemy grows slower than you ensuring you have
     * an easier time getting use to the game.
     * Level 10 - 19 enemy grows at a linear rate.
     * Level 20 - 29 enemy grow at a slow exponential rate
     * Level 30+ enemy grows at a greater exponential rate
     * @param playerLevel the level of the player
     * @param difficulty the difficulty ranging from 1-3
     */
    private void setEnemyLevel(double playerLevel, int difficulty) {
        double enemyLevelGrowthRate = 0;
        final double RATE_ONE = .03 * difficulty;
        final double RATE_TWO = 1.0 * difficulty * .8;
        final double RATE_THREE = .03* difficulty;
        final double RATE_FOUR = .025 * difficulty;
        if(playerLevel<10) {
            enemyLevelGrowthRate = RATE_ONE * Math.pow(playerLevel+8,2) - 1.92;
            enemyLevelGrowthRate =Math.ceil(enemyLevelGrowthRate);
        }
        else if(playerLevel<20) {
            enemyLevelGrowthRate = Math.round(RATE_TWO * playerLevel);
        }
        else if(playerLevel<30) {
            enemyLevelGrowthRate = Math.round(RATE_THREE * (Math.pow(playerLevel,2))+8);
        }
        else if(playerLevel>=30) {
            enemyLevelGrowthRate = Math.round(RATE_FOUR*Math.pow(playerLevel,2)+14);
        }
        this.setCharacterLevel((int) enemyLevelGrowthRate);
    }

    /**
     * sets the focus of the enemy - physical XOR magical.
     */
    private void setEnemyType(){
        Random rand = new Random();
        boolean physicalBased = rand.nextBoolean();
        boolean magicalBased = !physicalBased;
        this.setPhysicalType(physicalBased);
        this.setMagicType(magicalBased);
    }
    /**
     * Sets the attacks of this enemy from the available move pool.
     */
    private void setAttacks() {
        List<Attack> attackPool = Attack.getMovePool(this.getCharacterLevel(), this.isPhysicalType());
        Random rand = new Random();
        for(int x = 1; x<=this.getAttackSlotCount(); x++) {
            this.setAttackSlot(attackPool.get(rand.nextInt(attackPool.size())), x);
        }
    }

    /**
     * Sets the items of this enemy from the available item pool.
     */
    private void setItems() {
        List<Item> itemPool = Item.getItemPool(this.getCharacterLevel());
        Random rand = new Random();
        for(int x = 1; x<=this.getItemSlotCount(); x++) {
            this.setItemSlot(itemPool.get(rand.nextInt(itemPool.size())), x);
        }
    }

    /**
     * Returns the enemy's name.
     * @return the enemy's name
     */
    public String getEnemyName() {
        return enemyName;
    }

    /**
     * Returns the amount of experience given for defeat.
     * @return the amount of experience given for defeat
     */
    public double getExperienceGiven() {
        return experienceGiven;
    }

    /**
     * Returns the gold given for a defeat.
     * @return the gold given for a defeat
     */
    public double getGoldGiven() {
        return goldGiven;
    }
}
