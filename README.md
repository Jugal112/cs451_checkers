   ____ _   _ _____ ____ _  _______ ____  ____  
  / ___| | | | ____/ ___| |/ / ____|  _ \/ ___| 
 | |   | |_| |  _|| |   | ' /|  _| | |_) \___ \ 
 | |___|  _  | |__| |___| . \| |___|  _ < ___) |
  \____|_| |_|_____\____|_|\_\_____|_| \_\____/ 
  
# cs451_checkers
Checkers game made in Java

To generate a runnable jar, 
	run: "mvn package -DskipTests" 
	then select cs451_checkers\checkers-project\target\checkers-project-1.0-SNAPSHOT-jar-with-dependencies.jar

To generate static analysis and code coverage reports,
	run: " mvn clean; mvn package -DskipTests; mvn findbugs:findbugs site"
	navigate to cs451_checkers\checkers-project\target\site\project-reports.html