package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class DamageBar2 implements ImageTile {

	private Point2D position;

	public DamageBar2(Point2D position) {
		this.position = position;
	}
	
	@Override
	public String getName() {
		return "Red2";
	}

	@Override
	public Point2D getPosition() {
		return this.position;
	}

	@Override
	public int getLayer() {
		return 2;
	}

}
