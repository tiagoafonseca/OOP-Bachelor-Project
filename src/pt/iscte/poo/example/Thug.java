package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thug extends GameElement implements Moveble {

	EngineExample eng = EngineExample.getInstance();
	private int hitPoints = 10;

	public Thug(Point2D position) {
		super(position);
	}

	@Override
	public void move(Hero hero) {
		Point2D heroPos = hero.getPosition();
		Vector2D skeletonVector = Vector2D.movementVector(getPosition(), heroPos);
		Point2D p = getPosition().plus(skeletonVector);
		if (!(eng.isWall(p))) {
			if (!(eng.isAnObstacle(p))) {
				setPosition(p);
//				System.out.println(this.hitPoints);
			} else {
				attack(hero);
				System.out.println(this.hitPoints);
			}
		}
	}

	@Override
	public int getHitPoints() {
		return this.hitPoints;
	}

	@Override
	public void attack(Hero hero) {
		if(hero.getArmor()) {
			if(Math.random() < 0.5) {
				if(Math.random() < 0.3) {
					hero.setHitPoints(hero.getHitPoints() - damage());
					eng.heroGotDamage();
					System.out.println(this.hitPoints);
				}
			}
		} else {
			if(Math.random() < 0.3) {
				hero.setHitPoints(hero.getHitPoints() - damage());
				eng.heroGotDamage();
				System.out.println(this.hitPoints);
			}
		}
	}

	@Override
	public int damage() {
		return 3;
	}

	@Override
	public void setHitPoints(int a) {
		this.hitPoints = a;
	}

	@Override
	public String getName() {
		return "Thug";
	}

	@Override
	public boolean isDead() {
		if (getHitPoints() <= 0) {
			return true;
		}
		return false;
	}

}
