package agv;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

import java.util.Random;

/** class that implements the Model of AGV Simulator application */
public class HospitalModel extends GridWorldModel {

	// constants for the grid objects
	public static final int CART = 16;
	public static final int DEST = 8;
	// public static final int LIFT = 16;
	public static final int PARKING = 32;
	public static final int PICKUP = 64;
	// public static final int LIFT = 100;

	boolean carryingCart = false; // whether agv is carrying cart or not

	// the grid size
	public static final int GSize = 20;
	
	Random rand = new Random(); 
	
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
	public static HospitalModel get() {
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
	
	boolean move_randomly() {
		for (int i = 0; i < 4; i++) {
			if ((getAgPos(i).x >= 0) && (getAgPos(i).x < GSize)
					&& (getAgPos(i).y >= 0) && (getAgPos(i).y < GSize)) {
				if(getAgPos(i).x<GSize) {
					getAgPos(i).x++;
				}
				if(getAgPos(i).x==GSize-1) {
					int value = rand.nextInt(1);
					if (value==0) {
						getAgPos(i).y--;
					}
					else if((value==1)&&getAgPos(i).y!=GSize){
						getAgPos(i).y++;
					}
				}
				setAgPos(i, getAgPos(i));
			}
		}

		return true;
	}
	
	/** Rendering **/
	static HospitalModel renderer()throws Exception{
		HospitalModel model = HospitalModel.create(HospitalModel.GSize,
				HospitalModel.GSize, 4);
		// initial position of agvs
		model.setAgPos(0, HospitalModel.lParkingn4);
		model.setAgPos(1, HospitalModel.lParkingn3);
		model.setAgPos(2, HospitalModel.lParkingn2);
		model.setAgPos(3, HospitalModel.lParkingn1);
		
		// initial location of pickup spur, parking and storage
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp1);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp2);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp3);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp4);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp5);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp6);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp7);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp8);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp9);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp10);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp11);
		model.add(HospitalModel.PICKUP, HospitalModel.lPickUp12);
		
		model.add(HospitalModel.DEST, HospitalModel.lDest);
		
		model.add(HospitalModel.PARKING, HospitalModel.lParkingn1);
		model.add(HospitalModel.PARKING, HospitalModel.lParkingn2);
		model.add(HospitalModel.PARKING, HospitalModel.lParkingn3);
		model.add(HospitalModel.PARKING, HospitalModel.lParkingn4);
		// add(LIFT, lLift);

		// create wall
		model.add(GridWorldModel.OBSTACLE, 0, 1);
		model.add(GridWorldModel.OBSTACLE, 0, 2);
		model.add(GridWorldModel.OBSTACLE, 0, 3);
		model.add(GridWorldModel.OBSTACLE, 0, 4);
		model.add(GridWorldModel.OBSTACLE, 0, 6);
		model.add(GridWorldModel.OBSTACLE, 0, 7);
		model.add(GridWorldModel.OBSTACLE, 0, 9);
		model.add(GridWorldModel.OBSTACLE, 0, 10);
		model.add(GridWorldModel.OBSTACLE, 0, 12);
		model.add(GridWorldModel.OBSTACLE, 0, 13);
		model.add(GridWorldModel.OBSTACLE, 0, 15);
		model.add(GridWorldModel.OBSTACLE, 0, 17);
		model.add(GridWorldModel.OBSTACLE, 0, 18);

		model.add(GridWorldModel.OBSTACLE, 1, 1);
		model.add(GridWorldModel.OBSTACLE, 1, 2);
		model.add(GridWorldModel.OBSTACLE, 1, 3);
		model.add(GridWorldModel.OBSTACLE, 1, 4);
		model.add(GridWorldModel.OBSTACLE, 1, 6);
		model.add(GridWorldModel.OBSTACLE, 1, 7);
		model.add(GridWorldModel.OBSTACLE, 1, 9);
		model.add(GridWorldModel.OBSTACLE, 1, 10);
		model.add(GridWorldModel.OBSTACLE, 1, 12);
		model.add(GridWorldModel.OBSTACLE, 1, 13);
		model.add(GridWorldModel.OBSTACLE, 1, 15);
		model.add(GridWorldModel.OBSTACLE, 1, 17);
		model.add(GridWorldModel.OBSTACLE, 1, 18);

		model.add(GridWorldModel.OBSTACLE, 2, 1);
		model.add(GridWorldModel.OBSTACLE, 2, 2);
		model.add(GridWorldModel.OBSTACLE, 2, 3);
		model.add(GridWorldModel.OBSTACLE, 2, 4);
		model.add(GridWorldModel.OBSTACLE, 2, 6);
		model.add(GridWorldModel.OBSTACLE, 2, 7);
		model.add(GridWorldModel.OBSTACLE, 2, 9);
		model.add(GridWorldModel.OBSTACLE, 2, 10);
		model.add(GridWorldModel.OBSTACLE, 2, 12);
		model.add(GridWorldModel.OBSTACLE, 2, 13);
		model.add(GridWorldModel.OBSTACLE, 2, 15);
		model.add(GridWorldModel.OBSTACLE, 2, 17);
		model.add(GridWorldModel.OBSTACLE, 2, 18);

		model.add(GridWorldModel.OBSTACLE, 4, 1);
		model.add(GridWorldModel.OBSTACLE, 4, 2);
		model.add(GridWorldModel.OBSTACLE, 4, 3);
		model.add(GridWorldModel.OBSTACLE, 4, 4);
		model.add(GridWorldModel.OBSTACLE, 4, 6);
		model.add(GridWorldModel.OBSTACLE, 4, 7);
		model.add(GridWorldModel.OBSTACLE, 4, 9);
		model.add(GridWorldModel.OBSTACLE, 4, 10);
		model.add(GridWorldModel.OBSTACLE, 4, 12);
		model.add(GridWorldModel.OBSTACLE, 4, 13);
		model.add(GridWorldModel.OBSTACLE, 4, 15);
		model.add(GridWorldModel.OBSTACLE, 4, 16);
		model.add(GridWorldModel.OBSTACLE, 4, 17);
		model.add(GridWorldModel.OBSTACLE, 4, 18);

		model.add(GridWorldModel.OBSTACLE, 5, 1);
		model.add(GridWorldModel.OBSTACLE, 5, 2);
		model.add(GridWorldModel.OBSTACLE, 5, 3);
		model.add(GridWorldModel.OBSTACLE, 5, 4);
		model.add(GridWorldModel.OBSTACLE, 5, 6);
		model.add(GridWorldModel.OBSTACLE, 5, 7);
		model.add(GridWorldModel.OBSTACLE, 5, 9);
		model.add(GridWorldModel.OBSTACLE, 5, 10);
		model.add(GridWorldModel.OBSTACLE, 5, 12);
		model.add(GridWorldModel.OBSTACLE, 5, 13);
		model.add(GridWorldModel.OBSTACLE, 5, 15);
		model.add(GridWorldModel.OBSTACLE, 5, 16);
		model.add(GridWorldModel.OBSTACLE, 5, 17);
		model.add(GridWorldModel.OBSTACLE, 5, 18);

		model.add(GridWorldModel.OBSTACLE, 6, 1);
		model.add(GridWorldModel.OBSTACLE, 6, 2);
		model.add(GridWorldModel.OBSTACLE, 6, 3);
		model.add(GridWorldModel.OBSTACLE, 6, 4);
		model.add(GridWorldModel.OBSTACLE, 6, 6);
		model.add(GridWorldModel.OBSTACLE, 6, 7);
		model.add(GridWorldModel.OBSTACLE, 6, 9);
		model.add(GridWorldModel.OBSTACLE, 6, 10);
		model.add(GridWorldModel.OBSTACLE, 6, 12);
		model.add(GridWorldModel.OBSTACLE, 6, 13);
		model.add(GridWorldModel.OBSTACLE, 6, 15);
		model.add(GridWorldModel.OBSTACLE, 6, 16);
		model.add(GridWorldModel.OBSTACLE, 6, 17);
		model.add(GridWorldModel.OBSTACLE, 6, 18);

		model.add(GridWorldModel.OBSTACLE, 8, 1);
		model.add(GridWorldModel.OBSTACLE, 8, 2);
		model.add(GridWorldModel.OBSTACLE, 8, 4);
		model.add(GridWorldModel.OBSTACLE, 8, 5);
		model.add(GridWorldModel.OBSTACLE, 8, 7);
		model.add(GridWorldModel.OBSTACLE, 8, 8);
		model.add(GridWorldModel.OBSTACLE, 8, 9);
		model.add(GridWorldModel.OBSTACLE, 8, 10);
		model.add(GridWorldModel.OBSTACLE, 8, 12);
		model.add(GridWorldModel.OBSTACLE, 8, 13);
		model.add(GridWorldModel.OBSTACLE, 8, 15);
		model.add(GridWorldModel.OBSTACLE, 8, 16);
		model.add(GridWorldModel.OBSTACLE, 8, 18);

		model.add(GridWorldModel.OBSTACLE, 9, 1);
		model.add(GridWorldModel.OBSTACLE, 9, 2);
		model.add(GridWorldModel.OBSTACLE, 9, 4);
		model.add(GridWorldModel.OBSTACLE, 9, 5);
		model.add(GridWorldModel.OBSTACLE, 9, 7);
		model.add(GridWorldModel.OBSTACLE, 9, 8);
		model.add(GridWorldModel.OBSTACLE, 9, 9);
		model.add(GridWorldModel.OBSTACLE, 9, 10);
		model.add(GridWorldModel.OBSTACLE, 9, 12);
		model.add(GridWorldModel.OBSTACLE, 9, 13);
		model.add(GridWorldModel.OBSTACLE, 9, 15);
		model.add(GridWorldModel.OBSTACLE, 9, 16);
		model.add(GridWorldModel.OBSTACLE, 9, 18);

		model.add(GridWorldModel.OBSTACLE, 10, 1);
		model.add(GridWorldModel.OBSTACLE, 10, 2);
		model.add(GridWorldModel.OBSTACLE, 10, 4);
		model.add(GridWorldModel.OBSTACLE, 10, 5);
		model.add(GridWorldModel.OBSTACLE, 10, 7);
		model.add(GridWorldModel.OBSTACLE, 10, 8);
		model.add(GridWorldModel.OBSTACLE, 10, 9);
		model.add(GridWorldModel.OBSTACLE, 10, 10);
		model.add(GridWorldModel.OBSTACLE, 10, 12);
		model.add(GridWorldModel.OBSTACLE, 10, 13);
		model.add(GridWorldModel.OBSTACLE, 10, 15);
		model.add(GridWorldModel.OBSTACLE, 10, 16);
		model.add(GridWorldModel.OBSTACLE, 10, 18);

		model.add(GridWorldModel.OBSTACLE, 12, 1);
		model.add(GridWorldModel.OBSTACLE, 12, 2);
		model.add(GridWorldModel.OBSTACLE, 12, 3);
		model.add(GridWorldModel.OBSTACLE, 12, 4);
		model.add(GridWorldModel.OBSTACLE, 12, 6);
		model.add(GridWorldModel.OBSTACLE, 12, 7);
		model.add(GridWorldModel.OBSTACLE, 12, 9);
		model.add(GridWorldModel.OBSTACLE, 12, 10);
		model.add(GridWorldModel.OBSTACLE, 12, 12);
		model.add(GridWorldModel.OBSTACLE, 12, 13);
		model.add(GridWorldModel.OBSTACLE, 12, 15);
		model.add(GridWorldModel.OBSTACLE, 12, 16);
		model.add(GridWorldModel.OBSTACLE, 12, 18);

		model.add(GridWorldModel.OBSTACLE, 13, 1);
		model.add(GridWorldModel.OBSTACLE, 13, 2);
		model.add(GridWorldModel.OBSTACLE, 13, 3);
		model.add(GridWorldModel.OBSTACLE, 13, 4);
		model.add(GridWorldModel.OBSTACLE, 13, 6);
		model.add(GridWorldModel.OBSTACLE, 13, 7);
		model.add(GridWorldModel.OBSTACLE, 13, 9);
		model.add(GridWorldModel.OBSTACLE, 13, 10);
		model.add(GridWorldModel.OBSTACLE, 13, 12);
		model.add(GridWorldModel.OBSTACLE, 13, 13);
		model.add(GridWorldModel.OBSTACLE, 13, 15);
		model.add(GridWorldModel.OBSTACLE, 13, 16);
		model.add(GridWorldModel.OBSTACLE, 13, 18);

		model.add(GridWorldModel.OBSTACLE, 14, 1);
		model.add(GridWorldModel.OBSTACLE, 14, 2);
		model.add(GridWorldModel.OBSTACLE, 14, 3);
		model.add(GridWorldModel.OBSTACLE, 14, 4);
		model.add(GridWorldModel.OBSTACLE, 14, 6);
		model.add(GridWorldModel.OBSTACLE, 14, 7);
		model.add(GridWorldModel.OBSTACLE, 14, 9);
		model.add(GridWorldModel.OBSTACLE, 14, 10);
		model.add(GridWorldModel.OBSTACLE, 14, 12);
		model.add(GridWorldModel.OBSTACLE, 14, 13);
		model.add(GridWorldModel.OBSTACLE, 14, 15);
		model.add(GridWorldModel.OBSTACLE, 14, 16);
		model.add(GridWorldModel.OBSTACLE, 14, 18);

		model.add(GridWorldModel.OBSTACLE, 16, 1);
		model.add(GridWorldModel.OBSTACLE, 16, 2);
		model.add(GridWorldModel.OBSTACLE, 16, 3);
		model.add(GridWorldModel.OBSTACLE, 16, 5);
		model.add(GridWorldModel.OBSTACLE, 16, 6);
		model.add(GridWorldModel.OBSTACLE, 16, 8);
		model.add(GridWorldModel.OBSTACLE, 16, 9);
		model.add(GridWorldModel.OBSTACLE, 16, 12);
		model.add(GridWorldModel.OBSTACLE, 16, 13);
		model.add(GridWorldModel.OBSTACLE, 16, 15);
		model.add(GridWorldModel.OBSTACLE, 16, 16);
		model.add(GridWorldModel.OBSTACLE, 16, 18);

		model.add(GridWorldModel.OBSTACLE, 17, 1);
		model.add(GridWorldModel.OBSTACLE, 17, 2);
		model.add(GridWorldModel.OBSTACLE, 17, 3);
		model.add(GridWorldModel.OBSTACLE, 17, 5);
		model.add(GridWorldModel.OBSTACLE, 17, 6);
		model.add(GridWorldModel.OBSTACLE, 17, 8);
		model.add(GridWorldModel.OBSTACLE, 17, 9);
		model.add(GridWorldModel.OBSTACLE, 17, 12);
		model.add(GridWorldModel.OBSTACLE, 17, 13);
		model.add(GridWorldModel.OBSTACLE, 17, 15);
		model.add(GridWorldModel.OBSTACLE, 17, 16);
		model.add(GridWorldModel.OBSTACLE, 17, 18);

		model.add(GridWorldModel.OBSTACLE, 18, 1);
		model.add(GridWorldModel.OBSTACLE, 18, 2);
		model.add(GridWorldModel.OBSTACLE, 18, 3);
		model.add(GridWorldModel.OBSTACLE, 18, 5);
		model.add(GridWorldModel.OBSTACLE, 18, 6);
		model.add(GridWorldModel.OBSTACLE, 18, 8);
		model.add(GridWorldModel.OBSTACLE, 18, 9);

		model.add(GridWorldModel.OBSTACLE, 19, 1);
		model.add(GridWorldModel.OBSTACLE, 19, 2);
		model.add(GridWorldModel.OBSTACLE, 19, 3);
		model.add(GridWorldModel.OBSTACLE, 19, 5);
		model.add(GridWorldModel.OBSTACLE, 19, 6);
		model.add(GridWorldModel.OBSTACLE, 19, 8);
		model.add(GridWorldModel.OBSTACLE, 19, 9);
		model.add(GridWorldModel.OBSTACLE, 19, 11);
		model.add(GridWorldModel.OBSTACLE, 19, 12);
		model.add(GridWorldModel.OBSTACLE, 19, 13);
		model.add(GridWorldModel.OBSTACLE, 19, 15);
		model.add(GridWorldModel.OBSTACLE, 19, 16);
		model.add(GridWorldModel.OBSTACLE, 19, 17);
		model.add(GridWorldModel.OBSTACLE, 19, 18);

		return model;
	}
}
