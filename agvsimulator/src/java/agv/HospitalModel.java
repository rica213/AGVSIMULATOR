package agv;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/** class that implements the Model of AGV Simulator application */
public class HospitalModel extends GridWorldModel {

	// constants for the grid objects
	public static final int CART = 4;
	public static final int DEST = 8;
	public static final int LIFT = 16;
	public static final int PARKING = 32;
	public static final int PICKUP = 64;
	// public static final int LIFT = 100;
	
	boolean carryingCart = false; // whether agv is carrying cart or not

	// the grid size
	public static final int GSize = 20;
	
	/** Locations **/
	static Location lDest = new Location(0, GSize - 1);
	
	static Location lPickUp1 = new Location(0, 0);
	static Location lPickUp2 = new Location(GSize - 1, 0);
	static Location lPickUp3 = new Location(0, GSize / 4);
	static Location lPickUp4 = new Location(GSize - 1, (GSize / 2) - 3);
	static Location lPickUp5 = new Location(0, (GSize / 2) - 2);
	static Location lPickUp6 = new Location(GSize - 1, GSize / 2);
	static Location lPickUp7 = new Location(0, (GSize / 2) + 1);
	static Location lPickUp8 = new Location(0, GSize - 6);
	static Location lPickUp9 = new Location(GSize - 1, GSize - 6);
	static Location lPickUp10 = new Location(0, GSize - 4);
	static Location lPickUp11 = new Location(GSize / 2, GSize - 1);
	static Location lPickUp12 = new Location(GSize - 1, GSize - 1);

	static Location lParkingn1 = new Location(GSize - 1, GSize / 5);
	static Location lParkingn2 = new Location(GSize - 2, GSize / 5);
	static Location lParkingn3 = new Location(GSize - 3, GSize / 5);
	static Location lParkingn4 = new Location(GSize - 4, GSize / 5);

	// singleton pattern
	protected static HospitalModel model = null;

	private HospitalModel(int w, int h, int nbAgs) {
		super(w, h, nbAgs);
	}

	synchronized public static HospitalModel create(int w, int h, int nbAgs) {
		if (model == null) {
			model = new HospitalModel(w, h, nbAgs);
		}
		return model;
	}

	/** Actions **/
	boolean moveTowards(Location dest) {
		Location agv = getAgPos(2);	
		if (agv.x < dest.x)
			agv.x++;
		else if (agv.x > dest.x)
			agv.x--;
		if (agv.y < dest.y)
			agv.y++;
		else if (agv.y > dest.y)
			agv.y--;

		setAgPos(0, agv); // move the agv in the grid

		// repaint the stake and storage locations
		if (view != null) {
			view.update(lPickUp1.x, lPickUp1.y);
			view.update(lDest.x, lDest.y);
			// setAgPos(1, lift);
		}
		return true;
	}

	boolean takeinCart() {
		if (this.hasObject(PICKUP, getAgPos(0))) {
			remove(PICKUP, getAgPos(0));
			carryingCart = true;
		}
		return true;
	}

	boolean takeoffCart() {
		if (carryingCart) {
			add(PICKUP, getAgPos(0));
			carryingCart = false;
		}
		return true;
	}
}
