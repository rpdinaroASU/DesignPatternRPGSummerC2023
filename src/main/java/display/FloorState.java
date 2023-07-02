package display;

import characters.Enemy;
import characters.Player;

import javax.swing.JOptionPane;
import java.util.ArrayList;

/**
 * This state generates and hosts list of floor enemies
 * @author Ryan Dinaro
 * @version 7/1/2023
 */
public class FloorState extends UIStates{
    private final Enemy[] enemies;
    private static final int MAX_ENEMIES = 5;

    /**
     * Initiates the floor state of the FSM.
     * Generates an array of enemies, displays them and
     * solicits user to fight one.
     * Moves to Victory state, a new Battle state, or a new floor
     * @param playerCharacter the player character
     */
    public FloorState(Player playerCharacter) {
        int enemyCount = getRandomInt(MAX_ENEMIES-2) + 2;
        enemies = new Enemy[enemyCount];
        for(int x = 0; x < enemyCount; x++) {
            enemies[x] = new Enemy(playerCharacter.getCharacterLevel(),
                    playerCharacter.getPlayerDifficulty(),
                    playerCharacter.getGoldBonus());
        }
        chooseEnemy(playerCharacter);
        if(playerCharacter.getGold()>GOLD_GOAL) {
            new VictoryState(playerCharacter);
        } else {
            new FloorState(playerCharacter);
        }
    }

    /**
     * Solicits users to select which alive enemy they want.
     */
    private void chooseEnemy(Player playerCharacter) {
        int choiceNumber = 0;
        ArrayList<Enemy> enemyList = new ArrayList<Enemy>();
        for (Enemy enemy : enemies) {
            if (enemy.getHealthPoints() > 0) {
                enemyList.add(enemy);
            }
        }
        if(enemyList.size()==0) {
            new FloorState(playerCharacter);
        } else {
            Enemy[] enemyArr = enemyList.toArray(new Enemy[0]);
            String message = "Which do you want to face first?\n";
            message += listEnemy(playerCharacter);

            Enemy input = (Enemy) JOptionPane.showInputDialog(null, message,
                    "Choose Class", JOptionPane.QUESTION_MESSAGE,
                    null, enemyArr, enemyArr[0]);

            new PlayerBattleState(playerCharacter, input);
            chooseEnemy(playerCharacter);
        }
    }

    /**
     * Lists all enemies that are alive on the floor.
     */
    public String listEnemy(Player playerCharacter) {
        String message = "Player Stats: \n";
        message += getPlayerInfo(playerCharacter);
        message += "\nGold: " + (int) playerCharacter.getGold();
        for (int x = 0; x < enemies.length; x++) {
            if(enemies[x].getHealthPoints()>0) {
                message += enemies[x].getEnemyName()
                        + "\nHealth: " + (int) enemies[x].getHealthPoints()
                        + " / " + (int) enemies[x].getHealthCap() + "\n";
            }
        }
        return message;
    }
}
