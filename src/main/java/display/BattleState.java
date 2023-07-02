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
        character.reduceHealth(attack.getMaxMagicDamage());
        character.reduceHealth(attack.getMaxAttack());
        double damage = attack.getMaxMagicDamage() + attack.getMaxAttack();
        return damage;
    }

}
