package algorithm;

import java.util.ArrayList;

import util.SearchProblem;
import util.SearchTreeNode;

public abstract class SearchAlgorithm {

	protected ArrayList<SearchTreeNode> nodes;

	
	// Generic search method that uses all the staregies.
	public SearchTreeNode search(SearchProblem problem, int strategy, boolean visualize) {
		nodes = new ArrayList<SearchTreeNode>();
		nodes.add(new SearchTreeNode(problem.getInitialState(), null, null, 0,
				0));
		while (!nodes.isEmpty()) {
			SearchTreeNode node = nodes.get(0);
			if (problem.goalTest(node.getState())) {
				return node;
			} else {
				switch (strategy) {
				case 0:
					BFS(node, problem);
					break;
				case 1:
					DFS(node, problem);
					break;
				case 2:
					IDS(node, problem);
					break;
				case 3:
					GRS0(node, problem);
					break;
				case 4:
					GRS1(node, problem);
					break;
				case 5:
					AS0(node, problem);
					break;
				case 6:
					AS1(node, problem);
					break;
				}
			}
		}
		return null;
	}

	// BFS
	public abstract void BFS(SearchTreeNode node, SearchProblem problem);
	// DFS
	public abstract void DFS(SearchTreeNode node, SearchProblem problem);
	// IDS
	public abstract void IDS(SearchTreeNode node, SearchProblem problem);
	// GRS0 - with the first heuristic
	public abstract void GRS0(SearchTreeNode node, SearchProblem problem);
	// GRS1 - with the second heuristic
	public abstract void GRS1(SearchTreeNode node, SearchProblem problem);
	// AS0 - with the first heuristic
	public abstract void AS0(SearchTreeNode node, SearchProblem problem);
	// AS1 - with the second heuristic
	public abstract void AS1(SearchTreeNode node, SearchProblem problem);

}
