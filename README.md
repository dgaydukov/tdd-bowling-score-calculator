### TDD with Bowling Score Calculator
This is code for my [medium article](https://medium.com/@gaydukov89/tdd-in-practice-with-bowling-score-calculator-1d5644b1b9c1) to show how we should apply best practice including testing first, when we design and write the code. As a topic I choose bowling score calculator, cause it pretty simple, yet has a lot of different conditions to test.

###### Bowling rules
```
Strike
If you knock down all 10 pins in the first shot of a frame, you get a strike.
How to score: A strike earns 10 points plus the sum of your next two shots.

Spare
If you knock down all 10 pins using both shots of a frame, you get a spare.
How to score: A spare earns 10 points plus the sum of your next one shot.

Open Frame
If you do not knock down all 10 pins using both shots of your frame (9 or fewer pins knocked down), you have an open frame.
How to score: An open frame only earns the number of pins knocked down.

The 10th Frame (10th frame is a bit different)
If you roll a strike in the first shot of the 10th frame, you get 2 more shots.
If you roll a spare in the first two shots of the 10th frame, you get 1 more shot.
If you leave the 10th frame open after two shots, the game is over and you do not get an additional shot.
How to Score: The score for the 10th frame is the total number of pins knocked down in the 10th frame.
```


