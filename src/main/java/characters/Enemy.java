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
    private static final int MAXDIFFICULTY = 3;
    private final Random rand;

    /**
     * This constructor creates an enemy's name, stats,
     * move pool, and item pool.
     * @param playerLevel the level of the player, must be over 1
     * @param difficulty the level of difficulty 1 - 3
     * @throws IllegalArgumentException when params are out of bounds
     */
    public Enemy(double playerLevel, double difficulty,
                 double goldMultiplier) throws IllegalArgumentException {
        super();
        rand = new Random();
        this.enemyName = EnemyName.randomEnemyName().toString();
        setStats(playerLevel, difficulty);
        final double experienceMultiplier = .6;
        experienceGiven = experienceMultiplier*this.getCharacterLevel()*10;
        final double goldCoefficient = 10;
        goldGiven = goldCoefficient*goldMultiplier*this.getCharacterLevel();
    }

    /**
     * Sets the level of the Enemy based on player level and difficulty.
     * @param playerLevel the players level >= 1
     * @param difficulty the difficulty of the game 1 - 3
     * @throws IllegalArgumentException params do not fall in the correct bounds
     */
    private void setStats(double playerLevel, double difficulty)
            throws IllegalArgumentException {
        if(difficulty<=0||difficulty>MAXDIFFICULTY) {
            String message = "difficulty must be between 1 - 3";
            throw new IllegalArgumentException(message);
        }
        if(playerLevel<=0) {
            String message = "Player Level must be greater than 0";
            throw new IllegalArgumentException(message);
        }

        setEnemyLevel(playerLevel, difficulty);
        setEnemyType();
        this.setStatCaps(1,1,1);
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
    private void setEnemyLevel(double playerLevel, double difficulty) {
        double enemyLevelGrowthRate = 0;
        /*
        The coefficient of enemy level formula is variable based
        on the difficulty level
         */
        final double rateOne = .03;
        final double rateTwo = 1.0 * difficulty *.25 * .8;
        final double rateThree = .03* difficulty *.75;
        final double rateFour = .025 * difficulty;
        /*
        The level bands between each formula,
        as the level passes the band the formula
        switches as a piecewise formula
         */
        final int levelBandOne = 10;
        final int levelBandTwo = 20;
        final int levelBandThree = 30;
        final int levelBandFour = 40;

        final double b1HorizontalShift = 8;
        final double b1VerticalShift = -1.92;
        final double b3VerticalShift = 8;
        final double b4VerticalShift = 14;

        if(playerLevel<levelBandOne) {
            enemyLevelGrowthRate = Math.ceil(rateOne * Math.pow(playerLevel
                    + b1HorizontalShift,2) + b1VerticalShift);
        } else if(playerLevel<levelBandTwo) {
            enemyLevelGrowthRate = Math.round(rateTwo * playerLevel);
        } else if(playerLevel<levelBandThree) {
            enemyLevelGrowthRate = Math.round(rateThree
                    * (Math.pow(playerLevel,2))+b3VerticalShift);
        } else {
            enemyLevelGrowthRate = Math.round(rateFour
                    *Math.pow(playerLevel,2)+b4VerticalShift);
        }
        this.setCharacterLevel((int) enemyLevelGrowthRate);
    }

    /**
     * sets the focus of the enemy - physical XOR magical.
     */
    private void setEnemyType(){
        boolean physicalBased = rand.nextBoolean();
        boolean magicalBased = !physicalBased;
        this.setPhysicalType(physicalBased);
        this.setMagicType(magicalBased);
    }
    /**
     * Sets the attacks of this enemy from the available move pool.
     */
    private void setAttacks() {
        List<Attack> attackPool = Attack.getMovePool(this.getCharacterLevel(),
                this.isPhysicalType());
        for(int x = 1; x<=this.getAttackSlotCount(); x++) {
            this.setAttackSlot(attackPool.get(rand.nextInt(attackPool.size())),
                    x);
        }
    }

    /**
     * Sets the items of this enemy from the available item pool.
     *
     */
    private void setItems() {
        List<Item> itemPool = Item.getItemPool(this.getCharacterLevel());
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
