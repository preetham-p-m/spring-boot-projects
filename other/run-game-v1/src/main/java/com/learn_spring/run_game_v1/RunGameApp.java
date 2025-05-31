package com.learn_spring.run_game_v1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.learn_spring.run_game_v1.configuration.RunGameConfiguration;
import com.learn_spring.run_game_v1.game.GameRunner;

public class RunGameApp {
    public static void main(String[] args) {
        try (var conext = new AnnotationConfigApplicationContext(RunGameConfiguration.class)) {
            conext.getBean(GameRunner.class).run();
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
