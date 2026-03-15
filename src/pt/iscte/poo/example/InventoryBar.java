package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class InventoryBar implements ImageTile {

	private Point2D position;

	public InventoryBar(Point2D position) {
		this.position = position;
	}
	
	@Override
	public String getName() {
		return "Black";
	}

	@Override
	public Point2D getPosition() {
		return this.position;
	}

	@Override
	public int getLayer() {
		return 1;
	}
	

}
