package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Skeleton extends GameElement implements Moveble {

	EngineExample eng = EngineExample.getInstance();
	private int hitPoints = 5;

	public Skeleton(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Skeleton";
	}

	@Override
	public void move(Hero hero) {
		Point2D heroPos = hero.getPosition();
		Vector2D skeletonVector = Vector2D.movementVector(getPosition(), heroPos);
		Point2D p = getPosition().plus(skeletonVector);
		if (eng.getNumbOfTurns() % 2 == 0) {
			if (!(eng.isWall(p))) {
				if (!(eng.isAnObstacle(p))) {
					setPosition(p);
					System.out.println(this.hitPoints);
				} else {
					attack(hero);
//					System.out.println(this.hitPoints);
				}
			}
		}
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public int getHitPoints() {
		return this.hitPoints;
	}

	@Override
	public void attack(Hero hero) {
		if(hero.getArmor()) {
			if(Math.random() < 0.5) {
				hero.setHitPoints(hero.getHitPoints() - damage());
				eng.heroGotDamage();
			}
		} else {
			hero.setHitPoints(hero.getHitPoints() - damage());
			eng.heroGotDamage();
		}
	}

	@Override
	public int damage() {
		return 1;
	}

	@Override
	public void setHitPoints(int a) {
		this.hitPoints = a;
	}

	@Override
	public boolean isDead() {
		if (getHitPoints() <= 0) {
			return true;
		}
		return false;
	}

}
