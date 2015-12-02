echo "Test warcards 2 Players, Warpile 3"
java -jar warcards.jar 2 3
echo "Test warcards 4 Players, Warpile 3"
java -jar warcards.jar 4 3
echo "Test Invalid Command Line"
java -jar warcards.jar
echo "Test Error input"
java -jar warcards.jar A B

echo "Python version of warcards"
echo " 2 players, warpile 3"
python warcards.py 2 3
echo " 4 players, warpile 1"
python warcards.py 4 1

