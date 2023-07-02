package display;

import characters.Player;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Ryan Dinaro
 * @version 7/1/23
 */
public class UIStates {
    private static final int sleepTimeTemp = 0;
    protected final Random rand;
    protected final Scanner scan;

    /**
     * Initiates the FSM for display control
     */
    public UIStates(){
        rand = new Random();
        scan = new Scanner(System.in, StandardCharsets.UTF_8);
        new IntroState();
    }

    /**
     * Outputs a message and pauses for time to read
     * @param message the message to be outputted
     */
    public static void outputMessage(String message) {
        System.out.print(message);
        try {
            Thread.sleep(sleepTimeTemp);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Displays player stats
     * @param playerCharacter the player Character
     */
    public static void displayPlayerInfo(Player playerCharacter) {
        String message = "Health: " + (int) playerCharacter.getHealthPoints()
                + " / " + (int) playerCharacter.getHealthCap() + "\t\t"
                + "Mana: " + (int) playerCharacter.getManaPoints()
                + " / " + (int) playerCharacter.getManaCap() + "\t\t"
                + "Stamina: " + (int) playerCharacter.getStaminaPoints()
                + " / " + (int) playerCharacter.getStaminaCap() + "\t\t"
                + "Gold: " + (int) playerCharacter.getGold();

        System.out.println(message);
    }

}
