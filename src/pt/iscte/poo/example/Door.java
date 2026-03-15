package pt.iscte.poo.example;

import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement {

	private String doorID;
	private boolean canPass;
	private String name;
	private String nextRoom;
	private int n;
	private int xNext;
	private int yNext;
	private Point2D newHeroPos;

	public Door(Point2D position, String nextRoom, int xNext, int yNext, String doorID) {
		super(position);
		this.doorID = doorID;
		this.canPass = false;
		this.nextRoom = nextRoom;
		this.doorID = doorID;
		this.xNext = xNext;
		this.yNext = yNext;
		this.newHeroPos = new Point2D(xNext, yNext);
	}

	public Door(Point2D position, String nextRoom, int xNext, int yNext) {
		super(position);
		this.doorID = " ";
		this.canPass = true;
		this.nextRoom = nextRoom;
		this.newHeroPos = new Point2D(xNext, yNext);
	}

	@Override
	public String getName() {
		if (this.canPass == true) {
			this.name = "DoorOpen";
		} else {
			this.name = "DoorClosed";
		}
		return this.name;
	}

	public int getLayer() {
		return 2;
	}

	public String getDoorId() {
		return this.doorID;
	}

	public boolean getDoorState() {
		return this.canPass;
	}

	public int getN() {
		if (this.nextRoom.equals("room1")) {
			this.n = 1;
		} else if (this.nextRoom.equals("room2")) {
			this.n = 2;
		} else if (this.nextRoom.equals("room3")) {
			this.n = 3;
		}
		return this.n;
	}
	
	public Point2D getNewHeroPos() {
		return this.newHeroPos;
	}
	
	public int getX() {
		return this.xNext;
	}

	public int getY() {
		return this.yNext;
	}
	
	public String getNextRoom() {
		return this.nextRoom;
	}
}
