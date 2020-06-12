# 313232084
# malkana1

compile: bin
	javac -cp biuoop-1.4.jar:src -d bin src/*.java 

run:
	java -cp biuoop-1.4.jar:bin Ass7Game

bin:
	mkdir bin

jar:
	jar -cfm ass7game.jar Manifest.txt -C bin .