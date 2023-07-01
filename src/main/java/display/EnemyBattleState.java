package display;

import characters.Enemy;
import characters.Player;

import java.util.Random;

public class EnemyBattleState extends BattleState implements DisplayState{
    private final int enemyMoveCount;
    private final Enemy enemy;
    private final Player player;
    public EnemyBattleState(Player playerCharacter, Enemy enemyCharacter) {
        super(playerCharacter, enemyCharacter);
        enemy = enemyCharacter;
        player = playerCharacter;
        enemyMoveCount = enemyCharacter.getMoveCount();
        displayScene();
    }

    @Override
    public void displayScene() {
        getAttack();
    }
    public void getAttack() {
        int choiceNumber = -1;
        while(choiceNumber<0) {
            choiceNumber = rand.nextInt(enemyMoveCount);
            if(choiceNumber<=enemyMoveCount-1
                    && enemy.getAttackSlots(choiceNumber)
                    != null) {
                double damage = this.doDamage(player, enemy.getAttackSlots(choiceNumber));
                String message = enemy.getEnemyName() + " attacked with "
                        + enemy.getAttackSlots(choiceNumber) + ". \nThey did "
                        + damage + " damage.";
                System.out.println(message);
                if(player.getHealthPoints()!=0) {
                    new PlayerBattleState(player, enemy);
                    break;
                }
            } else {
                choiceNumber = -1;
            }
        }
    }
}
