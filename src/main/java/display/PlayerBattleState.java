package display;

import characters.Attack;
import characters.Enemy;
import characters.Player;

import javax.swing.JOptionPane;

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
        getPlayerMove(player,enemy);
    }

    /**
     * Displays the header of combat
     */
    private String combatHeader(Player playerCharacter,
                              Enemy enemyCharacter) {
        String message = (playerCharacter.getPlayerName() +" info: \n");
        message += getPlayerInfo(playerCharacter);
        message += "\n" + enemyCharacter.getEnemyName() + " info: \n";
        message += getPlayerInfo(enemyCharacter);
        return message;
    }

    /**
     * This method will list all attacks a player can use in combat.
     */
    private String listAttacks(Player playerCharacter) {
        String message = "";
        for(int x = 0; x < playerCharacter.getMoveCount(); x++) {
            if(playerCharacter.getAttackSlots(x) !=null) {
               message = "Attack: (" + (x+1) + ") "
                        + playerCharacter.getAttackSlots(x).getAttackName()
                        + "\t\n";
            }
        }
        return message;
    }

    /**
     * Solicits the players desired move.
     * Parses only results that remain in bounds of FSM
     */
    private void getPlayerMove(Player playerCharacter, Enemy enemyCharacter) {
        String message = combatHeader(playerCharacter,enemyCharacter);
        message += listAttacks(playerCharacter);

        Attack input = null;
        while(input==null) {
            Attack[] arr = getCharacterAttacks(playerCharacter);
            input = (Attack) JOptionPane.showInputDialog(null, message,
                    "Choose Attack", JOptionPane.QUESTION_MESSAGE, null, arr, arr[0]);
        }
        doPlayerAttack(input,playerCharacter,enemyCharacter);

    }

    /**
     * Attacks the enemy with the attack slot number specified
     * @param playerAttack the attack
     */
    private void doPlayerAttack(Attack playerAttack, Player playerCharacter,
                                Enemy enemyCharacter) {
        playerCharacter.reduceMana(playerAttack.getManaCost());
        playerCharacter.reduceStamina(playerAttack.getStaminaCost());
        doDamage(enemyCharacter, playerAttack);

        if(enemyCharacter.getHealthPoints()!=0) {
            combatHeader(playerCharacter, enemyCharacter);
            new EnemyBattleState(playerCharacter, enemyCharacter);
        } else {
            defeatedEnemy(playerCharacter,enemyCharacter);
        }
    }

    /**
     * The informative  exit message for the FSM to go back to the
     * levelUp state or the Heal State.
     */
    private void defeatedEnemy(Player playerCharacter,
                               Enemy enemyCharacter) {
        String message = ("You have defeated the "
                + enemyCharacter.getEnemyName() + ".\n");
        double goldRewarded = playerCharacter.getGoldBonus()
                *enemyCharacter.getGoldGiven();
        playerCharacter.addGold(goldRewarded);
        message += ("You have been awarded "
                + goldRewarded + " gold.\n");
        double experienceRewarded = enemyCharacter.getExperienceGiven();
        playerCharacter.addExperience(experienceRewarded);
        message += ("You have been awarded " + experienceRewarded
                + " experience.\n");
        message += ((int) (playerCharacter.getExperienceCap()
                - playerCharacter.getExperiencePoints())
                + " experience needed to improve\n");
        outputMessage(message);
        if(playerCharacter.levelUp()) {
            new LevelUpState(playerCharacter);
        } else {
            new HealState(playerCharacter);
        }
    }

}
