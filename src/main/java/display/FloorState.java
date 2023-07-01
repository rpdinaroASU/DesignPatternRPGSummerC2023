package display;

import characters.Enemy;
import characters.Player;

import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

import static display.UI.displayPlayerInfo;
import static display.UI.outputMessage;

public class FloorState implements DisplayState{
    private final Player playerCharacter;
    private final Scanner scan;
    private final Random rand;
    private Enemy[] enemies;
    private static final int maxEnemies = 5;
    public FloorState(Player player) {
        this.scan = new Scanner(System.in, StandardCharsets.UTF_8);
        playerCharacter = player;
        rand = new Random();
        int enemyCount = rand.nextInt(maxEnemies-2) + 2 ;
        enemies = new Enemy[enemyCount];
        for(int x = 0; x < enemyCount; x++) {
            enemies[x] = new Enemy(playerCharacter.getCharacterLevel(),
                    playerCharacter.getPlayerDifficulty(),
                    playerCharacter.getGoldBonus());
        }
        displayScene();
    }

    @Override
    public void displayScene() {
        chooseEnemy();
    }

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
                    new BattleState(playerCharacter, enemies[choiceNumber-1]);
                }
            }
            if(!worked) {
                System.out.println("That is an invalid number");
            }
        }
        for (Enemy enemy : enemies) {
            if (enemy.getHealthPoints() > 0) {
                listEnemy();
                chooseEnemy();
            }
        }


    }

    public void listEnemy() {
        System.out.println("Player Stats: ");
        displayPlayerInfo(playerCharacter);
        int count = 0;
        for (int x = 0; x < enemies.length; x++) {
            if(enemies[x].getHealthPoints()>0) {
                String message = "( " + (x+1) + " ) " + enemies[x].getEnemyName()
                        + "\nHealth: " + (int) enemies[x].getHealthPoints()
                        + " / " + (int) enemies[x].getHealthCap();
                System.out.println(message);
                count++;
            }
        }
        if(count==0) {
            new NewFloorState();
        }
    }
}
