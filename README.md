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

# Expected Output for the given Examples
1. input : AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7
2. Outputs:
#***************************************************************************************************************************************************
The distance of the route A-B-C = 9
The distance of the route A-D = 5
The distance of the route A-D-C = 13
The distance of the route A-E-B-C-D = 22
The distance of the route A-E-D = NO SUCH ROUTE
The number of trips starting at C and ending at C with a maximum of 3 stops = (2) via - [C-D-C, C-D-E-B-C, C-E-B-C]
The number of trips starting at A and ending at C with exactly 4 stops = (3) via - [A-B-C, A-D-C, A-D-E-B-C, A-E-B-C, A-B-C-D-C, A-D-C-D-C, A-D-E-B-C-D-C, A-E-B-C-D-C, A-B-C-D-E-B-C, A-D-C-D-E-B-C, A-D-E-B-C-D-E-B-C, A-E-B-C-D-E-B-C, A-B-C-E-B-C, A-D-C-E-B-C, A-D-E-B-C-E-B-C, A-E-B-C-E-B-C]
The length of the shortest route from A to C = (9) via - {A-E-B-C=14, A-B-C=9, A-D-C=13, A-B-C-D-E-B-C=30, A-B-C-E-B-C=18, A-D-E-B-C-D-C=34, A-D-C-D-E-B-C=34, A-D-E-B-C-D-E-B-C=39, A-D-E-B-C-E-B-C=27, A-D-E-B-C=18, A-E-B-C-D-E-B-C=35, A-E-B-C-E-B-C=23, A-D-C-D-C=29, A-B-C-D-C=25, A-D-C-E-B-C=22, A-E-B-C-D-C=30}
The length of the shortest route from B to B = (9) via - {B-C-D-E-B=21, B-C-E-B=9}
The number of different routes from C to C with a distance of less than 30 = (7) via - {C-D-C-E-B-C=25, C-D-E-B-C-D-E-B-C-D-E-B-C=63, C-E-B-C-E-B-C-E-B-C=27, C-D-E-B-C-E-B-C=30, C-E-B-C-D-E-B-C=30, C-E-B-C-D-C=25, C-D-C-D-C-D-C=48, C-D-C-D-C=32, C-E-B-C=9, C-D-E-B-C=21, C-D-E-B-C-D-E-B-C=42, C-E-B-C-E-B-C=18, C-D-C-D-E-B-C=37, C-D-E-B-C-D-C=37, C-D-C=16}
#*****************************************************************************************************************************************************


