package com.platon.paddle;

public final class GameConfig {

    public static final double WIDTH = 720;
    public static final double HEIGHT = 1280;

    public static final double PADDLE_WIDTH = 140;
    public static final double PADDLE_HEIGHT = 50;
    public static final double PADDLE_SPEED = 500; // Pixels per second.

    // Limits for the paddle's top-left corner. Y increases downwards.
    public static final double MIN_X = 40;
    public static final double MAX_X = WIDTH - 40 - PADDLE_WIDTH;
    public static final double MIN_Y = HEIGHT * 0.1;
    public static final double MAX_Y = HEIGHT - 40 - PADDLE_HEIGHT;
    public static final double ANGLE_MAX = 50;
    public static final double ROTATION_SPEED = 1;

    public static final double FIXED_STEP = 1.0 / 120.0;
    public static final double MAX_FRAME_TIME = 0.01;

    private GameConfig() {
    }
}

