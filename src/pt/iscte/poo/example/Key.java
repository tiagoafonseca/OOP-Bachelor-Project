package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement implements Catchable {

	private String keyID;
	
	public Key(Point2D position, String keyID) {
		super(position);
		this.keyID = keyID;
	}

	@Override
	public String getName() {
		return "Key";
	}
	
	@Override
	public int getLayer() {
		return 2;
	}

	public String getKeyID() {
		return keyID;
	}

}
