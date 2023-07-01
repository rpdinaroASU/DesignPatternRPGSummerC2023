package display;

import characters.Enemy;
import characters.Player;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static display.UI.displayPlayerInfo;

public class BattleState implements DisplayState{
    private final Player playerCharacter;
    private final Enemy enemyCharacter;
    private int moveCount;
    private final Scanner scan;
    public BattleState(Player player, Enemy enemy) {
        this.scan = new Scanner(System.in, StandardCharsets.UTF_8);
        playerCharacter = player;
        enemyCharacter = enemy;
        displayScene();
    }

    @Override
    public void displayScene() {
        displayCombatInfo();
    }
    private void displayCombatInfo() {
        System.out.println("Player info: ");
        displayPlayerInfo(playerCharacter);
        String message;
        moveCount = 0;
        for(int x = 0; x < playerCharacter.getAttackSlotCount(); x++) {
            if(playerCharacter.getAttackSlots(x) !=null) {
                message = "Attack: (" + x + ") "
                        + playerCharacter.getAttackSlots(x).getAttackName();
                System.out.println(message);
                moveCount++;
            }
        }
        displayEnemyInfo();
        getPlayerMove();
    }

    private void getPlayerMove() {
        int choiceNumber = 0;
        while(choiceNumber<=0 || choiceNumber>moveCount) {
            String input = scan.nextLine();
            try {
                choiceNumber = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Choose your attack ");
                choiceNumber = 0;
            }
        }


    }

    private void displayEnemyInfo() {
        String message = "Enemy: " + enemyCharacter.getEnemyName()
                + "\nHealth: " + (int) enemyCharacter.getHealthPoints()
                + " / " + (int) enemyCharacter.getHealthCap();
        System.out.println(message);
    }
}
