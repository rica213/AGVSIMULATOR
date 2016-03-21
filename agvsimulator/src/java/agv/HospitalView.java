package agv;

import jason.environment.grid.GridWorldView;
import jason.environment.grid.Location;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import java.util.logging.Logger;

/** class that implements the View of AGV Simulator application */
public class HospitalView extends GridWorldView {
	static Logger logger = Logger.getLogger("Hospital Environment");
	private static final long serialVersionUID = 6904075546215382571L;
	// HospitalModel hmodel;
	static final int AREA_WIDTH = 1024;

	/**
	 * constructor
	 * */
	public HospitalView(HospitalModel model) {
		super(model, "AGV Simulator", AREA_WIDTH);
		// hmodel = model;
		defaultFont = new Font("Arial", Font.BOLD, 16); // change default font
		setVisible(true);
		repaint();
	}

	/**
	 * 
	 */
	Random rand = new Random();
	Environnement env = null;
	public static Location location;
	public static int weight;
	public static boolean hasCart;                                                                        
	
	public static boolean isHasCart() {
		return hasCart;
	}

	@Override
	public void initComponents(int width) {
		super.initComponents(width);

		// Events handling
		getCanvas().addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {

				int col = e.getX() / cellSizeW;
				int lin = e.getY() / cellSizeH;
				location = new Location(col, lin);
				if (location.equals(HospitalModel.lPickUp1)
						|| location.equals(HospitalModel.lPickUp2)
						|| location.equals(HospitalModel.lPickUp3)
						|| location.equals(HospitalModel.lPickUp4)
						|| location.equals(HospitalModel.lPickUp5)
						|| location.equals(HospitalModel.lPickUp6)
						|| location.equals(HospitalModel.lPickUp7)
						|| location.equals(HospitalModel.lPickUp8)
						|| location.equals(HospitalModel.lPickUp9)
						|| location.equals(HospitalModel.lPickUp10)
						|| location.equals(HospitalModel.lPickUp11)
						|| location.equals(HospitalModel.lPickUp12)) {

					HospitalModel hm = (HospitalModel) model;
					update(col, lin);
					hm.add(HospitalModel.CART, col, lin);
					hasCart = true;
					// logger.info("Cart location : "+col +" ,"+lin);
					weight = rand.nextInt(7000) + 1;
				} else {
					logger.info("Put the cart on the pickup cart");
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
		Location lAGV = model.getAgPos(0);
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
		Location lAGV = model.getAgPos(0);
		if (!lAGV.equals(HospitalModel.lDest)
				&& !lAGV.equals(HospitalModel.lPickUp1)) {
			c = Color.red;

			// if (model.carryingCart)
			// c = Color.orange;
			super.drawAgent(g, x, y, c, -1);
			// ImageComponent.paint(getGraphics(), x, y, this);
			g.setColor(Color.WHITE);
			super.drawString(g, x, y, defaultFont, "" + (id + 1));
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

	/**
	 * 
	 * @param g
	 * @param x
	 * @param y
	 */

	public void drawPickUpSpur(Graphics g, int x, int y) {
		// g.setColor(Color.GREEN);
		// g.fillRect(x * cellSizeW, y * cellSizeH, cellSizeW, cellSizeH);
		g.setColor(Color.GREEN);
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
		g.fillRect(x * cellSizeW, y * cellSizeH, cellSizeW, cellSizeH);
	}
}
