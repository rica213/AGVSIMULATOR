/* Initial beliefs and rules */

// initially, agv believes that there is some cargos in the stake
available(cargo,stake).

/* Initial goals */

!search(cargo).
    
/* Plans */
    
+!search(cargo) 
	:	available(cargo,stake) & not at(agv,stake)
	<- 	!at(agv,stake).

+!load(cargo)
	:	at(agv,stake)
	<-	!ensure_pick(cargo).
		
+!search(cargo)
   :  not available(cargo,stake) //if there's no cargos in the floor, go to the next floor
   <-!at(agv,lift). // go to the elevator.
   
+!ensure_pick(C) : at(agv,stake)
   <- pick(cargo);
      !ensure_pick(C).
+!ensure_pick(_). 

+!at(agv,P) : not at(agv,P)
  <- move_towards(P);
 	 !at(agv,P).
 	 
+!at(agv,P) : at(agv,P) <- !load(cargo).  

// when the agv picked cargos, the available belief is updated
+stock(cargo,0) 
   :  available(cargo,stake)
   <- -available(cargo,stake).
+stock(cargo,N) 
   :  N > 0 & not available(cargo,stake)
   <- -+available(cargo,stake).
