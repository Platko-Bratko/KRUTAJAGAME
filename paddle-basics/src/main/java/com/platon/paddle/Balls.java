package com.platon.paddle;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.paint.Color;
import java.util.List;


public final class Balls {

    Random random = new Random();

    double x = random.nextInt(320) + 160;
    double y = - random.nextInt(100);
    private double velocityX;
    private double velocityY;
    private int colisionpause;
    public int colorball = random.nextInt(3);


    public void updateBalls(){
        y += velocityY;
        velocityY += 0.02;
        System.out.println(y);
        x += velocityX;
    }

    public void restart() {
            y = yStart();
            x = xStart();
            velocityX = startVelocityX();
            velocityY = startVelocityY();
    }


    public void checkColision(Paddle paddle, double Xaxis, double Yaxis){
        if (y < paddle.getY() + 20 && y > paddle.getY() - 10 && x > paddle.getX() - 40 + Math.abs(paddle.getAngle()/4) && x < paddle.getX() + 140 - Math.abs(paddle.getAngle()/4) && colisionpause == 0){
            velocityY = - (velocityY - Math.abs(paddle.getAngle()/20) - Yaxis);
            velocityX = (paddle.getAngle()/10 + Xaxis);
            System.out.println("COLLISION");
            colisionpause = 50;
            return;
        }
        if (colisionpause > 0) {
            colisionpause -= 1;
        }
    }

    public double xStart(){
        colorball = random.nextInt(3);
        return random.nextInt(320) + 160;
    }

    public double yStart(){
        return - random.nextInt(100);
    }

    public double getInitX() {
        return x ;
    }
    public double getInitY() {
        return y ;
    }
    public double startVelocityX() {
        return 0 ;
    }
    public double startVelocityY() {
        return 0 ;
    }

    public int getColorball() {
        return colorball;
    }

}
