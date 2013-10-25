package wamp;

import java.util.ArrayList;

import util.Grid;
import util.Operator;
import util.SearchProblem;
import util.SearchTreeNode;
import wamp.WampOperator.Direction;

import algorithm.SearchAlgorithm;

public class Wamp extends SearchAlgorithm {

	public Grid genGrid() {
		Grid grid = new Grid(true);
		return grid;
	}

	public String[] search(Grid grid, String strategy, boolean visualize) {
		WampState initialState = new WampState(grid, 0, 0);
		Operator[] operators = new Operator[grid.getParts().size() * 4];
		for (int i = 0; i < grid.getParts().size(); i++) {
			operators[i*4] = new WampOperator(i, Direction.UP);
			operators[i*4+1] = new WampOperator(i, Direction.DOWN);
			operators[i*4+2] = new WampOperator(i, Direction.LEFT);
			operators[i*4+3] = new WampOperator(i, Direction.RIGHT);
		}
		WampSearchProblem problem = new WampSearchProblem(operators, null,
				initialState);
		int strategyNumber = 0;
		if (strategy.equals("BF")) {
			strategyNumber = 0;
		} else {
			if (strategy.equals("DF")) {
				strategyNumber = 1;
			} else {
				if (strategy.equals("ID")) {
					strategyNumber = 2;
				} else {
					if (strategy.equals("GR1")) {
						strategyNumber = 3;
					} else {
						if (strategy.equals("GR2")) {
							strategyNumber = 4;
						} else {
							if (strategy.equals("AS1")) {
								strategyNumber = 5;
							} else {
								if (strategy.equals("AS2")) {
									strategyNumber = 6;
								} else {
									strategyNumber = 0;
								}
							}
						}
					}
				}
			}
		}

		search(problem, strategyNumber, visualize);
		return null;
	}

	public SearchTreeNode search(WampSearchProblem problem, int strategy,
			boolean visualize) {
		nodes = new ArrayList<SearchTreeNode>();
		nodes.add(new SearchTreeNode(problem.getInitialState(), null, null, 0,
				0));
		int count = 0;
		while (!nodes.isEmpty()) {
			SearchTreeNode node = nodes.remove(0);
			System.out.println("--------------------------------------------------");
		
			if (problem.goalTest(node.getState())) {
				System.out.println(((WampState)node.getState()).getGrid());
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
			if(count++>10){
				System.exit(0);
				}
		}
		return null;
	}

	public static void main(String[] args) {
		Wamp wamp = new Wamp();
		Grid grid = wamp.genGrid();
		System.out.println(grid);
		wamp.search(grid, "DF", true);
	}

	@Override
	public void BFS(SearchTreeNode node, SearchProblem problem) {
		WampState state = (WampState) node.getState();
		for(Operator operator : ((WampSearchProblem)problem).getOperators()){
			WampState output = (WampState) ((WampSearchProblem)problem).transferFunction2(state, operator);
			if(output !=null){
				SearchTreeNode newNode = new WampSearchTreeNode(output, node, operator, node.getDepth()+1, 0);
				System.out.println(">>>"+output.getNumberOfConnectedParts());
				nodes.add(newNode);
			}
		}
		
	}

	@Override
	public void DFS(SearchTreeNode node, SearchProblem problem) {

		WampState state = (WampState) node.getState();
		for(Operator operator : ((WampSearchProblem)problem).getOperators()){
			WampState output = (WampState) ((WampSearchProblem)problem).transferFunction2(state, operator);
			if(output !=null){
				SearchTreeNode newNode = new WampSearchTreeNode(output, node, operator, node.getDepth()+1, 0);
				System.out.println(">>>"+output.getNumberOfConnectedParts());
				nodes.add(0,newNode);
			}
			
		}
		
	}

	@Override
	public void IDS(SearchTreeNode node, SearchProblem problem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GRS0(SearchTreeNode node, SearchProblem problem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GRS1(SearchTreeNode node, SearchProblem problem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AS0(SearchTreeNode node, SearchProblem problem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void AS1(SearchTreeNode node, SearchProblem problem) {
		// TODO Auto-generated method stub
		
	}

}
