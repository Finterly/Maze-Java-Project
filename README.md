# Maze_Project
Java project

Example game below: 



Introduction: Arthur, the dog 🐶, chased a car and is now far away from home. Help Arthur get home.
Walls: | or --- walls, -d- doors, -b- breakable walls, | or --- fake walls.
Hidden items: key to open doors, hammer to break walls, trophy for points.
Rules: fail minus 1 point, retrace step minus 1 point, trophy doubles points. Reach home before you run out of points!

Enter: P to Play or X to Exit:

+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |           b   |
+   +   +---+   +   +
|           d   |   |
+   +---+   +   +   +
|       |   |       |
+---+---+   +   +   +
|🚙         |   |   |
+---+---+---+---+---+
p

Welcome! Arthur has entered the maze. 
Enter: N/S/E/W to Move or X to Exit
NAME: Arthur, POSSESSIONS: []
MOVES: 0, FAILS: 0, POINTS: 50
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |           b   |
+   +   +---+   +   +
|           d   |   |
+   +---+   +   +   +
|       |   |       |
+---+---+   +   +   +
|🐶         |   |   |
+---+---+---+---+---+
e
Move successful.
Found a trophy! 🍖Points doubled!
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 1, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |           b   |
+   +   +---+   +   +
|           d   |   |
+   +---+   +   +   +
|       |   |       |
+---+---+   +   +   +
|🐾  🐶     |   |   |
+---+---+---+---+---+
e
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 2, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |           b   |
+   +   +---+   +   +
|           d   |   |
+   +---+   +   +   +
|       |   |       |
+---+---+   +   +   +
|🐾  🐾  🐶 |   |   |
+---+---+---+---+---+
n
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 3, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |           b   |
+   +   +---+   +   +
|           d   |   |
+   +---+   +   +   +
|       |🐶 |       |
+---+---+   +   +   +
|🐾  🐾  🐾 |   |   |
+---+---+---+---+---+
n
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 4, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |           b   |
+   +   +---+   +   +
|        🐶 d   |   |
+   +---+   +   +   +
|       |🐾 |       |
+---+---+   +   +   +
|🐾  🐾  🐾 |   |   |
+---+---+---+---+---+
w
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 5, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |           b   |
+   +   +---+   +   +
|    🐶  🐾 d   |   |
+   +---+   +   +   +
|       |🐾 |       |
+---+---+   +   +   +
|🐾  🐾  🐾 |   |   |
+---+---+---+---+---+
n
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 6, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐶         b   |
+   +   +---+   +   +
|    🐾  🐾 d   |   |
+   +---+   +   +   +
|       |🐾 |       |
+---+---+   +   +   +
|🐾  🐾  🐾 |   |   |
+---+---+---+---+---+
e
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 7, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐶     b   |
+   +   +---+   +   +
|    🐾  🐾 d   |   |
+   +---+   +   +   +
|       |🐾 |       |
+---+---+   +   +   +
|🐾  🐾  🐾 |   |   |
+---+---+---+---+---+
e
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 8, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐾  🐶 b   |
+   +   +---+   +   +
|    🐾  🐾 d   |   |
+   +---+   +   +   +
|       |🐾 |       |
+---+---+   +   +   +
|🐾  🐾  🐾 |   |   |
+---+---+---+---+---+
s
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 9, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐾  🐾 b   |
+   +   +---+   +   +
|    🐾  🐾 d🐶 |   |
+   +---+   +   +   +
|       |🐾 |       |
+---+---+   +   +   +
|🐾  🐾  🐾 |   |   |
+---+---+---+---+---+
s
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 10, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐾  🐾 b   |
+   +   +---+   +   +
|    🐾  🐾 d🐾 |   |
+   +---+   +   +   +
|       |🐾 |🐶     |
+---+---+   +   +   +
|🐾  🐾  🐾 |   |   |
+---+---+---+---+---+
s
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 11, FAILS: 0, POINTS: 100
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐾  🐾 b   |
+   +   +---+   +   +
|    🐾  🐾 d🐾 |   |
+   +---+   +   +   +
|       |🐾 |🐾     |
+---+---+   +   +   +
|🐾  🐾  🐾 |🐶 |   |
+---+---+---+---+---+
n
Move successful.
You've been here 1 time(s) before! Minus 1 point.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 12, FAILS: 0, POINTS: 99
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐾  🐾 b   |
+   +   +---+   +   +
|    🐾  🐾 d🐾 |   |
+   +---+   +   +   +
|       |🐾 |🐶     |
+---+---+   +   +   +
|🐾  🐾  🐾 |🐾 |   |
+---+---+---+---+---+
e
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 13, FAILS: 0, POINTS: 99
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐾  🐾 b   |
+   +   +---+   +   +
|    🐾  🐾 d🐾 |   |
+   +---+   +   +   +
|       |🐾 |🐾  🐶 |
+---+---+   +   +   +
|🐾  🐾  🐾 |🐾 |   |
+---+---+---+---+---+
n
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 14, FAILS: 0, POINTS: 99
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐾  🐾 b   |
+   +   +---+   +   +
|    🐾  🐾 d🐾 |🐶 |
+   +---+   +   +   +
|       |🐾 |🐾  🐾 |
+---+---+   +   +   +
|🐾  🐾  🐾 |🐾 |   |
+---+---+---+---+---+
n
Move successful.
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 15, FAILS: 0, POINTS: 99
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐾  🐾 b🐶 |
+   +   +---+   +   +
|    🐾  🐾 d🐾 |🐾 |
+   +---+   +   +   +
|       |🐾 |🐾  🐾 |
+---+---+   +   +   +
|🐾  🐾  🐾 |🐾 |   |
+---+---+---+---+---+
n
Move successful.
Arthur is home! You win! ⭐
NAME: Arthur, POSSESSIONS: [ 🍖]
MOVES: 16, FAILS: 0, POINTS: 99
+---+---+---+---+---+
|   |   |       | 🏠|
+   +   +---+   +   +
|   |🐾  🐾  🐾 b🐾 |
+   +   +---+   +   +
|    🐾  🐾 d🐾 |🐾 |
+   +---+   +   +   +
|       |🐾 |🐾  🐾 |
+---+---+   +   +   +
|🐾  🐾  🐾 |🐾 |   |
+---+---+---+---+---+
End of game. Goodbye!

Your score has been written to highscore.txt
Results:Arthur,MidiMaze,16,0,99
2020-07-14 17:13:34
