// Agent decision_agent in project agvsimulator

/* Initial beliefs and rules */

/* Initial goals */

/* Plans */
+available(AGV_call)[source(A)]: true 
	<-.print("receive from ",A," : call for available AGV");
	.send(A, askOne,ready(Pickup_location)).
	
+ready(_)[source(A)]:true<- .print("...computing the nearest agv...").
