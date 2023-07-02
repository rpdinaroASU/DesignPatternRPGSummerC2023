package display;
import characters.Player;

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
        UIStates.displayPlayerInfo(playerCharacter);
        final int costMultiplier = 5;
        int costOfHeal = playerCharacter.getCharacterLevel() * costMultiplier;
        String message = "Would you like to heal for "
                + costOfHeal + " gold? \nYou have " + playerCharacter.getGold()
                + " gold.";
        String response = "";
        while(!response.equalsIgnoreCase("yes")
                && !response.equalsIgnoreCase("no")) {
            response = inputScan(message);
            if(!response.equalsIgnoreCase("yes")
                    && !response.equalsIgnoreCase("no")) {
                outputMessage("Sorry didn't catch that");
            } else if(response.equalsIgnoreCase("yes")
                    && playerCharacter.getGold()<costOfHeal) {
                outputMessage("You don't have enough gold");
            } else if(response.equalsIgnoreCase("yes")
                    && playerCharacter.getGold()>costOfHeal) {
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

