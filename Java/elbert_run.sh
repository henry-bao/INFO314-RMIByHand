# this is a script provided by Elbert Kaifong Cheng on EdStem
# https://edstem.org/us/courses/38230/discussion/3046042

javac *.java

java Server &
P1=$!
java Client
java Client
java Client
java UnitTest

kill $P1
