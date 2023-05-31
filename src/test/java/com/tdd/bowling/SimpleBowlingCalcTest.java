package com.tdd.bowling;

import com.tdd.bowling.exceptions.GameOverException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * You can use following links to test
 * https://www.easycalculation.com/sports/bowling-score-calculator.php
 * http://www.fryes4fun.com/Bowling/scoring.htm
 */
public class SimpleBowlingCalcTest {
    private BowlingCalc calc = new SimpleBowlingCalc();

    @BeforeEach
    public void rest() {
        calc.reset();
    }

    @Test
    public void simpleTest() {
        for (int i = 0; i < 10; i++) {
            calc.bowl(5);
            calc.bowl(4);
        }
        Assertions.assertEquals(90, calc.getScore(), "90 pins should be knocked");
    }

    @Test
    public void gameOverTest(){
        for (int i = 0; i < 10; i++) {
            calc.bowl(5);
            calc.bowl(4);
        }
        GameOverException exception = Assertions.assertThrows(GameOverException.class, () -> {
            calc.bowl(1);
        });
        Assertions.assertEquals("game over", exception.getMessage());
    }

    @Test
    public void spearTest() {
        for (int i = 0; i < 3; i++) {
            calc.bowl(5);
            calc.bowl(5);
        }
        calc.bowl(5);
        calc.bowl(4);
        Assertions.assertEquals(54, calc.getScore(), "54 pins should be knocked after 3 spears");
    }

    @Test
    public void strikeTest() {
        for (int i = 0; i < 3; i++) {
            calc.bowl(10);
        }
        calc.bowl(5);
        calc.bowl(4);
        Assertions.assertEquals(83, calc.getScore(), "83 pins should be knocked after 3 strikes");
    }

    @Test
    public void totalStrikeTest(){
        for(int i = 0; i <12; i++){
            calc.bowl(10);
        }
        Assertions.assertEquals(300, calc.getScore(), "300 pins should be knocked");
    }
}