package display;

import characters.Player;
import characters.PlayerClasses;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class IntroState implements DisplayState {
    private final Scanner scan;
    private static String name;
    private static PlayerClasses playerClass;
    private Player player;

    /**
     * Starting state of display
     */
    public IntroState() {
        this.scan = new Scanner(System.in, StandardCharsets.UTF_8);
        this.player = player;
        displayScene();
    }

    /**
     * Displays the intro scene
     */
    @Override
    public void displayScene() {
        String message = "You've heard tales of the dungeon "
                + "at the center of town since you were a child. \n"
                + "Legends of warriors searching for a bottom, "
                + "always to return with untold riches and relics; \n"
                + "few have returned none have ever found the bottom. \n";
        outputMessage(message);
        message = "You awaken the morning expecting all to be well.\n"
                + "You find a knife stuck to the door with a "
                + "a note attached.\n\n"
                + "\"We have your mother.\nPay 1,000,000 coins\n"
                + "or say goodbye\"\n\n";
        outputMessage(message);
        message = "Out of desperation your gaze turns to the dungeon. \n"
                + "There is no depth to your desperation.\n"
                + "There is no question.\nYou will save your mom.\n\n";
        outputMessage(message);
        message = "A guard stands before you at the "
                + "entrance to the dungeon.\n"
                + "\"Halt who approaches this cursed place.\"\n\n";
        outputMessage(message);
        getWarriorsName();
        message = "\n\"" + name + " ehh. what business do "
                +  "you have in this place?\" \n\n"
                + "You explain the predicament you found yourself in.\n"
                + "As you speak the shadows longed and the look on "
                + "their face steadily deepens. \n\n";
        outputMessage(message);
        message = "\"" + name + " as it stands, you have no hope.\n"
                + "The people who come through this dungeon do not return. \n"
                + "But your situation concerns me deeply.\"\n\n";
        outputMessage(message);
        message = "\"I will let you in on a secret though.\n"
                + "The ones who come back commit themselves to one "
                + "discipline.\"";
        outputMessage(message);
        message = "\n\n\"The disciplines of warriors.\"\n\n";
        outputMessage(message);
        message = "\"The way of the warrior - strong in health and stamina\n"
                + "The way of the thief - rich and healthy\n"
                + "The way of the witch - magical and intelligent\n"
                + "The way of the battlemage - well rounded\n"
                + "The way of the Paladin - "
                + "a righteous balance between health, "
                + "magic, and stamina with a touch of divine favor.\n"
                + "The way of the Ranger - physically robust and a master of "
                + "survival with a knack for treasure hunting.\n"
                + "The way of the Necromancer - a beacon of dark magic with an"
                + " uncanny sense for gold and treasures.\n"
                + "The way of the Monk - a perfect blend of physical might, "
                + "inner magic, and a mindful presence.\"\n\n";
        outputMessage(message);
        getWarriorsClass();
        message = "\"Ah, the way of the " + playerClass.name()
                + "You might have a chance. \"\n\n";
        outputMessage(message);
        message = "\"Enter now, lest your heart fails you.\" They said\n"
                + "They opened the door and gave you a final look \n"
                + "as you close the door on them.\n\n";
        outputMessage(message);
        message = "You are now in the dungeons.\n\n\n";
        outputMessage(message);

        new BattleState();
    }
    private void outputMessage(String message) {
        System.out.print(message);
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void getWarriorsName() {
        System.out.println("What is the name of your fighter: ");
        name = scan.nextLine();
    }
    private void getWarriorsClass() {
        String message = "What manner of fighter are you";
        int x = 1;
        PlayerClasses[] pClasses = PlayerClasses.values();
        for(PlayerClasses classes : pClasses) {
            System.out.println("(" + x +") " + classes.name());
            x++;
        }
        System.out.println(message);
        int classChoice = 0;
        while(classChoice<=0 || classChoice>pClasses.length) {
            String input = scan.nextLine();
            try {
                classChoice = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("\"I didn't catch that\"");
                classChoice = 0;
                continue;
            }
            if(classChoice<=0 ||classChoice>pClasses.length) {
                System.out.println("\"I'm sorry, what type was that?\" "
                        + "They said");
            }
        }
        playerClass = pClasses[classChoice-1];
        player = new Player(playerClass);
    }

}