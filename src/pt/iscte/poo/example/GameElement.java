package pt.iscte.poo.example;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile {

	private Point2D position;
	private int layer;

	public GameElement(Point2D position) {
		this.position = position;
	}

	public abstract String getName();

	public Point2D getPosition() {
		return this.position;
	}

	public int getLayer() {
		return layer;
	}

	public void setPosition(Point2D point2d) {
		position = point2d;
		
	}
}
