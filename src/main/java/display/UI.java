package display;

import characters.Player;


public class UI {
    private static final int sleepTime = 6000;
    private static final int sleepTimeTemp = 0;
    public UI(){
        Player player = null;
        new IntroState();
    }
    public static void outputMessage(String message) {
        System.out.print(message);
        try {
            Thread.sleep(sleepTimeTemp);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public static void displayPlayerInfo(Player playerCharacter) {
        String message = "Health: " + (int) playerCharacter.getHealthPoints()
                + " / " + (int) playerCharacter.getHealthCap() + "\t\t"
                + "Mana: " + (int) playerCharacter.getManaPoints()
                + " / " + (int) playerCharacter.getManaCap() + "\t\t"
                + "Stamina: " + (int) playerCharacter.getStaminaPoints()
                + " / " + (int) playerCharacter.getStaminaCap();
        System.out.println(message);
    }
}
