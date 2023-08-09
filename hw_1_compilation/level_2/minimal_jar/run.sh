javac -sourcepath ./src -d build/classes ./src/ua/com/level/Greeting.java
jar cvfm build/jar/greeting.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/greeting.jar