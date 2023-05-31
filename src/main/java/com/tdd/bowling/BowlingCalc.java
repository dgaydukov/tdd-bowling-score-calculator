package com.tdd.bowling;

public interface BowlingCalc {
    void bowl(int knockedPins);
    int getScore();

    void reset();
}
