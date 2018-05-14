#!bin/bash

cd src/main/java

pkill java 
pkill rmiregistry

javac *.java
rmic NodeImpl

rmiregistry 8000 &
java Simulation turing 8000
