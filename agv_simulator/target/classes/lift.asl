// Agent lift in project agv_simulator

/* Initial beliefs and rules */
pos(up).

/* Initial goals */
!goto(destination).

/* Plans */
+!goto(destination)
:	pos(up)
<-	!go(down);
	+pos(down);
	-pos(up);
	!goto(destination).
	
+!goto(destination)
:	pos(down)
<-	!go(up);
	+pos(up);
	-pos(down);
	!goto(destination).
	
+!go(D)	<-	go(D).
