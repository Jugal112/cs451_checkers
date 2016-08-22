# cs451_checkers
Checkers game made in Java
To generate a runnable jar, run: "mvn package -DskipTests" and then select cs451_checkers\checkers-project\target\checkers-project-1.0-SNAPSHOT-jar-with-dependencies.jar
To generate static analisys and code coverage reports, run " mvn clean; mvn package -DskipTests; mvn findbugs:findbugs site" and navigate to cs451_checkers\checkers-project\target\site\project-reports.html