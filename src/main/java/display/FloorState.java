package display;

import characters.Enemy;
import characters.Player;

/**
 * This state generates and hosts list of floor enemies
 * @author Ryan Dinaro
 * @version 7/1/2023
 */
public class FloorState extends UIStates{
    private final Player playerCharacter;
    private final Enemy[] enemies;
    private static final int MAX_ENEMIES = 5;

    /**
     * Initiates the floor state of the FSM.
     * Generates an array of enemies, displays them and
     * solicits user to fight one.
     * Moves to Victory state, a new Battle state, or a new floor
     * @param player the player character
     */
    public FloorState(Player player) {
        playerCharacter = player;
        int enemyCount = rand.nextInt(maxEnemies-2) + 2 ;
        enemies = new Enemy[enemyCount];
        for(int x = 0; x < enemyCount; x++) {
            enemies[x] = new Enemy(playerCharacter.getCharacterLevel(),
                    playerCharacter.getPlayerDifficulty(),
                    playerCharacter.getGoldBonus());
        }
        chooseEnemy();
        if(playerCharacter.getGold()>1000000) {
            new VictoryState(playerCharacter);
        } else {
            new FloorState(playerCharacter);
        }
    }

    /**
     * Solicits users to select which alive enemy they want.
     */
    private void chooseEnemy() {
        int choiceNumber = 0;
        System.out.println("Which do you want to face first?");
        listEnemy();
        while(choiceNumber<=0 || choiceNumber>enemies.length) {
            String input = scan.nextLine();
            try {
                choiceNumber = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Choose your enemy ");
                choiceNumber = 0;
                continue;
            }
            boolean worked = false;
            for (int x = 0; x < enemies.length; x++) {
                if (enemies[x].getHealthPoints() > 0
                    && choiceNumber-1 == x) {
                    worked = true;
                    new PlayerBattleState(playerCharacter, enemies[choiceNumber-1]);
                }
            }
            if(!worked) {
                System.out.println("That is an invalid number");
            }
        }
        for (Enemy enemy : enemies) {
            if (enemy.getHealthPoints() > 0) {
                chooseEnemy();
                break;
            }
        }


    }

    /**
     * Lists all enemies that are alive on the floor.
     */
    public void listEnemy() {
        System.out.println("Player Stats: ");
        displayPlayerInfo(playerCharacter);
        for (int x = 0; x < enemies.length; x++) {
            if(enemies[x].getHealthPoints()>0) {
                String message = "( " + (x+1) + " ) " + enemies[x].getEnemyName()
                        + "\nHealth: " + (int) enemies[x].getHealthPoints()
                        + " / " + (int) enemies[x].getHealthCap();
                System.out.println(message);
            }
        }
    }
}
