package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends GameElement implements Moveble {

	EngineExample eng = EngineExample.getInstance();
	private int hitPoints = 3;

	public Bat(Point2D position) {
		super(position);
		System.out.println(this.hitPoints);
	}

	@Override
	public String getName() {
		return "Bat";
	}

	@Override
	public void move(Hero hero) {
		if (Math.random() > 0.5) {
			Point2D heroPos = hero.getPosition();
			Vector2D batVector = Vector2D.movementVector(getPosition(), heroPos);
			Point2D p = getPosition().plus(batVector);

			if (!(eng.isWall(p))) {
				if (!(eng.isAnObstacle(p))) {
					setPosition(p);
//					System.out.println(this.hitPoints);
				} else {
					attack(hero);
				}
			}

		} else {
			Direction randDirection = Direction.random();
			Vector2D randVector = randDirection.asVector();
			Point2D p = getPosition().plus(randVector);

			if (!(eng.isWall(p))) {
				if (!(eng.isAnObstacle(p))) {
					setPosition(p);
//					System.out.println(this.hitPoints);
				} else {
					attack(hero);
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
		if (Math.random() > 0.5) {
			hero.setHitPoints(hero.getHitPoints() - damage());
			eng.heroGotDamage();
			if (this.hitPoints < 3) {
				this.hitPoints++;
			}
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