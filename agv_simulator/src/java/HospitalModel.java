import jason.environment.grid.GridWorldModel;
import jason.environment.grid.Location;

/** class that implements the Model of AGV Simulator application */
public class HospitalModel extends GridWorldModel {

	// constants for the grid objects
	public static final int STAKE = 16;
	public static final int DEST = 32;
	// public static final int LIFT = 100;
	public static final int MErr = 2; // max error in pick cargo
	int nerr; // number of tries of pick cargo
	boolean agvHasCargo = false; // whether agv is carrying cargo or not

	// the grid size
	public static final int GSize = 20;

	boolean carryingCargo = false; // whether the agv is carrying cargo
	int cargoCount = 0; // how many cargo are arrived into storage
	int availableCargos = 5; // how many cargos are available

	Location lStake = new Location(GSize / 2, GSize / 4);
	Location lStorage = new Location(GSize - 1, GSize - 1);

	Location lUp = new Location(GSize - 1, GSize / 4);
	Location lDown = new Location(GSize - 1, GSize - 1);

	public HospitalModel() {
		// create a GSizexGSize grid with one mobile agent
		super(GSize, GSize, 2);

		// initial location of agv
		// ag code 0 means the agv
		setAgPos(0, 0, GSize / 4);
		// initial location of the lift
		// ag code 1 means the lift
		setAgPos(1, GSize - 1, GSize / 4);

		// initial location of stake and storage
		add(STAKE, lStake);
		add(DEST, lStorage);
		// add(LIFT, lLift);
	}

	boolean go(Location dest) {
		Location agv = getAgPos(0);
		Location lift = getAgPos(1);
		if (lift.y < dest.y)
			lift.y++;
		else if (lift.y > dest.y)
			lift.y--;

		
		setAgPos(1, lift);

		// repaint the stake and storage locations
		if (view != null) {
			view.update(lStake.x, lStake.y);
			view.update(lStorage.x, lStorage.y);
			//setAgPos(0, agv);
		}
		return true;
	}

	boolean moveTowards(Location dest) {
		Location agv = getAgPos(0);
		Location lift = getAgPos(1);
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
			view.update(lStake.x, lStake.y);
			view.update(lStorage.x, lStorage.y);
		//	setAgPos(1, lift);
		}
		return true;
	}

	boolean pickCargo() {
		if (this.hasObject(STAKE, getAgPos(0))) {
			if (random.nextBoolean() || nerr == MErr) {
				remove(STAKE, getAgPos(0));
				nerr = 0;
				agvHasCargo = true;
			} else {
				nerr++;
			}
		}
		return true;
	}

	boolean putCargo() {
		if (agvHasCargo) {
			agvHasCargo = false;
			cargoCount++;
			add(STAKE, getAgPos(0));
		}
		return true;
	}

}
