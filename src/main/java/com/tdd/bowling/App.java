package com.tdd.bowling;

public class App {
    public static void main(String[] args){
        System.out.println("Bowling Score Calculator");
        IBowlingScoreCalculator calc = new BowlingScoreCalculatorImpl();
        calc.bowl(10);
        calc.bowl(10);
        calc.bowl(10);
        System.out.println("Current score after 3 strikes: " + calc.getScore());
    }
}