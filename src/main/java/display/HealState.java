package display;
import characters.Player;

import javax.swing.JOptionPane;

/**
 * Heals the user for a price proportional to level.
 * @author Ryan Dinaro
 * @version 7/1/2023
 */
public class HealState extends UIStates{
    /**
     * Solicits the user for healing services.
     * Moves back to Floor State after.
     * @param playerCharacter the player character
     */
    public HealState(Player playerCharacter) {
        final int costMultiplier = 225;
        int costOfHeal = playerCharacter.getCharacterLevel() * costMultiplier;
        String message = getPlayerInfo(playerCharacter)
                + "\nWould you like to heal for "
                + costOfHeal + " gold? \nYou have " + playerCharacter.getGold()
                + " gold.";

        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(null,
                message, "Heal", dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            if(playerCharacter.getGold()<costOfHeal) {
                outputMessage("You don't have enough gold");
            } else if(playerCharacter.getGold()>costOfHeal) {
                playerCharacter.removeGold(costOfHeal);
                playerCharacter.heal();
                outputMessage("You pour the gold into your trusty"
                        + " Gold-To-Health-O'Matic "
                        + "\nThat has no canonical reason"
                        + " to exist.");
            }
        }
    }
}

