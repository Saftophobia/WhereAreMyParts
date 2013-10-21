package wamp;

import util.Grid;
import util.State;

public class WampState extends State {

	private Grid grid;
	private int numberOfConnectedParts;

	public WampState(Grid grid, int numberOfConnectedParts, double cost) {
		super();
		this.grid = grid;
		this.numberOfConnectedParts = numberOfConnectedParts;
		this.setCost(cost);
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public int getNumberOfConnectedParts() {
		return numberOfConnectedParts;
	}

	public void setNumberOfConnectedParts(int numberOfConnectedParts) {
		this.numberOfConnectedParts = numberOfConnectedParts;
	}

}
