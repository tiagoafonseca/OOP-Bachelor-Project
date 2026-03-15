package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public interface Catchable {
	
	Point2D getPosition();
	
	String getName();
	
	String getKeyID();
	
	void setPosition(Point2D point);

}
