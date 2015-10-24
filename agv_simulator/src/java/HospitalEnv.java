// Environment code for project agv_simulator

import jason.asSyntax.*;
import jason.environment.*;
import jason.environment.grid.Location;

import java.util.logging.*;

public class HospitalEnv extends Environment {

	// common literals
	public static final Literal pickCargo = Literal.parseLiteral("pick(cargo)");
	public static final Literal putCargo = Literal.parseLiteral("put(cargo)");
	public static final Literal sc = Literal.parseLiteral("search(cargo)");

	public static final Literal atStake = Literal.parseLiteral("at(agv,stake)");
	public static final Literal atStorage = Literal
			.parseLiteral("at(agv,storage)");

	static Logger logger = Logger.getLogger(HospitalEnv.class.getName());

	HospitalModel model; // the model of the grid

	@Override
	public void init(String[] args) {
		model = new HospitalModel();

		if (args.length == 1 && args[0].equals("gui")) {
			HospitalView view = new HospitalView(model);
			model.setView(view);
		}

		updatePercepts();
	}

	/** creates the agents percepts based on the HouseModel */
	void updatePercepts() {
		// clear the percepts of the agents
		clearPercepts("agv");
		clearPercepts("stake");

		// get the agv location
		Location locAGV = model.getAgPos(0);

		// add agent location to its percepts
		if (locAGV.equals(model.lStake)) {
			addPercept("agv", atStake);
		}
		if (locAGV.equals(model.lStorage)) {
			addPercept("agv", atStorage);
		}

		if (model.cargoCount > 0) {
			addPercept("agv", sc);
		}
	}

	@Override
	public boolean executeAction(String ag, Structure action) {
		System.out.println("[" + ag + "] doing: " + action);
		boolean result = false;
		if (action.equals(pickCargo)) {
			result = model.pickCargo();

		} else if (action.equals(putCargo)) {
			result = model.putCargo();

		} else if (action.getFunctor().equals("move_towards")) {
			String l = action.getTerm(0).toString();
			Location dest = null;
			if (l.equals("stake")) {
				dest = model.lStake;
			} else if (l.equals("dest")) {
				dest = model.lStorage;
			}

			try {
				result = model.moveTowards(dest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.getFunctor().equals("go")) {
			String l = action.getTerm(0).toString();
			Location dest = null;
			if (l.equals("up")) {
				dest = model.lUp;
			} else if (l.equals("down")) {
				dest = model.lDown;
			}
			try {
				result = model.go(dest);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (result) {
			updatePercepts();
			try {
				Thread.sleep(500);
			} catch (Exception e) {
			}
		}
		return result;
	}
}