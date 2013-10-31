package util;

import wamp.WampState;


/**
 * @author Saftophobia
 *
 */
public class SearchTreeNode {
	private State state; // The state of the state space that this node
							// corresponds to
	private SearchTreeNode parentNode;
	private Operator operator; // The operator applied to generate this node, syntax "part_direction"
	private int depth; // The depth of the node in the tree
	private double pathCost; // The path cost from the root
	private boolean removable;
	private double heuristic;
	
	public double getHeuristic() {
		return heuristic;
	}

	public void setHeuristic(double heuristic) {
		this.heuristic = heuristic;
	}

	public SearchTreeNode(State state, SearchTreeNode parentNode,
			Operator operator, int depth, int pathCost) {
		super();
		this.state = state;
		this.parentNode = parentNode;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
	}

	public SearchTreeNode(SearchTreeNode parentNode, Operator operator,
			int depth, int pathCost) {
		this.parentNode = parentNode;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public SearchTreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(SearchTreeNode parentNode) {
		this.parentNode = parentNode;
	}

	public Operator getOperator() {
		return operator;
	}

	public void setOperator(Operator operator) {
		this.operator = operator;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public double getPathCost() {
		return pathCost;
	}

	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}
	
	public boolean isRemovable() {
		return removable;
	}

	public void setRemovable(boolean removable) {
		this.removable = removable;
	}

	public String toString()
	{
		return (this.getPathCost()+this.getHeuristic()) + "";
		
	}

}