package display;

import characters.Attack;
import characters.Player;

import javax.swing.JOptionPane;
import java.util.Collections;
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
        outputMessage("You leveled up");
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
            //minimize the number of attack placements
            int maxSlot = (attackPool.size()
                    < playerCharacter.getAttackSlotCount())
                    ? attackPool.size() : playerCharacter.getAttackSlotCount();

            Collections.reverse(attackPool); //Put strongest on top
            //solicits a move for each attack slot
            for (int y = 0; y < maxSlot; y++) {
                String message = "";
                message += ("Select an attack for slot #" + (y + 1) +"\n")
                    +getAttackPoolList(attackPool);
                Attack input = null;
                while (input==null) {
                    getAttackPoolList(attackPool);
                    Attack[] attackArr = attackPool.toArray(new Attack[0]);
                    input = (Attack) JOptionPane.showInputDialog(
                            null, message, "Choose Attack",
                            JOptionPane.QUESTION_MESSAGE, null,
                            attackArr, attackArr[0]);
                }
                attackPool.remove(input);
            }
        }
    }

    /**
     * Lists all attacks and stats behind the attacks
     * @return all attacks and stats
     */
    private String getAttackPoolList(List<Attack> attackPool) {
        //This lists all attacks and stats behind the attacks
        String message = "";
        for (int x = attackPool.size() - 1; x >= 0; x--) {
            message += "\n" +attackPool.get(x).getAttackName() + " | \t"
                    + " Physical Attack: "
                    + (int) attackPool.get(x).getMaxAttack() + "\t"
                    + " Magical Attack: "
                    + (int) attackPool.get(x).getMaxMagicDamage() + "\t"
                    + " Stamina Cost: "
                    + (int) attackPool.get(x).getStaminaCost() + "\t"
                    + " Mana Cost: "
                    + (int) attackPool.get(x).getManaCost() + "\n";
        }
        return message;
    }
}
