package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Scorpion extends GameElement implements Moveble {

	EngineExample eng = EngineExample.getInstance();
	private int hitPoints = 2;
	private boolean isPoisoned; 

	public Scorpion(Point2D position) {
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
				if (!(hero.getHealingPotion()) && isPoisoned) {
					attack(hero);
				}
			} else {
				if (!(hero.getHealingPotion())) {
					if (hero.getHeroMoved()) {
						attack(hero);
						isPoisoned = true;
					}
				}
			}
			System.out.println(this.hitPoints);
		}
	}

	@Override
	public int getHitPoints() {
		return this.hitPoints;
	}

	@Override
	public void attack(Hero hero) {
		hero.setHitPoints(hero.getHitPoints() - damage());
		eng.heroGotDamage();
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
	public String getName() {
		return "Scorpio";
	}

	@Override
	public boolean isDead() {
		if (getHitPoints() <= 0) {
			return true;
		}
		return false;
	}

}
