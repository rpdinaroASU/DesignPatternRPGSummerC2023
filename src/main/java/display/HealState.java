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
        int costOfHeal = playerCharacter.getCharacterLevel() * 5;
        String message = "Would you like to heal for "
                + costOfHeal + " gold? \nYou have " + playerCharacter.getGold()
                + " gold.";
        System.out.println(message);
        String response = "";
        while(!response.equalsIgnoreCase("yes")
                && !response.equalsIgnoreCase("no")) {
            response = scan.nextLine();
            if(!response.equalsIgnoreCase("yes")
                    && !response.equalsIgnoreCase("no")) {
                System.out.println("Sorry didn't catch that");
            } else if(response.equalsIgnoreCase("yes")
                    && playerCharacter.getGold()<costOfHeal) {
                System.out.println("You don't have enough gold");
            } else if(response.equalsIgnoreCase("yes")
                    && playerCharacter.getGold()>costOfHeal) {
                playerCharacter.removeGold(costOfHeal);
                playerCharacter.heal();
                System.out.println("You pour the gold into your trusty"
                        + " Gold-To-Health-O'Matic \nThat has no canonical reason"
                        + " to exist.");
            }
        }
    }
}

