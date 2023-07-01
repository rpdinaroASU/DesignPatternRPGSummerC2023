package display;

import characters.*;
import characters.Character;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class BattleState {
    private final static double attackPowerVariance = .25;
    protected static Random rand;
    protected final Scanner scan;

    public BattleState(Player player, Enemy enemy) {
        rand = new Random();
        scan = new Scanner(System.in, StandardCharsets.UTF_8);
    }
    /**
     * A method to damage a character.
     * In this method especially the decorator class gets to shine.
     * There is no need to separate the enemy and character damage
     * @param character the character to be damaged
     * @param attack the attack used
     */
    protected double doDamage(CharacterBase character, Attack attack) {
        int lowerMagicDamageAmount = (int) Math.ceil(attack.getMaxMagicDamage()
                * (1-attackPowerVariance));
        int upperMagicDamageAmount = (int) Math.ceil(attack.getMaxMagicDamage()
                - lowerMagicDamageAmount);
        double magicDamage;
        if(upperMagicDamageAmount!=0) {
            magicDamage = rand.nextInt(upperMagicDamageAmount)
                    + lowerMagicDamageAmount;
        } else {
            magicDamage = lowerMagicDamageAmount;
        }

        int lowerPhysicalDamageAmount = (int) Math.ceil(attack.getMaxAttack()
                * (1-attackPowerVariance));
        int upperPhysicalDamageAmount = (int) Math.ceil(attack.getMaxAttack()
                - lowerPhysicalDamageAmount);
        double physicalDamage;
        if(upperMagicDamageAmount!=0) {
            physicalDamage = rand.nextInt(upperPhysicalDamageAmount)
                    + lowerPhysicalDamageAmount;
        } else {
            physicalDamage = lowerPhysicalDamageAmount;
        }
        character.reduceHealth(magicDamage);
        character.reduceHealth(physicalDamage);
        double damage = magicDamage + physicalDamage;
        return damage;
    }

}
