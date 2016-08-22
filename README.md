# cs451_checkers
Checkers game made in Java

## Proof of Version Control: 

https://github.com/Jugal112/cs451_checkers

## Code Coverage Analysis: 

*Cobertura Coverage Report*

## Static Analysis: 

*FindBugs Report*

## Instructions
To generate a runnable jar, 

1. run: "mvn package -DskipTests" 

2. then select cs451_checkers\checkers-project\target\checkers-project-1.3-jar-with-dependencies.jar

To generate static analysis and code coverage reports,

1. run: " mvn clean; mvn package -DskipTests; mvn findbugs:findbugs site"

2. navigate to cs451_checkers\checkers-project\target\site\project-reports.html

3. navigate to the Cobertura Coverage Report and FindBugs Reports from there


## Release Notes

### Ver 1.3
  - Fixed issue with multi-jump turns not being received by opponent
  - Restart capabilities if users desire to play again after a winner has been declared
  - Fixed issue with highlights not showing second jump opportunities once a player makes the first jump.
  - Confetti cannon and victory sound play for the winner.  Winning is now more fun!
  
### Ver 1.2
  - Fixed issue with double jumps being automatically chosen by the computer
  - Fixed issue where if window is resized vertically then the board stretches vertically instead of scaling proportionately
  - Randomly chosen player for first turn
  - Move highlighting for current player’s turn to show user which moves are valid 
  - Both player’s screens are in sync to show opponent’s decisions in real time by sending moves as they are made.  Before, one player finished their entire turn before it was sent over the network
  - Fixed issue with checkers not being able to move back-left when the space was vacant and there was a checker to the front-left.
  
### Ver 1.1
  - Executable jar contains the entire game.  No need to compile every time before running
  - Resizable window for different screen sizes
  
### Ver 1.0
  - Java 1.8
  - JavaFX integration for graphical user interface
  - Maven integration for managing dependencies, testing, and static analysis
  - AngularJS front-end for cross-platform compatibility
  - Findbugs integration for static analysis
  - Cobertura plugin integration for unit test code coverage
  - Built-in Java libraries used to implement NetworkManager
  - All network communications take place using TCP on port 5500
  - Host screen displays IP address of the Host’s network interface
  - NetworkManager functions ran on worker threads to reduce UI lag
  - Client receives alert if they enter an invalid IP Address
  - Checkers game is playable
  - Fixed file path issues for platform compatibility
  - Fixed issue with getting midpoint between two locations