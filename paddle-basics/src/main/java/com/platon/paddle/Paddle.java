package com.platon.paddle;

// Game data only: this class has no JavaFX dependencies.
public final class Paddle {

    private double x = (GameConfig.WIDTH - GameConfig.PADDLE_WIDTH) / 2;
    private double y = GameConfig.HEIGHT - 100;
    private double Angle;

    public void updatepaddel(double directionX, double directionY, double Rotate, boolean strait, double dt) {
        if (dt <= 0) {
            return;
        }

        if ((Angle < GameConfig.ANGLE_MAX) && Rotate == 1){
            Angle += Rotate * GameConfig.ROTATION_SPEED;
        }
        if ((Angle > - GameConfig.ANGLE_MAX) && Rotate == -1){
            Angle += Rotate * GameConfig.ROTATION_SPEED;
        }

        if (strait) {
            Angle = 0;
        }

        x = clamp(x + directionX * GameConfig.PADDLE_SPEED * dt,
                GameConfig.MIN_X, GameConfig.MAX_X);
        y = clamp(y + directionY * GameConfig.PADDLE_SPEED * dt,
                GameConfig.MIN_Y, GameConfig.MAX_Y);
    }

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAngle() {return Angle;}

    public double getWidth() {
        return GameConfig.PADDLE_WIDTH;
    }

    public double getHeight() {
        return GameConfig.PADDLE_HEIGHT;
    }
}

