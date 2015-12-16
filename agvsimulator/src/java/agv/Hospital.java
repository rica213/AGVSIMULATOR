package agv;

// Environment code for project agv_simulator

import jason.asSyntax.*;
import jason.environment.*;
import jason.environment.grid.Location;

import java.util.logging.*;

public class Hospital extends Environment {
	
	HospitalModel model;
	HospitalView view;
	
	int sleep = 0;

	// common literals
	public static final Literal inCartPickUp = Literal.parseLiteral("in(cart, cart_pickup)");
	public static final Literal pickupLocation = Literal
			.parseLiteral("ready(pickup_location)");

	public static final Literal agvBusy = Literal
			.parseLiteral("hasState(agv, busy)");
	public static final Literal agvFree = Literal
			.parseLiteral("hasState(agv, free)");
	public static final Literal agvReserved = Literal
			.parseLiteral("hasState(agv, reserved)");

	public static final Literal atDestination = Literal
			.parseLiteral("samePosition(agv_location, destination_location)");

	static Logger logger = Logger.getLogger(Hospital.class.getName());



	@Override
	public void init(String[] args) {
		model = Renderer.renderer();
		if (args[0].equals("gui")) {
			sleep = Integer.parseInt(args[1]);
			HospitalView view = new HospitalView(model);
			model.setView(view);
		}

		//updatePercepts();
		updateAgsPercept();
	}

	
	private void updateAgsPercept() {
		for (int i = 0; i < model.getNbOfAgs(); i++) {
			updateAgPercept(i);
		}
	}

	private void updateAgPercept(int ag) {
		updateAgPercept("miner" + (ag + 1), ag);
	}

	private void updateAgPercept(String agName, int ag) {
		clearPercepts(agName);
		// its location
		Location l = model.getAgPos(ag);
		addPercept(agName, Literal.parseLiteral("pos(" + l.x + "," + l.y + ")"));

		//if (model.carryingCart(ag)) {
		//	addPercept(agName, Literal.parseLiteral("carrying_gold"));
	//	}

		// what's around
		updateAgPercept(agName, l.x - 1, l.y - 1);
		updateAgPercept(agName, l.x - 1, l.y);
		updateAgPercept(agName, l.x - 1, l.y + 1);
		updateAgPercept(agName, l.x, l.y - 1);
		updateAgPercept(agName, l.x, l.y);
		updateAgPercept(agName, l.x, l.y + 1);
		updateAgPercept(agName, l.x + 1, l.y - 1);
		updateAgPercept(agName, l.x + 1, l.y);
		updateAgPercept(agName, l.x + 1, l.y + 1);
	}

	private void updateAgPercept(String agName, int x, int y) {
		if (model == null || !model.inGrid(x, y))
			return;
		if (model.hasObject(HospitalModel.OBSTACLE, x, y)) {
			addPercept(agName,
					Literal.parseLiteral("cell(" + x + "," + y + ",obstacle)"));
		} else if (model.hasObject(HospitalModel.AGENT, x, y)) {
				addPercept(agName,
						Literal.parseLiteral("cell(" + x + "," + y + ",agv)"));
			}
		}
	
	
	/** creates the agents percepts based on the HouseModel 
	void updatePercepts() {
		// clear the percepts of the agents
		clearPercepts();

		//addPercept(Literal.parseLiteral("percept(demo)"));
		// get the agv location
		Location locAGV_1 = model.getAgPos(0);

		// add agent location to its percepts
		if (locAGV_1.equals(HospitalModel.lPickUp1)) {
			addPercept("samePosition(agv_location, pickup_location)");
		}
		if (locAGV_1.equals(HospitalModel.lDest)) {
			addPercept(atDestination);
		}
	}
*/
	@Override
	public boolean executeAction(String ag, Structure action) {
		try {
			if (sleep > 0) {
				Thread.sleep(sleep);
			}
			System.out.println("[" + ag + "] doing: " + action);
			boolean result = false;
			if (action.getFunctor().equals("takeinCart")) {
				addPercept(agvBusy);
				result = model.takeinCart();

			} else if (action.getFunctor().equals("takeoffCart")) {
				addPercept(agvFree);
				result = model.takeoffCart();

			} else if (action.getFunctor().equals("move_towards")) {
				addPercept(agvReserved);
				String l = action.getTerm(0).toString();
				Location dest = null;
				if (l.equals("cart_sensor_1")) {
					dest = HospitalModel.lPickUp1;
				} else if (l.equals("dest")) {
					dest = HospitalModel.lDest;
				}

				try {
					result = model.moveTowards(dest);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return result;

		} catch (InterruptedException e) {
		} catch (Exception e) {
			logger.log(Level.SEVERE,
					"error executing " + action + " for " + ag, e);
		}
		return false;
	}
}