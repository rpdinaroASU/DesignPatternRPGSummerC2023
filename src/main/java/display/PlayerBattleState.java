package display;

import characters.Attack;
import characters.Enemy;
import characters.Player;

/**
 * @author Ryan Dinaro
 * @version 7/1/23
 * This state represents a players turn while in a battle
 */
public class PlayerBattleState extends BattleState{
    private final Player playerCharacter;
    private final Enemy enemyCharacter;

    /**
     * This object will initiate a players turn
     * @param player the player
     * @param enemy the single enemy to fight
     */
    public PlayerBattleState(Player player, Enemy enemy) {
        playerCharacter = player;
        enemyCharacter = enemy;
        displayCombatHeader();
        listAttacks();
        getPlayerMove();
    }

    /**
     * Displays the header of combat
     */
    private void displayCombatHeader() {
        System.out.println("Player info: ");
        displayPlayerInfo(playerCharacter);
        System.out.println("===========================================================");
        displayEnemyInfo();
        System.out.println("===========================================================\n");
    }

    /**
     * This method will list all attacks a player can use in combat.
     */
    private void listAttacks() {
        for(int x = 0; x < playerCharacter.getMoveCount(); x++) {
            if(playerCharacter.getAttackSlots(x) !=null) {
                String message = "Attack: (" + (x+1) + ") "
                        + playerCharacter.getAttackSlots(x).getAttackName() + "\t\n";
                System.out.print(message);
            }
        }
    }

    /**
     * Solicits the players desired move.
     * Parses only results that remain in bounds of FSM
     */
    private void getPlayerMove() {
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

    /**
     * Attacks the enemy with the attack slot number specified
     * @param slotNumber the attack slot number
     */
    private void doPlayerAttack(int slotNumber) {
        Attack playerAttack = playerCharacter.getAttackSlots(slotNumber);
        playerCharacter.reduceMana(playerAttack.getManaCost());
        playerCharacter.reduceStamina(playerAttack.getStaminaCost());
        doDamage(enemyCharacter, playerAttack);

        if(enemyCharacter.getHealthPoints()!=0) {
            displayCombatHeader();
            new EnemyBattleState(playerCharacter, enemyCharacter);
        } else {
            defeatedEnemy();
        }
    }

    /**
     * The informative  exit message for the FSM to go back to the
     * levelUp state or the Heal State.
     */
    private void defeatedEnemy() {
        System.out.println("You have defeated the " + enemyCharacter.getEnemyName() + ".");
        double goldRewarded = playerCharacter.getGoldBonus()*enemyCharacter.getGoldGiven();
        playerCharacter.addGold(goldRewarded);
        System.out.println("You have been awarded " + goldRewarded + " gold.");
        double experienceRewarded = enemyCharacter.getExperienceGiven();
        playerCharacter.addExperience(experienceRewarded);
        System.out.println("You have been awarded " + experienceRewarded + " experience.");
        System.out.println((int) (playerCharacter.getExperienceCap() - playerCharacter.getExperiencePoints())
                + " experience needed to improve\n\n");
        if(playerCharacter.levelUp()) {
            new LevelUpState(playerCharacter);
        } else {
            new HealState(playerCharacter);
        }
    }

    /**
     * Display the information for enemies in combat.
     */
    private void displayEnemyInfo() {
        String message = "Enemy: " + enemyCharacter.getEnemyName()
                + "\nHealth: " + (int) enemyCharacter.getHealthPoints()
                + " / " + (int) enemyCharacter.getHealthCap();
        System.out.println(message);
    }
}
