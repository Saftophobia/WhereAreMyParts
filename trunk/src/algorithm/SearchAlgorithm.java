package algorithm;

import java.util.ArrayList;

import util.SearchProblem;
import util.SearchTreeNode;

public class SearchAlgorithm {

	ArrayList<SearchTreeNode> nodes;

	public SearchTreeNode search(SearchProblem problem, int strategy){
		nodes = new ArrayList<SearchTreeNode>();
		nodes.add(new SearchTreeNode(problem.getInitialState(),null,null,0,0));
		while(!nodes.isEmpty()){
			SearchTreeNode node = nodes.get(0);
			if(problem.goalTest(node.getState())){
				return node;
			}else{
				switch(strategy){
				case 0:BFS(node);break;
				case 1:DFS(node);break;
				case 2:IDS(node);break;
				case 3:GRS0(node);break;
				case 4:GRS1(node);break;
				case 5:AS0(node);break;
				case 6:AS1(node);break;	
				}
			}
		}
		return null;
	}

	public void BFS(SearchTreeNode node) {

	}

	public void DFS(SearchTreeNode node) {

	}

	public void IDS(SearchTreeNode node) {

	}

	public void GRS0(SearchTreeNode node) {

	}

	public void GRS1(SearchTreeNode node) {

	}

	public void AS0(SearchTreeNode node) {

	}

	public void AS1(SearchTreeNode node) {

	}

}
