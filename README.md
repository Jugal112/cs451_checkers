# cs451_checkers
Checkers game made in Java

To generate a runnable jar, 
1. run: "mvn package -DskipTests" 
2. then select cs451_checkers\checkers-project\target\checkers-project-1.0-SNAPSHOT-jar-with-dependencies.jar

To generate static analysis and code coverage reports,
1. run: " mvn clean; mvn package -DskipTests; mvn findbugs:findbugs site"
2. navigate to cs451_checkers\checkers-project\target\site\project-reports.html