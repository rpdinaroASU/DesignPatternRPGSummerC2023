package display;

import characters.CharacterBase;
import characters.Attack;

/**
 * This class allows the two Battle states to share a generic
 * method.
 * @author Ryan Dinaro
 * @version 7/1/2023
 */
public class BattleState extends UIStates{
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
