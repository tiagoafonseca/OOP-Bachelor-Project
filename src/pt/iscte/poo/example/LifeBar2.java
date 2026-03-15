package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class LifeBar2 implements ImageTile {
	
	private Point2D position;

	public LifeBar2(Point2D position) {
		this.position = position;
	}

	@Override
	public Point2D getPosition() {
		return this.position;
	}

	@Override
	public String getName() {
		return "GreenRed2";
	}

	@Override
	public int getLayer() {
		return 2;
	}


}
