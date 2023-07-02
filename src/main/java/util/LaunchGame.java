package util;

import display.IntroState;

/**
 * This class launches the game as it is called.
 */
public final class LaunchGame {
    /**
     * Establish a singleton.
     */
    private static LaunchGame game = null;

    /**
     * Calling this class will begin launching the RPG game,
     * if an instance is already running, it will simply return
     * the single instance.
     * @return instance of LaunchGame
     */
    public static LaunchGame getInstance() {
        if(game == null) {
            game = new LaunchGame();
        }
        return game;
    }

    /**
     * This constructor will initialize the game.
     */
    private LaunchGame() {
        new IntroState();
    }

}
