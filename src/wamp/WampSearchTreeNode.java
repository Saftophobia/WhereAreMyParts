package wamp;

import util.Operator;
import util.SearchTreeNode;

public class WampSearchTreeNode extends SearchTreeNode {
	private WampState state; // The state of the state space that this node
								// corresponds to

	public WampSearchTreeNode(WampState state, SearchTreeNode parentNode,
			Operator operator, int depth, int pathCost) {
		super(parentNode, operator, depth, pathCost);
		this.state = state;
	}

	public WampState getState() {
		return state;
	}

	public void setState(WampState state) {
		this.state = state;
	}

}
