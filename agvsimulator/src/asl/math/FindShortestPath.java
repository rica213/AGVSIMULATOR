// Internal action code for project agvsimulator

package math;

import jason.*;
import jason.asSemantics.*;
import jason.asSyntax.*;

public class FindShortestPath extends DefaultInternalAction {

    @Override
    public Object execute(TransitionSystem ts, Unifier un, Term[] args) throws Exception {
        // execute the internal action
        ts.getAg().getLogger().info("executing internal action 'math.FindShortestPath'");
        if (true) { // just to show how to throw another kind of exception
            throw new JasonException("not implemented!");
        }
        
        // everything ok, so returns true
        return true;
    }
}
