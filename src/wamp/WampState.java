package wamp;

import java.util.HashMap;

import util.Grid;
import util.State;

public class WampState extends State {

	// a Grid 
	private Grid grid;
	// number of connectedParts till Now
	private int numberOfConnectedParts;
	// possible state that can be reached from this state
	private HashMap<String, WampState> nextPossibleStates;
	
	public WampState(Grid grid, int numberOfConnectedParts, double cost) {
		super();
		this.grid = grid;
		this.nextPossibleStates = new HashMap<String, WampState>();
		this.numberOfConnectedParts = numberOfConnectedParts;
		this.setCost(cost);
	}

	public boolean isIdentical(WampState state)
	{
		for(int i = 0; i < state.getGrid().getParts().size();i++)
		{
			
				if(!state.getGrid().getParts().get(i).CompareParts(this.getGrid().getParts().get(i)))
				{
					return false;
				}
			
		}
		return true;
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

	// add the next possible state
	public void addNextPossibleState(WampOperator operator, WampState s){
		nextPossibleStates.put(operator.toString(),s);
	}
	// get the next possible state
	public WampState getNextPossibleState(WampOperator operator){
		return nextPossibleStates.get(operator.toString());
	}
	
}
