package display;

import characters.Enemy;
import characters.Player;

/**
 * Enemy attack state.
 * @author Ryan Dinaro
 * @version 7/1/2023
 */
public class EnemyBattleState extends BattleState{
    private final int enemyMoveCount;
    /**
     * The enemy attack State.
     * Moves to Death state or Player Battle State
     * @param playerCharacter player character
     * @param enemyCharacter enemy characters
     */
    public EnemyBattleState(Player playerCharacter, Enemy enemyCharacter) {
        enemyMoveCount = enemyCharacter.getMoveCount();
        getAttack(playerCharacter,enemyCharacter);
    }

    /**
     * Chooses the enemy attack and makes it
     */
    public void getAttack(Player player, Enemy enemy) {
        int choiceNumber = -1;
        while(choiceNumber<0) {
            choiceNumber = getRandomInt(enemyMoveCount);
            if(choiceNumber<=enemyMoveCount-1
                    && enemy.getAttackSlots(choiceNumber)
                    != null) {
                double damage = this.doDamage(player,
                        enemy.getAttackSlots(choiceNumber));
                String message = "\n\n\n\t\t\t"+enemy.getEnemyName()
                        + " attacked with "
                        + enemy.getAttackSlots(choiceNumber)
                        + ". \n\t\t\t\tThey did "
                        + damage + " damage.";
                System.out.println(message);
                if(player.getHealthPoints()!=0) {
                    new PlayerBattleState(player, enemy);
                    break;
                } else {
                    new DeathState();
                }
            } else {
                choiceNumber = -1;
            }
        }
    }
}
