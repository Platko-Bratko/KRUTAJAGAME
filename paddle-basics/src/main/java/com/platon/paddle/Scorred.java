package com.platon.paddle;


public final class Scorred {

    int score = 0;

    boolean Scored(Balls balls) {
        if((balls.getInitX() < 0 || balls.getInitX() > 750) ) {
            if((balls.getInitY() > 100 && balls.getInitY() < 350) && (balls.getColorball() == 2)){
                return true;
            }
            if(((balls.getInitY() > 350 && balls.getInitY() < 620) && (balls.getColorball() == 1))) {
                return true;
            }
            if((balls.getInitY() > 620 && balls.getInitY() < 950) && (balls.getColorball() == 0)) {
                return true;
            }
        }
        return false;
    }

    void plusScore() {
        score +=1;
    }

    int getScore() {
        return score;
    }

}
