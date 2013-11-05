package wamp;

import util.Operator;

public class WampOperator extends Operator {

	//part index that need to be moved
	private int partIndex;
	//part index that need to be moved
	private Direction partDirection;

	public int getPartIndex() {
		return partIndex;
	}

	public void setPartIndex(int partIndex) {
		this.partIndex = partIndex;
	}

	public Direction getPartDirection() {
		return partDirection;
	}

	public void setPartDirection(Direction partDirection) {
		this.partDirection = partDirection;
	}

	// emun for direction
	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}
	
	//constructor

	public WampOperator(int partIndex, Direction partDirection) {
		super();
		this.partIndex = partIndex;
		this.partDirection = partDirection;
	}
	//toString()
	public String toString(){
		return partIndex+" "+partDirection;
	}

}
