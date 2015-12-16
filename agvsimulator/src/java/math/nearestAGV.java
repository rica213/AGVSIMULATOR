// Internal action code for project agvsimulator

package math;

import jason.asSemantics.DefaultInternalAction;
import jason.asSemantics.TransitionSystem;
import jason.asSyntax.NumberTerm;
import jason.asSyntax.Term;

import java.util.logging.Logger;

public class nearestAGV extends DefaultInternalAction {

	private static final long serialVersionUID = 3185515489394410285L;
	 private Logger logger = Logger.getLogger("AgvSimulator."+nearestAGV.class.getName());
	
    public double evaluate(TransitionSystem ts, Term[] args) throws Exception {
        try {
            int n1 = (int)((NumberTerm)args[0]).solve();
            int n2 = (int)((NumberTerm)args[1]).solve();
            return Math.abs(n1 - n2);
        } catch (Exception e) {
            logger.warning("Error in function 'math.distance'! "+e);
        }
        return 0;
    }
	
	
	/**
	 * 
	 @Override public Object execute(TransitionSystem ts, Unifier un, Term[]
	 *           args) throws Exception { // execute the internal action
	 *           ts.getAg().getLogger().info(
	 *           "executing internal action 'math.FindShortestPath'"); if (true)
	 *           { // just to show how to throw another kind of exception throw
	 *           new JasonException("not implemented!"); }
	 * 
	 *           // everything ok, so returns true return true; }
	 */
}
