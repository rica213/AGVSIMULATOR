// Agent decision_agent in project agvsimulator

/* Initial beliefs and rules */

/* Initial goals */
!selectNearestAvailableAGV.

/* Plans */
+available(AGV_call)[source(A)]: true 
	<-.print("receive from ",A," : call for available AGV");
	.send(A, askOne,ready(Pickup_location));
	.print("Ask ",A," the pickup location").
	
+ready(_)[source(cart_sensor)]:true
	<- .print("**Request informations from each AGVs**");
	.send(agv1,askOne,pos(X,Y));
	.send(agv1,askOne,hasState(agv1,S));
	.send(agv1,askOne,type(T));
	.send(agv2,askOne,pos(X,Y));
	.send(agv2,askOne,hasState(agv2,S));
	.send(agv2,askOne,type(T));
	.send(agv3,askOne,pos(X,Y));
	.send(agv3,askOne,hasState(agv3,S));
	.send(agv3,askOne,type(T));
	.send(agv4,askOne,pos(X,Y));
	.send(agv4,askOne,hasState(agv4,S));
	.send(agv4,askOne,type(T)).

	
+pos(X,Y)[source(A)]:true<-.print(pos(X,Y)[source(A)]).

+hasState(A,free)[source(A)]:true<-.print(hasState(A,free)).

+type(T)[source(A)]:true
	<-.print(A," : ",type(T)).
	
+!selectNearestAvailableAGV:available(AGV_call)& hasState(A,free)
	<-.print("**Select the nearest available AGV**").
