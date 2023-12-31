package display;

/**
 * The state of death. Game ends on this, or victory state
 * @author Ryan Dinaro
 * @version 7/1/2023
 */
public class DeathState extends UIStates {
    /**
     * The end State where you lose
     */
    public DeathState() {
        outputMessage("You lie there, the world fading out.\n"
        + "What will happen to my mom? you wonder\n");
        Runtime.getRuntime().exit(0);
    }
}
