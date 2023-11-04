@echo off
echo Deploy started

echo Application is building
call mvnw clean package
echo .

echo Application is running
call java -jar ./target/t2consulting-1.0-SNAPSHOT.jar
echo .

echo Deploy completed
pause