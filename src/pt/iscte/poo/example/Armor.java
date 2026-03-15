package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Armor extends GameElement implements Catchable {

	public Armor(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Armor";
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
