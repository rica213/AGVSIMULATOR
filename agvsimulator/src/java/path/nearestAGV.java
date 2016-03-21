// Internal action code for project agvsimulator

package path;

import jason.JasonException;
import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSemantics.Unifier;
import jason.asSyntax.ObjectTermImpl;
import jason.asSyntax.Term;
import jason.environment.grid.Location;

import java.util.logging.Logger;

public class nearestAGV extends DefaultInternalAction {

	private static final long serialVersionUID = 3185515489394410285L;
	 private Logger logger = Logger.getLogger("AgvSimulator."+nearestAGV.class.getName());
	
   public double evaluate(TransitionSystem ts, Term[] args) throws Exception {
        try {
        	DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(CreatePath.g);
        	Location pickup_location = (Location)((ObjectTermImpl) args[0]).getObject();
        	Location pickup_ = (Location)((ObjectTermImpl) args[1]).getObject();
            //return Math.abs(n1 - n2);
        } catch (Exception e) {
            logger.warning("Error in function 'path.distance'! "+e);
        }
        return 0;
    }
	
   @Override protected void checkArguments(Term[] args) throws JasonException {	   
	           super.checkArguments(args); // check number of arguments
	   
	           if ((!(args[0] instanceof ObjectTermImpl))|| (!(args[1] instanceof ObjectTermImpl)))
   
	               throw JasonException.createWrongArgument(this,"argument must be a Location");
	   
	       }

 
	 @Override public Object execute(TransitionSystem ts, Unifier un, Term[]
	            args) throws Exception { // execute the internal action
	            ts.getAg().getLogger().info(
	            "executing internal action 'path.FindShortestPath'"); if (true)
	            { // just to show how to throw another kind of exception throw
	            new JasonException("not implemented!"); }
				return true
						;
	  
	            // everything ok, so returns true return true; 
	            }
	
}
