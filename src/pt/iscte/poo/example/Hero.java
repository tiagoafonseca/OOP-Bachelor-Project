package pt.iscte.poo.example;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Hero extends GameElement {

	EngineExample eng = EngineExample.getInstance();
	private int hitPoints = 10;
	private boolean sword;
	private boolean armor;
	private boolean healingpotion;
	private boolean key;
	private boolean heroMoved;

	public Hero(Point2D position) {
		super(position);
	}

	@Override
	public String getName() {
		return "Hero";
	}

	public void move(int key) {

		Direction randDirection = Direction.directionFor(key);
		Vector2D randVector = randDirection.asVector();
		Point2D p = getPosition().plus(randVector);

		if (eng.isTreasure(p)) {
			eng.gameWin();
		} else {
			if (!(eng.isWall(p))) {
				if (!(eng.isAnObstacle(p))) {
					if (eng.isOpenDoor(p)) {
						setPosition(p);
						heroMoved = true;
						System.out.println(this.hitPoints);
					}
					setPosition(p);
					heroMoved = true;
					System.out.println(this.hitPoints);
				} else {
					Moveble a = eng.isEnemy(p);
					if (a != null) {
						attack(a);
						System.out.println(this.hitPoints);
					}
				}
			}
		}
	}

	@Override
	public int getLayer() {
		return 1;
	}

	public int getHitPoints() {
		return this.hitPoints;
	}

	public void attack(Moveble enemy) {
		if (this.sword) {
			enemy.setHitPoints(enemy.getHitPoints() - damage() - 1);
		} else {
			enemy.setHitPoints(enemy.getHitPoints() - damage());
		}
	}

	public int damage() {
		return 1;
	}

	public void setHitPoints(int a) {
		this.hitPoints = a;
	}

	public void cure() {
		if (healingpotion) {
			if (this.hitPoints <= 5) {
				eng.heroRecover1();
				this.hitPoints = this.hitPoints + 5;
			} else {
				int aux = 10 - this.hitPoints;
				this.hitPoints = this.hitPoints + (10 - this.hitPoints);
				eng.heroRecover2(aux);
			}
		}
	}

	public boolean getHeroMoved() {
		return heroMoved;
	}

	public void setHealingPotion(boolean a) {
		this.healingpotion = a;
	}

	public boolean getHealingPotion() {
		return this.healingpotion;
	}

	public void setSword(boolean a) {
		this.sword = a;
	}

	public boolean getSword() {
		return this.sword;
	}

	public void setArmor(boolean a) {
		this.armor = a;
	}

	public boolean getArmor() {
		return this.armor;
	}

	public void setKey(boolean a) {
		this.key = a;
	}

	public boolean getKey() {
		return this.key;
	}

}
