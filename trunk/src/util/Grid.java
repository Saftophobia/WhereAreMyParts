package util;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

import wamp.WampOperator;

public class Grid {

	public enum GridType {
		Free, Obstacle, RobotPart
	}

	private GridType[][] gridCells;
	private ArrayList<Point> Parts;
	private ArrayList<Point> Obstacles;
	private int length;
	private int width;

	public Grid(boolean generate) {
		if (false) {
			if (generate) {
				do {
					length = (int) (Math.random() * 20);
					width = (int) (Math.random() * 20);
				} while (length < 3 || width < 3);
				// grid fills
				gridCells = new GridType[length][width];
				for (GridType[] row : gridCells) {
					Arrays.fill(row, GridType.Free);
				}

				// Parts randomization
				Parts = new ArrayList<Point>();
				int bound1;
				do {
					bound1 = (int) (Math.random() * 5);
				} while (bound1 < 1);
				for (int i = 0; i < bound1; i++) {
					int partsX;
					int partsY;
					do {
						partsX = (int) (Math.random() * length);
						partsY = (int) (Math.random() * width);
					} while (!gridCells[partsX][partsY].equals(GridType.Free));
					if (gridCells[partsX][partsY].equals(GridType.Free)) {
						Parts.add(new Point(partsX, partsY));
						gridCells[partsX][partsY] = GridType.RobotPart;
					}
				}

				// Obstacles randomization
				Obstacles = new ArrayList<Point>();
				int bound2;
				do {
					bound2 = (int) (Math.random() * 5);
				} while (bound2 < 1);
				for (int i = 0; i < bound2; i++) {
					int obsX;
					int obsY;
					do {
						obsX = (int) (Math.random() * length);
						obsY = (int) (Math.random() * width);
					} while (!gridCells[obsX][obsY].equals(GridType.Free));

					if (gridCells[obsX][obsY].equals(GridType.Free)) {
						Obstacles.add(new Point(obsX, obsY));
						gridCells[obsX][obsY] = GridType.Obstacle;
					}
				}
			}
		}else{
			gridCells = new GridType[5][5];
			for (GridType[] row : gridCells) {
				Arrays.fill(row, GridType.Free);
			}
			Parts = new ArrayList<Point>();
			Parts.add(new Point(1, 1));
			gridCells[1][1] = GridType.RobotPart;
			Parts.add(new Point(1, 4));
			gridCells[1][4] = GridType.RobotPart;
			Parts.add(new Point(4, 4));
			gridCells[4][4] = GridType.RobotPart;
			
		}
	}

	public GridType[][] getGridCells() {
		return gridCells;
	}

	public void setGridCells(GridType[][] gridCells) {
		this.gridCells = gridCells;
	}

	public ArrayList<Point> getParts() {
		return Parts;
	}

	public void setParts(ArrayList<Point> parts) {
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

	@SuppressWarnings("unchecked")
	public Grid fix(WampOperator Operator, int partX, int partY) {
		Grid fixed = new Grid(false);
		// Cloning the obstacles not needed
		ArrayList<Point> newObstacles = (ArrayList<Point>) Obstacles.clone();
		// Fixing the new part point
		ArrayList<Point> newParts = (ArrayList<Point>) Parts.clone();
		newParts.get(Operator.getPartIndex()).x = partX;
		newParts.get(Operator.getPartIndex()).y = partY;
		// Fixing the grid cells
		GridType[][] newGridCells = new GridType[length][width];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < width; j++) {
				if (i == Parts.get(Operator.getPartIndex()).x
						&& j == Parts.get(Operator.getPartIndex()).y) {
					newGridCells[i][j] = GridType.Free;
				} else {
					if (i == partX && j == partY) {
						newGridCells[i][j] = GridType.RobotPart;
					} else {
						newGridCells[i][j] = gridCells[i][j];
					}

				}
			}

		}
		fixed.Parts = newParts;
		fixed.gridCells = newGridCells;
		fixed.Obstacles = newObstacles;
		fixed.length = length;
		fixed.width = width;
		return fixed;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < gridCells.length; i++) {
			s += (gridCells[i][0]);
			for (int j = 1; j < gridCells[i].length; j++) {
				s += "\t" + (gridCells[i][j].toString());
			}
			s += "\n";
		}
		return s;
	}

}
