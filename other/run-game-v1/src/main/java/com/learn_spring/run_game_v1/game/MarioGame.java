package com.learn_spring.run_game_v1.game;

public class MarioGame implements GameControl {
    public void up() {
        System.out.println("Jump");
    }

    public void down() {
        System.out.println("Bend");
    }

    public void left() {
        System.out.println("Go back");
    }

    public void right() {
        System.out.println("Accelerate");
    }
}
