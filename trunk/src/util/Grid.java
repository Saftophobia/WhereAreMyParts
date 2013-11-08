package util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import wamp.WampOperator;

public class Grid {

	// Enumeration used for describing the status of a cell in the Grid
	public enum GridType {
		Free, Obstacle, RobotPart
	}
	// The grid of in form of 2D array
	private GridType[][] gridCells;
	// list of parts in the board
	private ArrayList<Part> Parts;
	// list of obstacles
	private ArrayList<Point> Obstacles;
	// the length and width of the board
	private int length;
	private int width;

	public Grid(boolean generate) {
		// to distinguish between cloning and creating a new grid
		if (generate) {
			if (true) {
				do {
					length = (int) (Math.random() * 8);
					width = (int) (Math.random() * 8);
				} while (length < 3 || width < 3);
				// grid fills
				gridCells = new GridType[length][width];
				for (GridType[] row : gridCells) {
					Arrays.fill(row, GridType.Free);
				}

				// Obstacles randomization
				Obstacles = new ArrayList<Point>();
				int bound2;
				do {
					bound2 = (int) (Math.random() * length * width * 0.25);
				} while (bound2 < 1);
				for (int i = 0; i < bound2; i++) {
					int obsX;
					int obsY;
					do {
						obsX = (int) (Math.random() * length);
						obsY = (int) (Math.random() * width);
					} while (!gridCells[obsX][obsY].equals(GridType.Free));

					// if (gridCells[obsX][obsY].equals(GridType.Free)) {
					Obstacles.add(new Point(obsX, obsY));
					gridCells[obsX][obsY] = GridType.Obstacle;
					// }
				}

				// Parts randomization
				Parts = new ArrayList<Part>();
				int bound1;
				do {
					bound1 = (int) (Math.random() * length * width * 0.25);
				} while (bound1 < 1);
				for (int i = 0; i < bound1; i++) {
					int partsX;
					int partsY;
					int counter = 0;

					do {
						partsX = (int) (Math.random() * length);
						partsY = (int) (Math.random() * width);
						counter++;

						if (counter > width*length - 1) {

							break;
						}
					} while (!gridCells[partsX][partsY].equals(GridType.Free)
							|| checkAroundFor(partsX, partsY,
									GridType.RobotPart)
							|| checkAroundFor(partsX, partsY, GridType.Obstacle));
					if (gridCells[partsX][partsY].equals(GridType.Free)) {
						Parts.add(new Part(new Point(partsX, partsY)));
						gridCells[partsX][partsY] = GridType.RobotPart;
					}
				}

			} else {
				//this is a static Grid for testing
//				gridCells = new GridType[6][5];
//				for (GridType[] row : gridCells) {
//					Arrays.fill(row, GridType.Free);
//				}
//				length = 6;
//				width = 5;
//				Parts = new ArrayList<Part>();
//				Parts.add(new Part(new Point(1, 0)));
//				gridCells[1][0] = GridType.RobotPart;
//				Parts.add(new Part(new Point(1, 4)));
//				gridCells[1][4] = GridType.RobotPart;
//				Parts.add(new Part(new Point(3, 1)));
//				gridCells[3][1] = GridType.RobotPart;
//				Parts.add(new Part(new Point(5, 1)));
//				gridCells[5][1] = GridType.RobotPart;
//				Parts.add(new Part(new Point(0, 2)));
//				gridCells[0][2] = GridType.RobotPart;
//				
//				Obstacles = new ArrayList<Point>();
//				Obstacles.add(new Point(0, 1));
//				gridCells[0][1] = GridType.Obstacle;
//				Obstacles.add(new Point(3, 2));
//				gridCells[3][2] = GridType.Obstacle;
				
			String grid = 
					"Robo\tFree\tObst\tObst\tFree\tObst\tFree\n" +
					"Free\tRobo\tFree\tRobo\tFree\tFree\tRobo\n" +
					"Obst\tFree\tRobo\tFree\tFree\tObst\tFree\n"+
					"Free\tFree\tFree\tRobo\tFree\tFree\tRobo\n"+
					"Free\tFree\tObst\tFree\tFree\tFree\tFree\n"+
					"Free\tFree\tObst\tFree\tObst\tObst\tFree\n"+
					"Free\tRobo\tFree\tFree\tObst\tFree\tRobo";
			generateGridFromString(grid);
			}
		}
	}

	
	public void generateGridFromString(String Grid){
		String [] rows = Grid.split("\n");
		length = rows.length;
		width = rows[0].split("\t").length;
		gridCells = new GridType[length][width];
		for (GridType[] row : gridCells) {
			Arrays.fill(row, GridType.Free);
		}
		Parts = new ArrayList<Part>();
		Obstacles = new ArrayList<Point>();

		for(int i = 0;i<rows.length;i++){
			String [] cells = rows[i].split("\t");
			for(int j = 0;j<cells.length;j++){
				if(cells[j].equalsIgnoreCase("Robo")){
					Parts.add(new Part(new Point(i, j)));
					gridCells[i][j] = GridType.RobotPart;
				}else{
					if(cells[j].equalsIgnoreCase("Obst")){
						Obstacles.add(new Point(i, j));
						gridCells[i][j] = GridType.Obstacle;
					}
				}
			}
		}
	}
	
	
	// this method is used to verify the existence of a certain GridType around a given location
	public boolean checkAroundFor(int i, int j, GridType type) {

		if (i - 1 > 0) {
			if (gridCells[i - 1][j].equals(type))
				return true;
		}
		if (i + 1 < length) {
			if (gridCells[i + 1][j].equals(type))
				return true;
		}
		if (j - 1 > 0) {
			if (gridCells[i][j - 1].equals(type))
				return true;
		}
		if (j + 1 < width) {
			if (gridCells[i][j + 1].equals(type))
				return true;
		}
		return false;
	}

	public GridType[][] getGridCells() {
		return gridCells;
	}

	public void setGridCells(GridType[][] gridCells) {
		this.gridCells = gridCells;
	}

	public ArrayList<Part> getParts() {
		return Parts;
	}

	public void setParts(ArrayList<Part> parts) {
		Parts = parts;
	}

	public ArrayList<Point> getObstacles() {
		return Obstacles;
	}

	public void setObstacles(ArrayList<Point> obstacles) {
		Obstacles = obstacles;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	
	
//	@SuppressWarnings("unchecked")
//	public Grid fix(WampOperator Operator, int partX, int partY) {
//		Grid fixed = new Grid(false);
//		// Cloning the obstacles not needed -- reference may cause issue take
//		// care !!
//		ArrayList<Point> newObstacles = (ArrayList<Point>) Obstacles.clone();
//		// Fixing the new part point
//		ArrayList<Part> newParts = cloneParts();
//		newParts.get(Operator.getPartIndex()).getLocation().x = partX;
//		newParts.get(Operator.getPartIndex()).getLocation().y = partY;
//		// Fixing the grid cells
//		GridType[][] newGridCells = new GridType[length][width];
//		for (int i = 0; i < length; i++) {
//			for (int j = 0; j < width; j++) {
//				if (i == Parts.get(Operator.getPartIndex()).getLocation().x
//						&& j == Parts.get(Operator.getPartIndex())
//								.getLocation().y) {
//					newGridCells[i][j] = GridType.Free;
//					if (i == partX && j == partY) {
//						newGridCells[i][j] = GridType.RobotPart;
//					}
//				} else {
//					if (i == partX && j == partY) {
//						newGridCells[i][j] = GridType.RobotPart;
//					} else {
//						newGridCells[i][j] = gridCells[i][j];
//					}
//
//				}
//			}
//
//		}
//		switch (Operator.getPartDirection()) {
//
//		case UP: {
//			for (Part p : newParts) {
//				if (p.getLocation().x == partX - 1
//						&& p.getLocation().y == partY) {
//					newParts.get(Operator.getPartIndex()).setUp(p);
//					p.setDown(newParts.get(Operator.getPartIndex()));
//
//				}
//			}
//		}
//			break;
//		case DOWN:
//			for (Part p : newParts) {
//				if (p.getLocation().x == partX + 1
//						&& p.getLocation().y == partY) {
//					newParts.get(Operator.getPartIndex()).setDown(p);
//					p.setUp(newParts.get(Operator.getPartIndex()));
//				}
//			}
//			break;
//		case LEFT:
//			for (Part p : newParts) {
//				if (p.getLocation().x == partX
//						&& p.getLocation().y == partY - 1) {
//					newParts.get(Operator.getPartIndex()).setLeft(p);
//					p.setRight(newParts.get(Operator.getPartIndex()));
//				}
//			}
//			break;
//		case RIGHT:
//			for (Part p : newParts) {
//				if (p.getLocation().x == partX
//						&& p.getLocation().y == partY + 1) {
//					newParts.get(Operator.getPartIndex()).setRight(p);
//					p.setLeft(newParts.get(Operator.getPartIndex()));
//				}
//			}
//			break;
//		}
//
//		fixed.Parts = newParts;
//		fixed.gridCells = newGridCells;
//		fixed.Obstacles = newObstacles;
//		fixed.length = length;
//		fixed.width = width;
//		return fixed;
//	}

	
	// This method is used to clone and apply changes to a state.
	public Grid applyAndClone(ArrayList<Part> AdjacentParts, WampOperator Operator,
			int partX, int partY) {
		// new Grid for cloning
		Grid fixed = new Grid(false);
		// Cloning the obstacles not needed -- reference may cause issue take
		// care !!
		@SuppressWarnings("unchecked")
		ArrayList<Point> newObstacles = (ArrayList<Point>) Obstacles.clone();
		// Fixing the new part point
		ArrayList<Part> newParts = cloneParts();
		ArrayList<Part> newAdjacent = cloneParts(newParts, AdjacentParts);
		// System.out.println("!@#$ "+partX+" "+partY);
		for (Part np : newParts) {
			for (Part ap : newAdjacent) {
				if (np == ap) {
					np.getLocation().x += partX;
					np.getLocation().y += partY;
				}

			}
		}
		// newParts.get(Operator.getPartIndex()).getLocation().x = partX;
		// newParts.get(Operator.getPartIndex()).getLocation().y = partY;

		// Fixing the grid Types
		GridType[][] newGridCells = new GridType[length][width];

		for (GridType[] row : newGridCells) {
			Arrays.fill(row, GridType.Free);
		}

		for (Point obst : Obstacles) {
			newGridCells[(int) obst.getX()][(int) obst.getY()] = GridType.Obstacle;
		}

		for (Part particular : newParts) {

			newGridCells[(int) particular.getLocation().getX()][(int) particular
					.getLocation().getY()] = GridType.RobotPart;
		}

		//checking for part collisions
		for (Part AP : newParts) { // <-------------

			for (Part p : newParts) {
				if (p.getLocation().x == AP.getLocation().getX() - 1
						&& p.getLocation().y == AP.getLocation().getY()) {
					// System.out.println(">>>>here");
					AP.setUp(p);
					p.setDown(AP);
				}
				if (p.getLocation().x == AP.getLocation().getX() + 1
						&& p.getLocation().y == AP.getLocation().getY()) {
					// System.out.println(">>>>here");
					AP.setDown(p);
					p.setUp(AP);
				}
				if (p.getLocation().x == AP.getLocation().getX()
						&& p.getLocation().y == AP.getLocation().getY() - 1) {
					// System.out.println(">>>>here");
					AP.setLeft(p);
					p.setRight(AP);
				}
				if (p.getLocation().x == AP.getLocation().x
						&& p.getLocation().y == AP.getLocation().y + 1) {
					// System.out.println(">>>>here");
					AP.setRight(p);
					p.setLeft(AP);
				}

			}

		}

		// finish cloning
		fixed.Parts = newParts;
		fixed.gridCells = newGridCells;
		fixed.Obstacles = newObstacles;
		fixed.length = length;
		fixed.width = width;
		return fixed;
	}

	// this method for cloning the parts from the parts list
	public ArrayList<Part> cloneParts() {
		ArrayList<Part> newParts = new ArrayList<Part>();
		for (Part p : Parts) {
			// System.out.println(">"+p.getRight());
			newParts.add(new Part(new Point(p.getLocation().x, p
					.getLocation().y), p.getUp(), p.getDown(), p.getLeft(), p
					.getRight()));
			// System.out.println(">"+newP.getRight());
		}
		return newParts;
	}

	// this method is used to make the parts in the adjacent list are
	//the same reference like the one in the cloned parts list
	public ArrayList<Part> cloneParts(ArrayList<Part> newParts,
			ArrayList<Part> adjParts) {
		ArrayList<Part> out = new ArrayList<Part>();
		for (Part np : newParts) {
			for (Part ap : adjParts) {
				if (np.CompareParts(ap)) {
					out.add(np);
				}
			}
		}
		return out;
	}

	// a to string method
	public String toString() {
		String s = "";
		for (int i = 0; i < gridCells.length; i++) {
			s += (gridCells[i][0]).toString().substring(0, 4);
			for (int j = 1; j < gridCells[i].length; j++) {
				s += "\t" + (gridCells[i][j].toString().substring(0, 4));
			}
			s += "\n";
		}
		return s;
	}

	// this recursive method is used to return all the parts in the same bulk of parts
	public ArrayList<Part> GetBulksRec(Part p, ArrayList<Part> result) {
		result.add(p);
		if (p.getUp() != null) {
			boolean Found = true;
			for (Part AvailableTestPart : result) {
				if (p.getUp().CompareParts(AvailableTestPart)) {
					Found = false;
					break;
				}

			}
			if (Found) {
				GetBulksRec(p.getUp(), result); // mara wa7da bas
			}
		}
		if (p.getDown() != null) {
			boolean Found = true;
			for (Part AvailableTestPart : result) {
				if (p.getDown().CompareParts(AvailableTestPart)) {
					Found = false;
					break;
				}

			}
			if (Found) {
				GetBulksRec(p.getDown(), result);
			}
		}
		if (p.getLeft() != null) {
			boolean Found = true;
			for (Part AvailableTestPart : result) {
				if (p.getLeft().CompareParts(AvailableTestPart)) {
					Found = false;
					break;
				}

			}
			if (Found) {
				GetBulksRec(p.getLeft(), result);
			}
		}

		if (p.getRight() != null) {
			boolean Found = true;
			for (Part AvailableTestPart : result) {
				if (p.getRight().CompareParts(AvailableTestPart)) {
					Found = false;
					break;

				}
			}
			if (Found) {
				GetBulksRec(p.getRight(), result);
			}
		}

		return result;
	}

}
