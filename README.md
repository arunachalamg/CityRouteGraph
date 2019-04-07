# CityRouteGraph
Graph model algorithm for city routes

A directed graph where a node represents a town and an edge represents a route between two towns.  The weighting of the edge represents the distance between the two towns.  A given route will never appear more than once, and for a given route, the starting and ending town will not be the same town.

Towns are named using the first few letters of the alphabet from A to D.  A route between two towns (A to B) with a distance of 5 is represented as AB5.

Graph: AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7

# How to execute
1. Download the cityRouteGraph application
2. Install jdk 8 and above version
3. Add/Update proper inputs with file path : CityRouteGraph\src\main\resources\inputcityroutes.txt 
4. Run this main class : CityRouteGraph\src\main\java\com\route\Main.java
