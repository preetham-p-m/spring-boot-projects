package com.learn_spring.run_game_v2.game;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.learn_spring.run_game_v2.constants.GameNameQualifier;

@Component
@Qualifier(GameNameQualifier.SUPER_CONTRA)
public class SuperContraGame implements GameControl {
    public void up() {
        System.out.println("SuperContra: Jump");
    }

    public void down() {
        System.out.println("SuperContra: Bend");
    }

    public void left() {
        System.out.println("SuperContra: Go back");
    }

    public void right() {
        System.out.println("SuperContra: Accelerate");
    }
}
