package algorithm;

import java.util.ArrayList;

import util.SearchProblem;
import util.SearchTreeNode;

public class SearchAlgorithm {

	ArrayList<SearchTreeNode> nodes;

	public SearchTreeNode search(SearchProblem problem, int strategy) {
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

	public void BFS(SearchTreeNode node, SearchProblem problem) {

	}

	public void DFS(SearchTreeNode node, SearchProblem problem) {

	}

	public void IDS(SearchTreeNode node, SearchProblem problem) {

	}

	public void GRS0(SearchTreeNode node, SearchProblem problem) {

	}

	public void GRS1(SearchTreeNode node, SearchProblem problem) {

	}

	public void AS0(SearchTreeNode node, SearchProblem problem) {

	}

	public void AS1(SearchTreeNode node, SearchProblem problem) {

	}

}
