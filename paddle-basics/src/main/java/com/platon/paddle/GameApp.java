package com.platon.paddle;

import java.util.EnumSet;
import java.util.Set;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public final class GameApp extends Application {

    private final Paddle paddle = new Paddle();
    private final Balls balls = new Balls();
    private final Scorred scored = new Scorred();
    private final Set<KeyCode> pressedKeys = EnumSet.noneOf(KeyCode.class);

    private AnimationTimer gameLoop;
    private long previousTime;
    private double accumulator;

    @Override
    public void start(Stage stage) {
        GameView view = new GameView();
        Scene scene = new Scene(new Group(view), GameConfig.WIDTH, GameConfig.HEIGHT);

        scene.setOnKeyPressed(event -> pressedKeys.add(event.getCode()));
        scene.setOnKeyReleased(event -> pressedKeys.remove(event.getCode()));


        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (previousTime == 0) {
                    previousTime = now;
                    view.render(paddle, balls, scored);
                    view.render(paddle, balls, scored);
                    return;
                }

                double frameTime = Math.min(
                        (now - previousTime) / 1_000_000_000.0,
                        GameConfig.MAX_FRAME_TIME);
                previousTime = now;
                accumulator += frameTime;

                while (accumulator >= GameConfig.FIXED_STEP) {
                    double directionX = axis(KeyCode.A, KeyCode.D);
                    double directionY = axis(KeyCode.W, KeyCode.S);
                    double rotation = rotation(KeyCode.LEFT, KeyCode.RIGHT);
                    boolean strait = straiten(KeyCode.UP);

                    paddle.updatepaddel(directionX, directionY, rotation, strait, GameConfig.FIXED_STEP);
                    balls.updateBalls();
                    if(scored.Scored(balls)){
                        balls.restart();
                        scored.plusScore();
                    };
                    if(balls.getInitY() > 1280) {
                        balls.restart();
                    }
                    balls.checkColision(paddle, directionX, directionY);
                    accumulator -= GameConfig.FIXED_STEP;
                }

                view.render(paddle, balls, scored);
            }
        };

        stage.setTitle("Paddle Basics");
        stage.setScene(scene);
        stage.setResizable(false);
        view.render(paddle, balls, scored);
        stage.show();
        stage.centerOnScreen();
        view.setFocusTraversable(true);
        view.requestFocus();
        gameLoop.start();
    }

    private int axis(KeyCode negativeKey, KeyCode positiveKey) {
        int negative = pressedKeys.contains(negativeKey) ? 1 : 0;
        int positive = pressedKeys.contains(positiveKey) ? 1 : 0;
        return positive - negative;
    }

    private int rotation(KeyCode RotationL, KeyCode RotationR) {
        int negative = pressedKeys.contains(RotationL) ? 1 : 0;
        int positive = pressedKeys.contains(RotationR) ? 1 : 0;
        return positive - negative;
    }

    private boolean straiten(KeyCode straight) {
        boolean realno = pressedKeys.contains(straight);
        return realno;
    }

    @Override
    public void stop() {
        if (gameLoop != null) {
            gameLoop.stop();
        }
        pressedKeys.clear();
    }
}

