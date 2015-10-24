import jason.environment.grid.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/** class that implements the View of AGV Simulator application */
public class HospitalView extends GridWorldView {

	HospitalModel hmodel;
	static final int AREA_WIDTH = 1024;

	public HospitalView(HospitalModel model) {
		super(model, "AGV Simulator", AREA_WIDTH);
		hmodel = model;
		defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
		setVisible(true);
		repaint();
	}

	/** draw application objects */
	@Override
	public void draw(Graphics g, int x, int y, int object) {
		Location lAGV = hmodel.getAgPos(0);
		super.drawAgent(g, x, y, Color.lightGray, -1);
		switch (object) {
		case HospitalModel.STAKE:
			if (lAGV.equals(hmodel.lStake)) {
				super.drawAgent(g, x, y, Color.red, -1);
			}
			g.setColor(Color.black);
			drawString(g, x, y, defaultFont, "STAKE (" + hmodel.availableCargos
					+ ")");
			break;
		case HospitalModel.DEST:
			if (lAGV.equals(hmodel.lStorage)) {
				super.drawAgent(g, x, y, Color.red, -1);
			}
			String d = "DEST";
			if (hmodel.cargoCount > 0) {
				d += " (" + hmodel.cargoCount + ")";
			}
			g.setColor(Color.black);
			drawString(g, x, y, defaultFont, d);
			break;
		}
		repaint();
	}

	@Override
	public void drawAgent(Graphics g, int x, int y, Color c, int id) {
		Location lAGV = hmodel.getAgPos(0);
		if (!lAGV.equals(hmodel.lStorage) && !lAGV.equals(hmodel.lStake)) {
			c = Color.red;
			if (hmodel.carryingCargo)
				c = Color.orange;
			super.drawAgent(g, x, y, c, -1);
			g.setColor(Color.WHITE);
			super.drawString(g, x, y, defaultFont, "");
		}
	}
}
