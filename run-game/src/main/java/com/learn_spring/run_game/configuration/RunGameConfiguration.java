package com.learn_spring.run_game.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn_spring.run_game.game.GameControl;
import com.learn_spring.run_game.game.GameRunner;
import com.learn_spring.run_game.game.MarioGame;

@Configuration
public class RunGameConfiguration {

    @Bean
    public GameControl game() {
        return new MarioGame();
    }

    @Bean
    public GameRunner gameRunner(GameControl game) {
        return new GameRunner(game);
    }
}
