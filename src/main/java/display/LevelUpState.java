package display;

import characters.Attack;
import characters.Player;

import java.util.List;

/**
 * Heals the player and adjusts the move pool
 * @author Ryan Dinaro
 * @version 7/1/2023
 */
public class LevelUpState extends UIStates{
    /**
     * This State is activated when a player levels up
     * Heals the player and adjusts the move pool
     * @param playerCharacter the player to level up
     */
    public LevelUpState(Player playerCharacter) {
        outputMessage("\nYou leveled up\n");
        //This condition checks that the move pool changed in size
        //before making the user rebuild the move pool.
        if(Attack.getMovePool(playerCharacter.getCharacterLevel()-1,
                playerCharacter.isPhysicalType()).size()
            != Attack.getMovePool(playerCharacter.getCharacterLevel(),
                playerCharacter.isPhysicalType()).size()) {
            //This populates the attack pool list
            List<Attack> attackPool = Attack.getMovePool(
                    playerCharacter.getCharacterLevel(),
                    playerCharacter.isPhysicalType());
            //This lists all attacks and stats behind the attacks
            String message = "";
            for (int x = attackPool.size() - 1; x >= 0; x--) {
                message = "Attack: (" + (attackPool.size() - x) + ") - "
                        + attackPool.get(x).getAttackName() + "\t"
                        + " Physical Attack: "
                        + (int) attackPool.get(x).getMaxAttack() + "\t"
                        + " Magical Attack: "
                        + (int) attackPool.get(x).getMaxMagicDamage() + "\t"
                        + " Stamina Cost: "
                        + (int) attackPool.get(x).getStaminaCost() + "\t"
                        + " Mana Cost: "
                        + (int) attackPool.get(x).getManaCost();
            }
            //minimize the number of attack placements
            int maxSlot = (attackPool.size()
                    < playerCharacter.getAttackSlotCount())
                    ? attackPool.size() : playerCharacter.getAttackSlotCount();
            //solicits a move for each attack slot
            for (int y = 0; y < maxSlot; y++) {
                int choiceNumber = -1;
                outputMessage("Select an attack for slot #" + (y + 1));
                while (choiceNumber < 0 || choiceNumber > attackPool.size()) {
                    String input = inputScan(message);
                    try {
                        choiceNumber = Integer.parseInt(input);
                    } catch (Exception e) {
                        outputMessage("Enter a valid input.");
                        choiceNumber = -1;
                        y--;
                        continue;
                    }
                    if (choiceNumber > 0 && choiceNumber <= attackPool.size()) {
                        playerCharacter.setAttackSlot(attackPool.get(
                                attackPool.size() - (choiceNumber)), y + 1);
                    } else {
                        outputMessage("You do not have that slot");
                        y--;
                        choiceNumber = -1;
                    }
                }
            }
        }
    }
}
