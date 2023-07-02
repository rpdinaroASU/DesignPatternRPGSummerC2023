package display;

import characters.Attack;
import characters.Enemy;
import characters.Player;

/**
 * Enemy attack state.
 * @author Ryan Dinaro
 * @version 7/1/2023
 */
public class EnemyBattleState extends BattleState{
    /**
     * The enemy attack State.
     * Moves to Death state or Player Battle State
     * @param playerCharacter player character
     * @param enemyCharacter enemy characters
     */
    public EnemyBattleState(Player playerCharacter, Enemy enemyCharacter) {
        getAttack(playerCharacter,enemyCharacter);
    }

    /**
     * Chooses the enemy attack and makes it
     */
    public void getAttack(Player player, Enemy enemy) {
        int choiceNumber = -1;
        Attack[] enemyMoveArr = getCharacterAttacks(enemy);
        choiceNumber = getRandomInt(enemyMoveArr.length) - 1;
        double damage = this.doDamage(player, enemy.getAttackSlots(choiceNumber));
        String message = enemy.getEnemyName() + " attacked with "
                + enemy.getAttackSlots(choiceNumber) + ".\nThey did "
                + damage + " damage.";
        outputMessage(message);
        if(player.getHealthPoints()!=0) {
            new PlayerBattleState(player, enemy);
        } else {
            new DeathState();
        }
    }
}
