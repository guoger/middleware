echo Compiling...
mkdir bin
javac -cp ./src -d bin/ src/server/Server.java
javac -cp ./src -d bin/ src/client/Client.java
javac -cp ./src -d bin/ src/de/tu_berlin/kbs/reflect/*.java
echo Run...
echo
java -cp ./bin server.Server &
sleep 1
java -cp ./bin client.Client
