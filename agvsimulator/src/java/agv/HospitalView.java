package agv;

import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/** class that implements the View of AGV Simulator application */
public class HospitalView extends GridWorldView {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6904075546215382571L;
	HospitalModel hmodel;
	static final int AREA_WIDTH = 1024;

	public HospitalView(HospitalModel model) {
		super(model, "AGV Simulator", AREA_WIDTH);
		hmodel = model;
		defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
		setVisible(true);
		repaint();
	}

	/**
	 * */
	Environnement env = null;

	@Override
	public void initComponents(int width) {
		super.initComponents(AREA_WIDTH);

		// Events handling
		final HospitalModel hm = (HospitalModel) model;
		getCanvas().addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {
				int col = e.getX() / cellSizeW;
				int lin = e.getY() / cellSizeH;
				if (col >= 0 && lin >= 0 && col < getModel().getWidth()
						&& lin < getModel().getHeight()) {
					hm.add(HospitalModel.CART, col, lin);
					update(col, lin);
				}
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseReleased(MouseEvent e) {
			}
		});
	}

	/** draw application objects */
	@Override
	public void draw(Graphics g, int x, int y, int object) {
		Location lAGV = hmodel.getAgPos(0);
		super.drawAgent(g, x, y, Color.lightGray, -1);
		switch (object) {
		case HospitalModel.PICKUP:
			drawPickUpSpur(g, x, y);
			break;
		case HospitalModel.DEST:
			if (lAGV.equals(HospitalModel.lDest)) {
				super.drawAgent(g, x, y, Color.red, -1);
			}
			String d = "DEST";
			g.setColor(Color.black);
			drawString(g, x, y, defaultFont, d);
			break;
		case HospitalModel.PARKING:
			drawParking(g, x, y);
			break;
		case HospitalModel.CART:
			drawCart(g, x, y);
			break;
		}

		repaint();
	}

	@Override
	public void drawAgent(Graphics g, int x, int y, Color c, int id) {
		Location lAGV = hmodel.getAgPos(0);
		if (!lAGV.equals(HospitalModel.lDest)
				&& !lAGV.equals(HospitalModel.lPickUp1)) {
			c = Color.red;

			if (hmodel.carryingCart)
				c = Color.orange;
			super.drawAgent(g, x, y, c, -1);
			// ImageComponent.paint(getGraphics(), x, y, this);
			g.setColor(Color.WHITE);
			super.drawString(g, x, y, defaultFont, "");
		}
	}

	public void drawParking(Graphics g, int x, int y) {
		g.setColor(Color.PINK);
		g.fillRect(x * cellSizeW, y * cellSizeH, cellSizeW, cellSizeH);
		g.setColor(Color.WHITE);
		g.drawRect(x * cellSizeW + 2, y * cellSizeH + 2, cellSizeW - 4,
				cellSizeH - 4);
		g.drawLine(x * cellSizeW + 2, y * cellSizeH + 2, (x + 1) * cellSizeW
				- 2, (y + 1) * cellSizeH - 2);
		g.drawLine(x * cellSizeW + 2, (y + 1) * cellSizeH - 2, (x + 1)
				* cellSizeW - 2, y * cellSizeH + 2);
		/*
		 * try { g.drawImage(ImageIO.read(new
		 * File(getClass().getResource("forklift2.png").getFile())), x, y,
		 * this); } catch (IOException e) { e.printStackTrace(); }
		 */
	}

	public void drawPickUpSpur(Graphics g, int x, int y) {
		g.setColor(Color.GREEN);
		g.fillRect(x * cellSizeW, y * cellSizeH, cellSizeW, cellSizeH);
		g.setColor(Color.WHITE);
		g.drawRect(x * cellSizeW + 2, y * cellSizeH + 2, cellSizeW - 4,
				cellSizeH - 4);
		g.drawLine(x * cellSizeW + 2, y * cellSizeH + 2, (x + 1) * cellSizeW
				- 2, (y + 1) * cellSizeH - 2);
		g.drawLine(x * cellSizeW + 2, (y + 1) * cellSizeH - 2, (x + 1)
				* cellSizeW - 2, y * cellSizeH + 2);
		g.setColor(Color.black);
		drawString(g, x, y, defaultFont, "P");
	}

	public void drawCart(Graphics g, int x, int y) {
		g.setColor(Color.ORANGE);
		g.drawRect(x * cellSizeW + 2, y * cellSizeH + 2, cellSizeW - 4,
				cellSizeH - 4);
		int[] vx = new int[4];
		int[] vy = new int[4];
		vx[0] = x * cellSizeW + (cellSizeW / 2);
		vy[0] = y * cellSizeH;
		vx[1] = (x + 1) * cellSizeW;
		vy[1] = y * cellSizeH + (cellSizeH / 2);
		vx[2] = x * cellSizeW + (cellSizeW / 2);
		vy[2] = (y + 1) * cellSizeH;
		vx[3] = x * cellSizeW;
		vy[3] = y * cellSizeH + (cellSizeH / 2);
		g.fillPolygon(vx, vy, 4);
	}
}
