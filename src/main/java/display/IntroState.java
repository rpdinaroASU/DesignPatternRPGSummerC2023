package display;

import characters.Player;
import characters.PlayerClasses;

import javax.swing.JSlider;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

/**
 * This is the initial state of the FSM
 * @author Ryan Dinaro
 * @version 7/1/2023
 */
public class IntroState extends UIStates{
    private static String name;
    private Player player;

    /**
     * Starting state of FSM
     */
    public IntroState() {
        super();
        displayScene();
    }

    /**
     * Displays the intro scene. Moves to Floor State after execution
     */
    public void displayScene() {
        String message = "You've heard tales of the dungeon \n"
                + "at the center of town since you were a child. \n"
                + "Legends of warriors searching for a bottom,\n"
                + "always to return with untold riches and relics; \n"
                + "few have returned none have ever found the bottom. \n";
        outputMessage(message);
        message = "You awaken the morning expecting all to be well.\n"
                + "You find a knife stuck to the door with a \n"
                + "a note attached.\n";
        outputMessage(message);
        message =  "\"We have your mother.\nPay 1,000,000 coins\n"
                + "or say goodbye\"\n";
        outputMessage(message);
        message = "Out of desperation your gaze turns to the dungeon. \n"
                + "There is no depth to your desperation.\n"
                + "There is no question.\nYou will save your mom.\n";
        outputMessage(message);
        message = "A guard stands before you at the \n"
                + "entrance to the dungeon.\n"
                + "\"Halt who approaches this cursed place.\"\n";
        outputMessage(message);
        getWarriorsName();
        message = "\"" + name + " ehh. what business do \n"
                +  "you have in this place?\" \n";
        outputMessage(message);
        message =  "You explain the predicament you found yourself in.\n"
                + "As you speak the shadows longed and the look on \n"
                + "their face steadily deepens. \n";
        outputMessage(message);
        message = "\"" + name + " as it stands, you have no hope.\n"
                + "The people who come through this dungeon do not return. \n"
                + "But your situation concerns me deeply.\"\n";
        outputMessage(message);
        message = "\"I will let you in on a secret though.\n"
                + "The ones who come back commit themselves to one \n"
                + "discipline.\"\n";
        outputMessage(message);
        message = "\"The discipline of warriors.\"";
        outputMessage(message);
        message = "The way of the warrior - strong in health and stamina\n"
                + "The way of the thief - rich and healthy\n"
                + "The way of the witch - magical and intelligent\n"
                + "The way of the battlemage - well rounded\n"
                + "The way of the Paladin - "
                + "a righteous balance between health, "
                + "magic, and stamina with a touch of divine favor.\n"
                + "The way of the Ranger - physically robust and a master of "
                + "survival with a knack for treasure hunting.\n"
                + "The way of the Necromancer - a beacon of dark magic with an"
                + "uncanny sense for gold and treasures.\n"
                + "The way of the Monk - a perfect blend of physical might, "
                + "inner magic, and a mindful presence.";
        getWarriorsClass(message);
        player.setPlayerName(name);
        message = "\"Ah, the way of the "
                + player.getPlayerClass().name() + ".\n"
                + "\nYou might have a chance. \"\n";
        outputMessage(message);
        message = "\"Enter now, lest your heart fails you.\" They said\n"
                + "They opened the door and gave you a final look \n"
                + "as you close the door on them.\n";
        outputMessage(message);
        message = "You are now in the dungeons.\n";
        getDifficultyLevel();
        outputMessage(message);
        new FloorState(player);
    }

    /**
     * Solicits the name of the player
     */
    private void getWarriorsName() {
        name = inputScan("What is the name of your fighter");
    }

    /**
     * Solicits and parses the difficulty level.
     * On a scale from 1 to 10
     */
    private void getDifficultyLevel() {
        String message = "How difficult do you want this journey?\n"
                + "(1-10) 1 is a hard journey, 10 is impossible";
        final int lowerBoundsDifficulty = 1;
        final int upperBoundsDifficulty = 10;
        int difficulty = 0;

        JSlider slider = new JSlider(JSlider.HORIZONTAL, lowerBoundsDifficulty,
                upperBoundsDifficulty, lowerBoundsDifficulty);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("How difficult do you want this journey? "
                + "\n(1-10) 1 is a hard journey, 10 is impossible"));
        panel.add(slider);

        JOptionPane optionPane = new JOptionPane(panel,
                JOptionPane.QUESTION_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = optionPane.createDialog("Choose Difficulty");
        dialog.setVisible(true);

        if (optionPane.getValue() != null
                && (int) optionPane.getValue() == JOptionPane.OK_OPTION) {
            difficulty = slider.getValue();
        } else {
            Runtime.getRuntime().exit(0);
        }

        outputMessage("So it is decided, " + difficulty + " difficulty");
        double scaledDifficulty = 1
                + (((double) difficulty)/upperBoundsDifficulty);
        player.setDifficulty(scaledDifficulty);
        player.setStatCaps(player.getPlayerClass().getHealthBonus(),
                player.getPlayerClass().getManaBonus(),
                player.getPlayerClass().getStaminaBonus());
    }

    /**
     * Lists and solicits the player classes
     */
    private void getWarriorsClass(String message) {
        PlayerClasses[] choices = PlayerClasses.values();

        PlayerClasses playerClass = (PlayerClasses)
                JOptionPane.showInputDialog(null, message,
                "Choose Class", JOptionPane.QUESTION_MESSAGE,
                        null, choices, choices[0]);

        player = new Player(playerClass);
    }

}
