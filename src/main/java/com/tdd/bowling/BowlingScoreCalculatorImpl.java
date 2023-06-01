package com.tdd.bowling;

import com.tdd.bowling.exceptions.GameOverException;

public class BowlingScoreCalculatorImpl implements IBowlingScoreCalculator {
    private int[][] shots;
    private int shotInTheFrame;
    private int currentFrame;

    public BowlingScoreCalculatorImpl() {
        reset();
    }

    @Override
    public void bowl(int knockedPins) {
        if (currentFrame == 10) {
            throw new GameOverException("All shots are done, game over!");
        }
        int[] frame = shots[currentFrame];
        if (shotInTheFrame == 1) {
            frame[0] = knockedPins;
            if (knockedPins == 10 && currentFrame != 9) {
                currentFrame++;
            } else {
                shotInTheFrame = 2;
            }
        } else {
            if (currentFrame == 9) {
                // 10th frame
                if (shotInTheFrame == 2) {
                    frame[1] = knockedPins;
                    // check to allow third shot
                    if (frame[0] == 10 || frame[0] + frame[1] == 10) {
                        shotInTheFrame = 3;
                    } else {
                        currentFrame++;
                    }
                } else if (shotInTheFrame == 3) {
                    frame[2] = knockedPins;
                    currentFrame++;
                }
            } else {
                frame[1] = knockedPins;
                shotInTheFrame = 1;
                currentFrame++;
            }
        }
    }

    @Override
    public int getScore() {
        for (int i = 0; i < shots.length; i++) {
            if (i < 9) {
                shots[i][2] = shots[i][0] + shots[i][1];
                if (i > 0) {
                    shots[i][2] += shots[i - 1][2];
                }
                int nextShot = shots[i + 1][0];
                int secondNextShot = shots[i + 1][1];
                if (nextShot == 10) {
                    secondNextShot = i + 2 == 10 ? shots[i + 1][1] : shots[i + 2][0];
                }
                if (shots[i][0] == 10) {
                    shots[i][2] += nextShot + secondNextShot;
                } else if (shots[i][0] + shots[i][1] == 10) {
                    shots[i][2] += nextShot;
                }
            }
        }
        return shots[8][2] + shots[9][0] + shots[9][1] + shots[9][2];
    }

    @Override
    public void reset() {
        // we use 10 frames as maximum, and 3 shots as maximum (on 10th frame you can have 3 shots)
        shots = new int[10][3];
        currentFrame = 0;
        shotInTheFrame = 1;
    }
}