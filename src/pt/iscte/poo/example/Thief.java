package pt.iscte.poo.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Thief extends GameElement implements Moveble {

	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	EngineExample eng = EngineExample.getInstance();
	private int hitPoints = 5;
	private List<GameElement> stolenItems = new ArrayList<>();

	public Thief(Point2D position) {
		super(position);
	}

	@Override
	public void move(Hero hero) {
		Point2D heroPos = hero.getPosition();
		Vector2D ThiefVector = Vector2D.movementVector(getPosition(), heroPos);
		Point2D p = getPosition().plus(ThiefVector);
		
		if (notStolen()) {
			if (!(eng.isWall(p))) {
				if (!(eng.isAnObstacle(p))) {
					setPosition(p);
//					System.out.println(this.hitPoints);
				} else {
					if (notStolen()) {
						attack(hero);
					}
				}
			}

		} else {
			Direction aux = getPosition().directionTo(hero.getPosition());
			Direction d = aux.opposite();
			Vector2D vetor = d.asVector();
			Point2D opposite = getPosition().plus(vetor);
			if (!(eng.isWall(opposite))) {
				if (!(eng.isAnObstacle(opposite))) {
					if (!(notStolen())) {
						setPosition(opposite);
						stolenItems.get(0).setPosition(getPosition());
					}
				}
			}
		}
	}

	@Override
	public int getHitPoints() {
		return this.hitPoints;
	}
	
	@Override
	public void attack(Hero hero) {
		Random generator = new Random();
		int stoloenPosition =  generator.nextInt(2);
		System.out.println(stoloenPosition);
		
		if(hero.getArmor()) {
			if(Math.random() < 0.5) {
				if (!(eng.getInventory().get(stoloenPosition) == null)) {
					stolenItems.add((GameElement) eng.getInventory().get(stoloenPosition));
//					stolenItems.get(stoloenPosition).setPosition(getPosition());
					gui.removeImage((ImageTile) eng.getInventory().get(stoloenPosition));
					eng.getInventory().remove(stoloenPosition);
					eng.DecreaseMaxItems();
				}
			}
		} else {
			if (!(eng.getInventory().get(stoloenPosition) == null)) {
				stolenItems.add((GameElement) eng.getInventory().get(stoloenPosition));
	//			stolenItems.get(stoloenPosition).setPosition(getPosition());
				gui.removeImage((ImageTile) eng.getInventory().get(stoloenPosition));
				eng.getInventory().remove(stoloenPosition);
				eng.DecreaseMaxItems();
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
	public String getName() {
		return "Thief";
	}

	@Override
	public boolean isDead() {
		if (getHitPoints() <= 0) {
			return true;
		}
		return false;
	}

	public boolean notStolen() {
		if (stolenItems.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

}
