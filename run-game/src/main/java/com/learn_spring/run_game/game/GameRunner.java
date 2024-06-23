package com.learn_spring.run_game.game;

public class GameRunner {

    GameControl game;

    public GameRunner(GameControl game) {
        this.game = game;
    }

    public void run() {
        System.out.println("Running game: " + this.game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
