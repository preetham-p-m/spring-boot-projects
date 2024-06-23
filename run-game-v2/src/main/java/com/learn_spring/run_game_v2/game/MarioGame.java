package com.learn_spring.run_game_v2.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.learn_spring.run_game_v2.constants.GameNameQualifier;

@Component
@Primary
@Qualifier(GameNameQualifier.MARIO)
public class MarioGame implements GameControl {
    public void up() {
        System.out.println("Mario: Jump");
    }

    public void down() {
        System.out.println("Mario: Bend");
    }

    public void left() {
        System.out.println("Mario: Go back");
    }

    public void right() {
        System.out.println("Mario: Accelerate");
    }
}
