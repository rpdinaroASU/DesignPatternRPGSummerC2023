I implemented a singleton for the game launch. 
The game can only be launched by calling the getInstance() 
method and cannot create more than one instance.

I created a Decorator class for Character classes, 
which is implemented in the CharacterBase class and 
decorated with the enemy and player classes. 
The Decorator class stands out in the characterBattleState 
and enemyBattleState, implementing shared methods that rely 
on the common class to inflict damage on each other.

I used a State design pattern for displays. It begins 
with the initial state, the introstate, which introduces 
the premise and allows you to select a class, name, 
and difficulty level. The game then transitions to the 
'floor' state where a list of enemies to fight is presented. 
Upon selecting an enemy, the game transitions to a battle 
state that alternates between player and enemy turns 
until either defeat, leading to a 'sad ending' state, 
or victory. If you win but don't level up, the game 
moves to a 'heal' state, healing you and returning 
you to the 'floor' state. If you do level up, you have 
the opportunity to alter your selected moves, if new moves 
are learned, before returning to the floor. This cycle 
continues until all enemies are defeated, at which point 
the game either progresses to a new level with difficulty 
scaled according to your level, or moves to a 'victory' state.

Screencast:
https://drive.google.com/file/d/1ZNMzM38woM6T-fs-uXTnZTuY5VqyA9q7/view?usp=sharing
