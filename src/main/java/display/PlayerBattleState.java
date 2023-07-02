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

    /**
     * This object will initiate a players turn
     * @param player the player
     * @param enemy the single enemy to fight
     */
    public PlayerBattleState(Player player, Enemy enemy) {
        displayCombatHeader(player,enemy);
        listAttacks(player);
        getPlayerMove(player,enemy);
    }

    /**
     * Displays the header of combat
     */
    private void displayCombatHeader(Player playerCharacter, Enemy enemyCharacter) {
        System.out.println("Player info: ");
        displayPlayerInfo(playerCharacter);
        System.out.println("===========================================================");
        displayEnemyInfo(enemyCharacter);
        System.out.println("===========================================================\n");
    }

    /**
     * This method will list all attacks a player can use in combat.
     */
    private void listAttacks(Player playerCharacter) {
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
    private void getPlayerMove(Player playerCharacter, Enemy enemyCharacter) {
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
                doPlayerAttack(choiceNumber,playerCharacter,enemyCharacter);
                if(enemyCharacter.getHealthPoints()!=0) {
                    doPlayerAttack(choiceNumber,playerCharacter,enemyCharacter);
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
    private void doPlayerAttack(int slotNumber, Player playerCharacter,
                                Enemy enemyCharacter) {
        Attack playerAttack = playerCharacter.getAttackSlots(slotNumber);
        playerCharacter.reduceMana(playerAttack.getManaCost());
        playerCharacter.reduceStamina(playerAttack.getStaminaCost());
        doDamage(enemyCharacter, playerAttack);

        if(enemyCharacter.getHealthPoints()!=0) {
            displayCombatHeader(playerCharacter, enemyCharacter);
            new EnemyBattleState(playerCharacter, enemyCharacter);
        } else {
            defeatedEnemy(playerCharacter,enemyCharacter);
        }
    }

    /**
     * The informative  exit message for the FSM to go back to the
     * levelUp state or the Heal State.
     */
    private void defeatedEnemy(Player playerCharacter, Enemy enemyCharacter) {
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
    private void displayEnemyInfo(Enemy enemyCharacter) {
        String message = "Enemy: " + enemyCharacter.getEnemyName()
                + "\nHealth: " + (int) enemyCharacter.getHealthPoints()
                + " / " + (int) enemyCharacter.getHealthCap();
        System.out.println(message);
    }
}
