package util;


/**
 * @author Saftophobia
 *
 */
public class SearchTreeNode {
	private State state; // The state of the state space that this node
							// corresponds to
	private SearchTreeNode parentNode;
	private String operator; // The operator applied to generate this node, syntax "part_direction"
	private int depth; // The depth of the node in the tree
	private int pathCost; // The path cost from the root

	public SearchTreeNode(State state, SearchTreeNode parentNode,
			String operator, int depth, int pathCost) {
		super();
		this.state = state;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getPathCost() {
		return pathCost;
	}

	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}

}