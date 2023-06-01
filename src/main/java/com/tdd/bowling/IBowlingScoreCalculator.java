package com.tdd.bowling;

public interface IBowlingScoreCalculator {
    void bowl(int knockedPins);
    int getScore();

    /**
     * Reset the game and score to 0
     */
    void reset();
}