package com.learn_spring.run_game_v2.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.learn_spring.run_game_v2.constants.GameNameQualifier;

@Component
public class GameRunner {

    GameControl game;

    public GameRunner(@Qualifier(GameNameQualifier.SUPER_CONTRA) GameControl game) {
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
