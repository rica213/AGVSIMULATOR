/* Agent agv in project agvsimulator
 * Savant DC-10S(2,000 Pounds Standard Capacity)
 * Savant DC-20L(7,000 Pounds Standard Capacity) .
 */

/* Initial beliefs */ 
hasState(_, free).
hasState(_, charging).

/* rules */

/* Initial goals */
!move_randomly.

/* Plans */
+!move_randomly : hasState(_, running) 
	<- move_randomly;
	!move_randomly.