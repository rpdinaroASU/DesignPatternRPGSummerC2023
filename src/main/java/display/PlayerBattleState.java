package display;

import characters.Attack;
import characters.Enemy;
import characters.Player;


import static display.UI.displayPlayerInfo;

public class PlayerBattleState extends BattleState implements DisplayState{
    private final Player playerCharacter;
    private final Enemy enemyCharacter;

    public PlayerBattleState(Player player, Enemy enemy) {
        super(player, enemy);
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
        System.out.println("===========================================================");
        displayEnemyInfo();
        System.out.println("===========================================================");
        getPlayerMove();
    }

    private void getPlayerMove() {
        for(int x = 0; x < playerCharacter.getMoveCount(); x++) {
            if(playerCharacter.getAttackSlots(x) !=null) {
                String message = "Attack: (" + (x+1) + ") "
                        + playerCharacter.getAttackSlots(x).getAttackName() + "\t";
                System.out.print(message);
            }
        }
        System.out.println();
        int choiceNumber = -1;
        while(choiceNumber<0 || choiceNumber>playerCharacter.getMoveCount()) {
            String input = scan.nextLine();
            try {
                choiceNumber = Integer.parseInt(input);
                choiceNumber--;
            } catch (Exception e) {
                System.out.println("Enter a valid input.");
                choiceNumber = -1;
                continue;
            }
            if((choiceNumber>=0 && choiceNumber<=playerCharacter.getMoveCount()-1)
                    && playerCharacter.getAttackSlots(choiceNumber)
                    != null) {
                doPlayerAttack(choiceNumber);
                if(enemyCharacter.getHealthPoints()!=0) {
                    doPlayerAttack(choiceNumber);
                    choiceNumber = -1;
                }
            } else {
                System.out.println("You do not have an attack in that slot");
                choiceNumber = -1;
            }
        }
    }
    private void doPlayerAttack(int slotNumber) {
        Attack playerAttack = playerCharacter.getAttackSlots(slotNumber);
        playerCharacter.reduceMana(playerAttack.getManaCost());
        playerCharacter.reduceStamina(playerAttack.getStaminaCost());
        doDamage(enemyCharacter, playerAttack);

        if(enemyCharacter.getHealthPoints()!=0) {
            System.out.println("===========================================================");
            displayPlayerInfo(playerCharacter);
            System.out.println("===========================================================");
            displayEnemyInfo();
            new EnemyBattleState(playerCharacter, enemyCharacter);
        } else {
            defeatedEnemy();
        }
    }

    private void defeatedEnemy() {
        System.out.println("You have defeated the " + enemyCharacter.getEnemyName() + ".");
        double goldRewarded = playerCharacter.getGoldBonus()*enemyCharacter.getGoldGiven();
        playerCharacter.addGold(goldRewarded);
        System.out.println("You have been awarded " + goldRewarded + " gold.");
        double experienceRewarded = enemyCharacter.getExperienceGiven();
        playerCharacter.addExperience(experienceRewarded);
        System.out.println("You have been awarded " + experienceRewarded + " experience.");
        System.out.println(playerCharacter.getExperienceCap() - playerCharacter.getExperiencePoints()
                + " experience needed to improve\n\n");
    }
    private void displayEnemyInfo() {
        String message = "Enemy: " + enemyCharacter.getEnemyName()
                + "\nHealth: " + (int) enemyCharacter.getHealthPoints()
                + " / " + (int) enemyCharacter.getHealthCap();
        System.out.println(message);
    }
}
