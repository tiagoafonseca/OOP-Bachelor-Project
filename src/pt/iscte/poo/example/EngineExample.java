package pt.iscte.poo.example;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;

public class EngineExample implements Observer {

	public static final int GRID_HEIGHT = 11;
	public static final int GRID_WIDTH = 10;

	private static EngineExample INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();

	private String userName;
	private Hero hero;
	private int maxOfItems = 0;
	private int turns;
	private boolean doorPassed;
	private int doorDestiny;
	private String keyID;
	private Point2D heroDestiny;
	private List<Moveble> enemies = new ArrayList<>();
	private List<GameElement> wallList = new ArrayList<>();
	private List<Door> doorList = new ArrayList<>();
	private List<GameElement> obstacles = new ArrayList<>();
	private List<GameElement> items = new ArrayList<>();
	private Map<Integer, Catchable> inventory = new HashMap<>();

	public static EngineExample getInstance() {
		if (INSTANCE == null)
			INSTANCE = new EngineExample();
		return INSTANCE;
	}

	private EngineExample() {
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT);
		gui.go();
	}

	public void start() {
		this.userName = gui.askUser("Introduza o seu nome:");
		loadMaps();
		creatLifeBar();
		creatInventoryBar();
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns + "   " + "UserName:" + this.userName + "   "
				+ "Health:" + hero.getHitPoints());
		gui.update();
	}

//	public void addHero() {
//		Hero hero = new Hero(new Point2D(1, 1));
//		obstacles.add(hero);
//		gui.addImage((ImageTile) hero);
//	}

	public void loadMaps() {
		if (doorPassed) {
			gui.clearImages();
			wallList.clear();
			doorList.clear();
			obstacles.clear();
			enemies.clear();
			items.clear();
			String a = Integer.toString(this.doorDestiny);
			creatMap("rooms/room" + a + ".txt");
			this.hero = new Hero(this.heroDestiny);
			obstacles.add(hero);
			creatInventoryBar();
			creatLifeBar();
			loadInventary();
			gui.addImage(hero);
			gui.update();
		} else {
			creatMap("rooms/room" + "0" + ".txt");
			this.hero = new Hero(new Point2D(1, 1));
			obstacles.add(hero);
			gui.addImage(hero);
		}
	}

	public void loadInventary() {
		for (int i = 0; i < 3; i++) {
			if (!(inventory.get(i) == null)) {
				items.add((GameElement) inventory.get(i));
				gui.addImage((ImageTile) inventory.get(i));
			}
		}
	}

	public void creatMap(String mapName) {
		List<ImageTile> tileList = new ArrayList<>();
		Scanner sc;

		try {
			sc = new Scanner(new File(mapName));

			for (int x = 0; x < 10; x++) {
				String line = sc.nextLine();
				for (int y = 0; y < 10; y++) {
					if (line.charAt(y) == '#') {
						tileList.add(new Wall(new Point2D(y, x)));
						Wall wall = new Wall(new Point2D(y, x));
						this.wallList.add(wall);
					} else {
						tileList.add(new Floor(new Point2D(y, x)));
					}
				}
			}

			gui.addImages(tileList);

			while (sc.hasNextLine()) {

				String line1 = sc.nextLine();
				String[] aux = line1.split(",");

				if (aux[0].equals("Bat")) {
					Bat bat = new Bat(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
					obstacles.add(bat);
					enemies.add(bat);
					gui.addImage(bat);
				} else if (aux[0].equals("Skeleton")) {
					Skeleton skeleton = new Skeleton(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
					obstacles.add(skeleton);
					enemies.add(skeleton);
					gui.addImage(skeleton);
				} else if (aux[0].equals("Thug")) {
					Thug thug = new Thug(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
					obstacles.add(thug);
					enemies.add(thug);
					gui.addImage(thug);
				} else if (aux[0].equals("Scorpion")) {
					Scorpion scorpion = new Scorpion(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
					obstacles.add(scorpion);
					enemies.add(scorpion);
					gui.addImage(scorpion);
				} else if (aux[0].equals("Thief")) {
					Thief thief = new Thief(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
					obstacles.add(thief);
					enemies.add(thief);
					gui.addImage(thief);
				} else if (aux[0].equals("Sword")) {
					Sword sword = new Sword(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
					items.add(sword);
					gui.addImage(sword);
				} else if (aux[0].equals("HealingPotion")) {
					HealingPotion healingPotion = new HealingPotion(
							new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
					items.add(healingPotion);
					gui.addImage(healingPotion);
				} else if (aux[0].equals("Key")) {
					Key key = new Key(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])), aux[3]);
					items.add(key);
					gui.addImage(key);
				} else if (aux[0].equals("Armor")) {
					Armor armor = new Armor(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
					items.add(armor);
					gui.addImage(armor);
				} else if (aux[0].equals("Treasure")) {
					Treasure treasure = new Treasure(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])));
					items.add(treasure);
					gui.addImage(treasure);
				} else if (aux[0].equals("Door")) {
					if (aux.length == 7) {
						Door door = new Door(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])), aux[3],
								Integer.parseInt(aux[4]), Integer.parseInt(aux[5]), aux[6]);
						doorList.add(door);
						wallList.add(door);
						gui.addImage(door);
					} else {
						Door door = new Door(new Point2D(Integer.parseInt(aux[1]), Integer.parseInt(aux[2])), aux[3],
								Integer.parseInt(aux[4]), Integer.parseInt(aux[5]));
						doorList.add(door);
						gui.addImage(door);
					}
				}
			}
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	private void creatLifeBar() {
		List<ImageTile> images = new ArrayList<>();
		for (int x = 0; x < 5; x++) {
			images.add(new LifeBar(new Point2D(x, 10)));
		}
		gui.addImages(images);
	}

	private void creatInventoryBar() {
		List<ImageTile> images = new ArrayList<>();
		for (int x = 5; x < 10; x++) {
			images.add(new InventoryBar(new Point2D(x, 10)));
		}
		gui.addImages(images);
	}

	public Moveble isEnemy(Point2D point) {
		Moveble a = null;
		for (Moveble enemy : enemies) {
			if (point.equals(enemy.getPosition())) {
				a = enemy;
			}
		}
		return a;
	}

	public void isAClosedDoor() {
		Door newDoor = null;
		boolean finded = false;
		while (finded == false) {
			for (Door obj : doorList) {
				if (this.keyID.equals(obj.getDoorId())) {
					finded = true;
					newDoor = new Door(obj.getPosition(), obj.getNextRoom(), obj.getX(), obj.getY());
					this.heroDestiny = newDoor.getNewHeroPos();
					wallList.remove(obj);
					gui.removeImage(obj);
				}
			}
			doorList.add(newDoor);
			gui.addImage(newDoor);
		}
	}

	public boolean isOpenDoor(Point2D point) {
		for (Door obj : doorList) {
			if (point.equals(obj.getPosition()) && obj.getDoorState() == true) {
				this.doorPassed = true;
				this.doorDestiny = obj.getN();
				this.heroDestiny = obj.getNewHeroPos();
				loadMaps();
				System.out.println("ok");
				return true;
			}
		}
		return false;
	}

	public boolean isWall(Point2D point) {
		int count = 0;
		for (GameElement obj : wallList) {
			if (point.equals(obj.getPosition())) {
				count++;
			}
		}
		if (count != 0) {
			return true;
		}
		return false;
	}

	public boolean isAnObstacle(Point2D point) {
		for (GameElement obj : obstacles) {
			if (point.equals(obj.getPosition())) {
				return true;
			}
		}
		return false;
	}

	public boolean isCatchable(GameElement obj) {
		if (obj instanceof Catchable) {
			if (hero.getPosition().equals(obj.getPosition())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isTreasure(Point2D point) {
		for(GameElement obj : items) {
			if(obj.getName().equals("Treasure") && point.equals(obj.getPosition())) {
				return true;
			}
		}
		return false;
	}

	public void gameOver() {
		obstacles.remove(hero);
		gui.removeImage((ImageTile) hero);
		gui.dispose();
		gui.setMessage("Game Over");
	}

	public void gameWin() {
		gui.clearImages();
		gui.dispose();
		gui.setMessage("Congrats you win the game");
	}

	@Override
	public void update(Observed source) {

		if (ImageMatrixGUI.getInstance().wasWindowClosed())
			System.out.println("Ending");

		int key = ((ImageMatrixGUI) source).keyPressed();

		if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_UP || key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {

			hero.move(key);
			turns++;

			if (hero.getHitPoints() <= 0) {
				gameOver();
			}

			for (GameElement obj : items) {
				if (isCatchable(obj) && this.maxOfItems <= 2) {
					if (obj.getName().equals("HealingPotion")) {
						hero.setHealingPotion(true);
						hero.cure();
						gui.removeImage((ImageTile) obj);
					} else if (obj.getName().equals("Sword")) {
						hero.setSword(true);
						addinventory((Catchable) obj);
					} else if (obj.getName().equals("Armor")) {
						hero.setArmor(true);
						addinventory((Catchable) obj);
					} else if (obj.getName().equals("Key")) {
						hero.setKey(true);
						addinventory((Catchable) obj);
						this.keyID = getIDFromKey();
						isAClosedDoor();
						System.out.println(getIDFromKey());
					}
				}
			}
			Moveble dead = null;
			for (Moveble obj : enemies) {
				if (!(obj.isDead())) {
					obj.move(hero);
				} else {
					dead = obj;
				}
			}
			if (dead != null) {
				gui.removeImage((ImageTile) dead);
				enemies.remove(dead);
				obstacles.remove((GameElement) dead);
			}

//            if (dead != null)  diferentes maneiras de morrer
//            	dead.die();~

			gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns + "   " + "UserName:" + this.userName + "   "
					+ "Health:" + hero.getHitPoints());
			gui.update();
		}

		if (key == KeyEvent.VK_1) {
			removeImagesFromInventory(0);
		} else if (key == KeyEvent.VK_2) {
			removeImagesFromInventory(1);
		} else if (key == KeyEvent.VK_3) {
			removeImagesFromInventory(2);
		}
	}

	public String getIDFromKey() {
		String a = null;
		for (int i = 0; i < inventory.size(); i++) {
			if (inventory.get(i) instanceof Key) {
				a = inventory.get(i).getKeyID();
				System.out.println(inventory.get(i).getKeyID());
			}
		}
		return a;
	}

	public void addinventory(Catchable obj) {
		int a = 0;
		boolean added = false;
		while (added == false) {
			if (!(inventory.containsKey(a))) {
				added = true;
				obj.setPosition(new Point2D(5 + a, 10));
				inventory.put(a, obj);
				gui.addImage((ImageTile) obj);
				this.maxOfItems++;
				gui.update();
			} else {
				a++;
			}
		}
	}

	private void removeImagesFromInventory(int i) {
		if (inventory.get(i) != null) {
			inventory.get(i).setPosition(hero.getPosition());
			gui.addImage((GameElement) inventory.get(i));
			inventory.remove(i);
			this.maxOfItems--;
			gui.update();
		}
	}

	public int getNumbOfTurns() {
		return this.turns;
	}

	public void heroGotDamage() {
		if (hero.getHitPoints() % 2 != 0) {
			gui.addImage((new DamageBar(new Point2D((hero.getHitPoints() - 1) / 2, 10))));
		} else if (hero.getHitPoints() % 2 == 0) {
			gui.addImage((new DamageBar2(new Point2D((hero.getHitPoints() + 1) / 2, 10))));

		}
	}

	public void heroRecover1() {
		int heroLifeBar = hero.getHitPoints() / 2;
		if (hero.getHitPoints() % 2 == 0) {
			for (int x = heroLifeBar; x <= heroLifeBar + 2; x++) {
				if (x != heroLifeBar + 2) {
					gui.addImage(new LifeBar(new Point2D(x, 10)));
				} else {
					gui.addImage(new DamageBar(new Point2D(x, 10)));
				}
			}
		} else {
			heroLifeBar = (hero.getHitPoints() - 1) / 2;
			for (int x = heroLifeBar; x <= heroLifeBar + 2; x++) {
				gui.addImage(new LifeBar(new Point2D(x, 10)));
			}
		}

	}

	public void heroRecover2(int a) {
		for (int x = a; x < 5; x++) {
			gui.addImage(new LifeBar(new Point2D(x, 10)));
		}
	}

	public Map<Integer, Catchable> getInventory() {
		return inventory;
	}

	public void DecreaseMaxItems() {
		this.maxOfItems--;
	}

}
