package wamp;

import java.util.ArrayList;
import java.util.Arrays;

import algorithm.SearchAlgorithm;

import wamp.GenGrid.GridType;

public class Wamp extends SearchAlgorithm {
	private int length;
	private int width;
	private ArrayList<String> Parts;
	private ArrayList<String> Obstacles;

	public enum GridType {
		Free, Obstacle, RobotPart
	}

	public GridType[][] Grid;

	public Wamp() {

	}

	public void GenGrid() {
		this.length = (int) Math.random() * 500;
		this.width = (int) Math.random() * 500;

		// Grid fills
		Grid = new GridType[length][width];
		for (GridType[] row : Grid) {
			Arrays.fill(row, GridType.Free);
		}

		// Parts randomization
		this.Parts = new ArrayList<String>();
		for (int i = 0; i < (int) Math.random() * 50; i++) {

			int partsX = (int) Math.random() * length;
			int partsY = (int) Math.random() * width;

			if (Grid[partsX][partsY].name().equals(GridType.Free)) {
				Parts.add(partsX + "_" + partsY);
				Grid[partsX][partsY] = GridType.RobotPart;
			}
		}

		// Obstacles randomization
		this.Obstacles = new ArrayList<String>();
		for (int i = 0; i < (int) Math.random() * 50; i++) {
			int partsX = (int) Math.random() * length;
			int partsY = (int) Math.random() * width;

			if (Grid[partsX][partsY].name().equals(GridType.Free)) {
				Obstacles.add(partsX + "_" + partsY);
				Grid[partsX][partsY] = GridType.Obstacle;
			}
		}
	}

}
