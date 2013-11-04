package wamp;

import util.Operator;

public class WampOperator extends Operator {

	private int partIndex;
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

	public enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	public WampOperator(int partIndex, Direction partDirection) {
		super();
		this.partIndex = partIndex;
		this.partDirection = partDirection;
	}
	
	public String toString(){
		return partIndex+" "+partDirection;
	}

}
