package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Sword extends GameElement implements Catchable {

	public Sword(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Sword";
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public String getKeyID() {
		return null;
	}

}
