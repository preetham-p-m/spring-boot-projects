package com.learn_spring.run_game_v1.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.learn_spring.run_game_v1.game.GameControl;
import com.learn_spring.run_game_v1.game.GameRunner;
import com.learn_spring.run_game_v1.game.MarioGame;

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
