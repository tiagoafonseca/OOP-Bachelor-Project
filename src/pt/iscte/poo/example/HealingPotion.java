package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class HealingPotion extends GameElement implements Catchable {

	private int hitPoints =  5;
	
	public HealingPotion(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "HealingPotion";
	}

	public int getHitPoints() {
		return hitPoints;
	}
	
	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public String getKeyID() {
		return null;
	}

//	public void setHitPoints(int hitPoints) {
//		this.hitPoints = hitPoints;
//	}

}
