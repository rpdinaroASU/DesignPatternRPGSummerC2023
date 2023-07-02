package display;

import characters.Attack;
import characters.Player;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class LevelUpState {
    public LevelUpState(Player playerCharacter) {
        System.out.println("\n\nYou leveled up\n\n");
        if(Attack.getMovePool(playerCharacter.getCharacterLevel()-1,playerCharacter.isPhysicalType()).size()
            !=Attack.getMovePool(playerCharacter.getCharacterLevel(),playerCharacter.isPhysicalType()).size()) {
            Scanner scan = new Scanner(System.in, StandardCharsets.UTF_8);
            List<Attack> attackPool = Attack.getMovePool(
                    playerCharacter.getCharacterLevel(),
                    playerCharacter.isPhysicalType());
            for (int x = attackPool.size() - 1; x >= 0; x--) {
                String message = "Attack: (" + (attackPool.size() - x) + ") - "
                        + attackPool.get(x).getAttackName() + "\t"
                        + " Physical Attack: " + (int) attackPool.get(x).getMaxAttack() + "\t"
                        + " Magical Attack: " + (int) attackPool.get(x).getMaxMagicDamage() + "\t"
                        + " Stamina Cost: " + (int) attackPool.get(x).getStaminaCost() + "\t"
                        + " Mana Cost: " + (int) attackPool.get(x).getManaCost();
                System.out.println(message);
            }
            int maxSlot = (attackPool.size() < playerCharacter.getAttackSlotCount())
                    ? attackPool.size() : playerCharacter.getAttackSlotCount();
            for (int y = 0; y < maxSlot; y++) {
                int choiceNumber = -1;
                System.out.println("Select an attack for slot #" + (y + 1));
                while (choiceNumber < 0 || choiceNumber > attackPool.size()) {
                    String input = scan.nextLine();
                    try {
                        choiceNumber = Integer.parseInt(input);
                    } catch (Exception e) {
                        System.out.println("Enter a valid input.");
                        choiceNumber = -1;
                        y--;
                        continue;
                    }
                    if (choiceNumber > 0 && choiceNumber <= attackPool.size()) {
                        playerCharacter.setAttackSlot(attackPool.get(
                                attackPool.size() - (choiceNumber)), y + 1);
                    } else {
                        System.out.println("You do not have that slot");
                        y--;
                        choiceNumber = -1;
                    }
                }
            }
        }
    }
}
