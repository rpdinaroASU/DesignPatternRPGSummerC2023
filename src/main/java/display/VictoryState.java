package display;

import characters.Player;

/**
 * @author Ryan Dinaro
 * @version 7/1/23
 * This state represents a victory
 */
public class VictoryState {
    /**
     * The state that comes from reaching 1,000,000 gold
     * @param playerCharacter the player character
     */
    public VictoryState(Player playerCharacter) {
        String message = "You step out of the dungeon and "
                + "make eye contact with the guard. \nThey "
                + "are clearly surprised but say nothing in "
                + "the face of your determination.\n";
        UIStates.outputMessage(message);
        message = "You make your way back to the agreed drop point"
                + "and see your mother there with the kidnappers.\n"
                + "You toss the gold at their feet without saying a word.\n";
        UIStates.outputMessage(message);
        message = "They count the gold as they release your mom.\n"
                + "As you get your mom to safety, they stop you.\n"
                + "Where do you think your going? You are still short"
                + "another million.\n";
        UIStates.outputMessage(message);
        message = "You tell your mother to run and you turn to face them.\n"
                + "Before you lie three villains weaker than anything you saw\n"
                + "in the dungeon\nYou smile to yourself as they approach\n";
        UIStates.outputMessage(message);
        message = "This will feel good\n\n";
        UIStates.outputMessage(message);
        message = "You spend your time tending the garden and helping your mother\n"
                + "around the house. \n Often times the children stop by to hear"
                + " the story of " + playerCharacter.getPlayerName() + " the "
                + "warrior.";
        UIStates.outputMessage(message);
    }
}
