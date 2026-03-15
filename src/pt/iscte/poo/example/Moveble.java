package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public interface Moveble {
	void move(Hero hero);
	int getHitPoints();
	void attack(Hero hero);
	int damage();
	void setHitPoints(int a);
	Point2D getPosition();
	String getName();
	boolean isDead();
}
