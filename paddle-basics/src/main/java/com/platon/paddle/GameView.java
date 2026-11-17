package com.platon.paddle;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

public final class GameView extends Canvas {

    private final Image paddleImage = new Image(
            GameView.class.getResourceAsStream("/images/paddle.png")
    );
    private final Image backgroundImage = new Image(
            GameView.class.getResourceAsStream("/images/backgroundfp.png")
    );

    public GameView() {
        super(GameConfig.WIDTH, GameConfig.HEIGHT);
    }

    public void render(Paddle paddle, Balls balls, Scorred scored) {
        renderback();
        renderpaddle(paddle);
        renderball(balls);
        renderScore(scored);

    }

    void renderback() {
        GraphicsContext graphics = getGraphicsContext2D();


        graphics.translate(0,0);
        graphics.drawImage(backgroundImage, 0, 0, getWidth(), getHeight());
    }


    void renderpaddle(Paddle paddle) {
        GraphicsContext graphics1 = getGraphicsContext2D();

        graphics1.translate(paddle.getX() + GameConfig.PADDLE_WIDTH/2, paddle.getY());

        graphics1.rotate(paddle.getAngle());

        graphics1.drawImage(paddleImage, -GameConfig.PADDLE_WIDTH/2, 0, paddle.getWidth(), paddle.getHeight());

        graphics1.rotate(-paddle.getAngle());

        graphics1.translate(- (paddle.getX() + GameConfig.PADDLE_WIDTH/2), - paddle.getY());
    }

    void renderball(Balls balls){
        List<Color> colors = List.of(
                Color.GREEN,
                Color.BLUE,
                Color.ORANGE
        );

        GraphicsContext graphics2 = getGraphicsContext2D();
        graphics2.setFill(colors.get(balls.getColorball()));
        graphics2.fillOval(balls.getInitX(), balls.getInitY(), 40, 40);

    }


    void renderScore(Scorred score) {

        GraphicsContext graphics = getGraphicsContext2D();

        graphics.setFill(Color.BLACK);
        graphics.setFont(Font.font(90));
        graphics.fillText("Score: " + score.getScore(), 200, 100);

    }


}

