package com.learn_spring.run_game_v2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.learn_spring.run_game_v2.game.GameRunner;

@Configuration
@ComponentScan()
public class RunGameApp {

    public static void main(String[] args) {
        try (var conext = new AnnotationConfigApplicationContext(RunGameApp.class)) {
            conext.getBean(GameRunner.class).run();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
