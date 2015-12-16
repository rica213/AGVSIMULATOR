// Agent cart_sensor in project agvsimulator

/* Initial beliefs and rules */


/* Initial goals */

!detectCart.

/* Plans */

+!detectCart: in(cart,_,_) 
	<- read(pickup_location);
	!readTag.
	
+!readTag: in(cart,_,_)
	<- read(destination_location);
	!deliverCall.
	
+!deliverCall: ready(Pickup_location)&ready(Destination_location)
	<- +available(AGV_call);
		.send(decision_agent, tell, available(AGV_call)).
		