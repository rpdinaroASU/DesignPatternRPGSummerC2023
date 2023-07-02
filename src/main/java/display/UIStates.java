package display;

import characters.CharacterBase;


import javax.swing.JOptionPane;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

/**
 * @author Ryan Dinaro
 * @version 7/1/23
 */
public class UIStates {
    private static final int SLEEP_TIME_TEMP = 10;
    private final Random rand;
    private final Scanner scan;
    protected static final int GOLD_GOAL = 1000000;

    /**
     * Initiates the FSM for display control
     */
    public UIStates(){
        rand = new Random();
        scan = new Scanner(System.in, StandardCharsets.UTF_8);
    }

    /**
     * Calls for an input from the user
     * @return the input received
     */
    public String inputScan(String message) {
        String input = JOptionPane.showInputDialog(null, message);
        return input;
    }

    /**
     * Returns a random int bounded by param
     * @param bound the upper bound of random
     * @return random int bounded by param
     */
    public int getRandomInt(int bound) {
        return rand.nextInt(bound);
    }

    /**
     * Outputs a message and pauses for time to read
     * @param message the message to be outputted
     */
    public static void outputMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    /**
     * Displays player stats
     * @param character the Character
     */
    public static String getPlayerInfo(CharacterBase character) {
        String message = "Health: " + (int) character.getHealthPoints()
                + " / " + (int) character.getHealthCap() + "\t\t"
                + " Mana: " + (int) character.getManaPoints()
                + " / " + (int) character.getManaCap() + "\t\t"
                + " \nStamina: " + (int) character.getStaminaPoints()
                + " / " + (int) character.getStaminaCap();
        return message;
    }

}
