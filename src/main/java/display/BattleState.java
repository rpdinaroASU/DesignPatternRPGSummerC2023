package display;

import characters.CharacterBase;
import characters.Attack;

import java.util.ArrayList;

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

    /**
     * This method returns a list of attacks available for characters
     * to use. This again allows for the Decorator Class to shine
     * @param character The character to inquire attacks
     * @return the array of attacks available
     */
    protected Attack[] getCharacterAttacks(CharacterBase character) {
        ArrayList<Attack> characterAttacks = new ArrayList<Attack>();
        Attack[] characterArr;
        for(int x = 0; x < character.getAttackSlotCount(); x++) {
            if(character.getAttackSlots(x)!=null)
                characterAttacks.add(character.getAttackSlots(x));
        }
        characterArr = characterAttacks.toArray(new Attack[0]);
        return characterArr;
    }
}
