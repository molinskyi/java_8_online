javac -sourcepath ./src -d build/classes -cp ./libs/commons-math3-3.6.1.jar ./src/ua/com/level/Greeting.java
java -cp build/classes:./libs/commons-math3-3.6.1.jar ua.com.level.Greeting