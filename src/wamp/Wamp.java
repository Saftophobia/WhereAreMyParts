package wamp;

import util.Grid;

import algorithm.SearchAlgorithm;

public class Wamp extends SearchAlgorithm {

	
	public  static Grid genGrid() {
		Grid grid = new Grid(true);
		return grid;
	}
	
	public static String [] search(Grid grid, String strategy, boolean visualize) {
		// 	do some stuff
		//	search(problem, strategy, visualize);
		return null;
	}
	
	public static void main(String[] args){
		Grid grid = genGrid();
		search(grid,"",true);
	}

}
