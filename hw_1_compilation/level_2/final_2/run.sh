javac -sourcepath ./src -d build/classes -cp ./libs/commons-math3-3.6.1.jar ./src/ua/com/level/Greeting.java
cd libs
jar xf commons-math3-3.6.1.jar
cp -rf ./org ../build/classes
cd ../
jar cvfm build/jar/greeting.jar resources/MANIFEST.MF -C build/classes .
java -jar build/jar/greeting.jar