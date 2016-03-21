package agv;

// Environment code for project agv_simulator

import jason.asSyntax.Literal;
import jason.asSyntax.Structure;
import jason.environment.Environment;
import jason.environment.grid.Location;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Environnement extends Environment {

	HospitalModel model;
	HospitalView view;

	int sleep = 0;

	// common literals
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

	static Logger logger = Logger.getLogger("Perception");

	@Override
	public void init(String[] args) {
		try {
			model = HospitalModel.renderer();
			clearPercepts();
			if (args[0].equals("gui")) {
				sleep = Integer.parseInt(args[1]);
				HospitalView view = new HospitalView(model);
				model.setView(view);
			}
			updateAgsPercept();
			informAgsEnvironmentChanged();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	private void updateAgsPercept() {
		for (int i = 0; i < model.getNbOfAgs(); i++) {
			updateAgPercept(i);
		}
		if (HospitalView.hasCart) {
			Location lCart = HospitalView.location;
			logger.info("Cart location : " + lCart.x + " ," + lCart.y);
			addPercept(Literal.parseLiteral("in(cart," + lCart.x + ","
					+ lCart.y + ")"));
			if (HospitalView.weight > 0 || HospitalView.weight <= 2000) {
				addPercept(Literal.parseLiteral("weight(cart,"
						+ HospitalView.weight + ")"));
				logger.info("Choose DS10S ");
			} else
				logger.info("Choose DS20L");
		}

	}

	private void updateAgPercept(int ag) {
		// (Integer.parseInt(agName.substring(5))) - 1
		updateAgPercept("agv" + (ag + 1), ag);

	}

	private void updateAgPercept(String agName, int ag) {
		clearPercepts(agName);
		// its location
		Location l = model.getAgPos(ag);
		addPercept(agName, Literal.parseLiteral("pos(" + l.x + "," + l.y + ")"));

		if (ag == 0 || ag == 1) {
			addPercept(agName, Literal.parseLiteral("type(dc10s)"));
		} else if (ag == 2 || ag == 3) {
			addPercept(agName, Literal.parseLiteral("type(dc20l)"));
		}
		if (model.carryingCart) {
			addPercept(agName,
					Literal.parseLiteral("hasState(" + agName + ", busy)"));
		}

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
		} else {
			if (model.hasObject(HospitalModel.CART, x, y)) {
				addPercept(Literal.parseLiteral("in(cart," + x + "," + y + ")"));
			}
			if (model.hasObject(HospitalModel.AGENT, x, y)) {
				addPercept(agName,
						Literal.parseLiteral("cell(" + x + "," + y + ",agv)"));
			}
		}
	}

	// creates the agents percepts based on the HouseModel
	void updatePercepts() {

	}

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

			} else if (action.getFunctor().equals("read")) {
				String l = action.getTerm(0).toString();
				if (l.equals("pickup_location")) {
					logger.info("read the pickup location");

					addPercept(ag,
							Literal.parseLiteral("ready(pickup_location)"));
					result = true;
				} else if (l.equals("destination_location")) {
					logger.info("return the destination location");
					addPercept(ag,
							Literal.parseLiteral("ready(destination_location)"));
					result = true;
				}
			}

			else if (action.getFunctor().equals("move_towards")) {
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
			} else if (action.getFunctor().equals("move_randomly")) {
				result = model.move_randomly();
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