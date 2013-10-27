package wamp;

import java.util.ArrayList;

import util.Grid;
import util.Heuristic;
import util.Operator;
import util.Part;
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
			operators[i * 4] = new WampOperator(i, Direction.UP);
			operators[i * 4 + 1] = new WampOperator(i, Direction.DOWN);
			operators[i * 4 + 2] = new WampOperator(i, Direction.LEFT);
			operators[i * 4 + 3] = new WampOperator(i, Direction.RIGHT);
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

		SearchTreeNode node = search(problem, strategyNumber, visualize);
		if (visualize) {
			SearchTreeNode pointer = node;
			if (pointer != null) {
				while (pointer != null) {
					System.out.println(((WampState) pointer.getState())
							.getGrid());
					System.out.println(Heuristic
							.returnHeuristic(((WampState) pointer.getState())
									.getGrid().getParts()));
					pointer = pointer.getParentNode();
				}
			}
		}
		return null;
	}

	public SearchTreeNode search(WampSearchProblem problem, int strategy,
			boolean visualize) {
		nodes = new ArrayList<SearchTreeNode>();
		nodes.add(new SearchTreeNode(problem.getInitialState(), null, null, 0,
				0));
		while (!nodes.isEmpty()) {
			SearchTreeNode node = nodes.remove(0);
			System.out
					.println("--------------------------------------------------");

			if (problem.goalTest(node.getState())) {
				System.out.println(((WampState) node.getState()).getGrid());
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

	public static void main(String[] args) {
		Wamp wamp = new Wamp();
		Grid grid = wamp.genGrid();
		System.out.println(grid);

		// System.out.println(Heuristic.returnHeuristic(grid.getParts()));
		wamp.search(grid, "AS1", true);
	}

	@Override
	public void BFS(SearchTreeNode node, SearchProblem problem) {
		WampState state = (WampState) node.getState();
		ArrayList<SearchTreeNode> children = new ArrayList<SearchTreeNode>();
		for (Operator operator : ((WampSearchProblem) problem).getOperators()) {
			WampState output = (WampState) ((WampSearchProblem) problem)
					.transferFunction2(state, operator);
			if (output != null) {
				SearchTreeNode newNode = new WampSearchTreeNode(output, node,
						operator, node.getDepth() + 1, 0);
				System.out.println(">>>" + output.getNumberOfConnectedParts());
				children.add(newNode);
			}
		}
		for (int i = 0; i < children.size(); i++) {
			SearchTreeNode child1 = children.get(i);
			for (int j = 0; j < children.size(); j++) {
				SearchTreeNode child2 = children.get(j);
				if (child1 != child2) {
					boolean all = true;
					for (Part p1 : ((WampState) child1.getState()).getGrid()
							.getParts()) {
						for (Part p2 : ((WampState) child2.getState())
								.getGrid().getParts()) {
							if (!p1.CompareParts(p2)) {
								all = false;
								break;
							}
						}
						if (!all) {
							break;
						}
					}
					if (all) {
						child2.setRemovable(true);
					}
				}
			}
		}
		for (SearchTreeNode child : children) {
			if (!child.isRemovable())
				nodes.add(child);
		}

	}

	@Override
	public void DFS(SearchTreeNode node, SearchProblem problem) {

		WampState state = (WampState) node.getState();
		ArrayList<SearchTreeNode> children = new ArrayList<SearchTreeNode>();
		for (Operator operator : ((WampSearchProblem) problem).getOperators()) {
			WampState output = (WampState) ((WampSearchProblem) problem)
					.transferFunction2(state, operator);
			if (output != null) {
				SearchTreeNode newNode = new WampSearchTreeNode(output, node,
						operator, node.getDepth() + 1, 0);
				System.out.println(">>>" + output.getNumberOfConnectedParts());
				children.add(0, newNode);
			}

		}
		for (int i = 0; i < children.size(); i++) {
			SearchTreeNode child1 = children.get(i);
			for (int j = 0; j < children.size(); j++) {
				SearchTreeNode child2 = children.get(j);
				if (child1 != child2) {
					boolean all = true;
					for (Part p1 : ((WampState) child1.getState()).getGrid()
							.getParts()) {
						for (Part p2 : ((WampState) child2.getState())
								.getGrid().getParts()) {
							if (!p1.CompareParts(p2)) {
								all = false;
								break;
							}
						}
						if (!all) {
							break;
						}
					}
					if (all) {
						child2.setRemovable(true);
					}
				}
			}
		}
		for (SearchTreeNode child : children) {
			if (!child.isRemovable())
				nodes.add(0, child);
		}

	}

	@Override
	public void IDS(SearchTreeNode node, SearchProblem problem) {
		SearchTreeNode initialNode = node;
		nodes.add(node);
		int limit = 0;
		while (true) {
			SearchTreeNode extract = nodes.remove(0);
			System.out
					.println("--------------------------------------------------");
			if (problem.goalTest(extract.getState())) {
				nodes.clear();
				nodes.add(extract);
				return;
			} else {
				if (extract.getDepth() <= limit) {
					DFS(extract, problem);
				} else {
					if (nodes.isEmpty()) {
						limit++;
						nodes.add(initialNode);
						System.out
								.println("$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%$%");
					}
				}
			}
		}
	}

	@Override
	public void GRS0(SearchTreeNode node, SearchProblem problem) {
		WampState state = (WampState) node.getState();
		ArrayList<SearchTreeNode> children = new ArrayList<SearchTreeNode>();
		for (Operator operator : ((WampSearchProblem) problem).getOperators()) {
			WampState output = (WampState) ((WampSearchProblem) problem)
					.transferFunction2(state, operator);
			if (output != null) {
				SearchTreeNode newNode = new WampSearchTreeNode(output, node,
						operator, node.getDepth() + 1, 0);
				System.out.println(">>>" + output.getNumberOfConnectedParts());
				children.add(newNode);
			}
		}
		for (int i = 0; i < children.size(); i++) {
			SearchTreeNode child1 = children.get(i);
			child1.setHeuristic(Heuristic.returnHeuristic(((WampState) child1
					.getState()).getGrid().getParts()));
			for (int j = 0; j < children.size(); j++) {
				SearchTreeNode child2 = children.get(j);
				if (child1 != child2) {
					boolean all = true;
					for (Part p1 : ((WampState) child1.getState()).getGrid()
							.getParts()) {
						for (Part p2 : ((WampState) child2.getState())
								.getGrid().getParts()) {
							if (!p1.CompareParts(p2)) {
								all = false;
								break;
							}
						}
						if (!all) {
							break;
						}
					}
					if (all) {
						child2.setRemovable(true);
					}
				}
			}
		}

		for (SearchTreeNode child : children) {
			if (!child.isRemovable())
				if (nodes.size() == 0) {
					nodes.add(child);
					
				} else {
					for (int y = 0; y < nodes.size(); y++) {
						if (child.getHeuristic() > nodes.get(y).getHeuristic()) {
							nodes.add(y, child);
							break;
						}
					}
				}
		}

	}

	@Override
	public void GRS1(SearchTreeNode node, SearchProblem problem) {
		// TODO Auto-generated method stub

	}

	@Override
	public void AS0(SearchTreeNode node, SearchProblem problem) {
		WampState state = (WampState) node.getState();
		ArrayList<SearchTreeNode> children = new ArrayList<SearchTreeNode>();
		for (Operator operator : ((WampSearchProblem) problem).getOperators()) {
			WampState output = (WampState) ((WampSearchProblem) problem)
					.transferFunction2(state, operator);
			if (output != null) {
				SearchTreeNode newNode = new WampSearchTreeNode(output, node,
						operator, node.getDepth() + 1, 0);
				System.out.println(">>>" + output.getNumberOfConnectedParts());
				children.add(newNode);
			}
		}
		for (int i = 0; i < children.size(); i++) {
			SearchTreeNode child1 = children.get(i);
			child1.setHeuristic(Heuristic.returnHeuristic(((WampState) child1
					.getState()).getGrid().getParts()));
			child1.setPathCost(0.1*child1.getDepth());
			for (int j = 0; j < children.size(); j++) {
				SearchTreeNode child2 = children.get(j);
				if (child1 != child2) {
					boolean all = true;
					for (Part p1 : ((WampState) child1.getState()).getGrid()
							.getParts()) {
						for (Part p2 : ((WampState) child2.getState())
								.getGrid().getParts()) {
							if (!p1.CompareParts(p2)) {
								all = false;
								break;
							}
						}
						if (!all) {
							break;
						}
					}
					if (all) {
						child2.setRemovable(true);
					}
				}
			}
		}

		for (SearchTreeNode child : children) {
			System.out.println(child.getPathCost());
			if (!child.isRemovable())
				if (nodes.size() == 0) {
					nodes.add(child);
					
				} else {
					for (int y = 0; y < nodes.size(); y++) {
						if (child.getHeuristic() + child.getPathCost() > nodes.get(y).getHeuristic() + nodes.get(y).getPathCost()) {
							nodes.add(y, child);
							break;
						}
					}
				}
		}


	}

	@Override
	public void AS1(SearchTreeNode node, SearchProblem problem) {
		// TODO Auto-generated method stub

	}

}
