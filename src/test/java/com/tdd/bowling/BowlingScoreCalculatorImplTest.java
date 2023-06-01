package com.tdd.bowling;

import com.tdd.bowling.exceptions.GameOverException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BowlingScoreCalculatorImplTest {
    private IBowlingScoreCalculator calc = new BowlingScoreCalculatorImpl();

    @BeforeEach
    public void rest() {
        calc.reset();
    }


    @Test
    public void incrementAndResetTest() {
        Assertions.assertEquals(0, calc.getScore(), "score should be 0 when we start");
        calc.bowl(3);
        Assertions.assertEquals(3, calc.getScore(), "score should be 3");
        calc.bowl(5);
        Assertions.assertEquals(8, calc.getScore(), "score should be 8");
        calc.reset();
        Assertions.assertEquals(0, calc.getScore(), "score should be 0 after reset");
    }

    @Test
    public void gameOverTest() {
        for (int i = 0; i < 10; i++) {
            calc.bowl(5);
            calc.bowl(4);
        }
        GameOverException exception = Assertions.assertThrows(GameOverException.class, () -> calc.bowl(1));
        Assertions.assertEquals("All shots are done, game over!", exception.getMessage());
    }

    @Test
    public void allOpenFrameTest() {
        for (int i = 0; i < 10; i++) {
            calc.bowl(5);
            calc.bowl(4);
        }
        Assertions.assertEquals(90, calc.getScore(), "90 pins should be knocked for all open frames");
    }

    @Test
    public void allSpearFrameTest() {
        for (int i = 0; i < 10; i++) {
            calc.bowl(5);
            calc.bowl(5);
        }
        // last shot on 10th frame
        calc.bowl(5);
        Assertions.assertEquals(150, calc.getScore(), "150 pins should be knocked");
    }

    @Test
    public void allStrikeFrameTest() {
        for (int i = 0; i < 12; i++) {
            calc.bowl(10);
        }
        Assertions.assertEquals(300, calc.getScore(), "300 pins should be knocked");
    }

    @Test
    public void nineStrikeFrameLastOpenTest() {
        for (int i = 0; i < 9; i++) {
            calc.bowl(10);
        }
        calc.bowl(5);
        calc.bowl(4);
        Assertions.assertEquals(263, calc.getScore(), "263 pins should be knocked");
    }

    @Test
    public void spearNormalStrikeOpenTest() {
        // spear
        calc.bowl(5);
        calc.bowl(5);
        // open
        calc.bowl(5);
        calc.bowl(4);
        // strike
        calc.bowl(10);
        // open
        calc.bowl(5);
        calc.bowl(4);
        Assertions.assertEquals(52, calc.getScore(), "52 pins should be knocked");
    }

    @Test
    public void strikeSpearStrikeSpearOpenTest() {
        // strike
        calc.bowl(10);
        // spear
        calc.bowl(5);
        calc.bowl(5);
        // strike
        calc.bowl(10);
        // spear
        calc.bowl(5);
        calc.bowl(5);
        // open
        calc.bowl(5);
        calc.bowl(4);
        Assertions.assertEquals(84, calc.getScore(), "84 pins should be knocked");
    }
}