package util;

import java.util.ArrayList;

public class SearchProblem {
	//operators strings for now
	private String[] operators;
	//state space list
	private ArrayList<State> stateSpace;
	//initial State
	private State initialState;
	//goal State
	private State goalState;
	
	

	// Constructor
	public SearchProblem(String[] operators, ArrayList<State> stateSpace,
			State initialState, State goalState) {
		super();
		this.operators = operators;
		this.stateSpace = stateSpace;
		this.initialState = initialState;
		this.goalState = goalState;
	}
	
	

	public String[] getOperators() {
		return operators;
	}



	public void setOperators(String[] operators) {
		this.operators = operators;
	}



	public ArrayList<State> getStateSpace() {
		return stateSpace;
	}



	public void setStateSpace(ArrayList<State> stateSpace) {
		this.stateSpace = stateSpace;
	}



	public State getInitialState() {
		return initialState;
	}



	public void setInitialState(State initialState) {
		this.initialState = initialState;
	}



	public State getGoalState() {
		return goalState;
	}



	public void setGoalState(State goalState) {
		this.goalState = goalState;
	}

	// test if goal reached
	public boolean goalTest(State state){
		return state == goalState;
	}
	
	// get path cost
	public double pathCost(String... actions) {
		return 0.0;
	}



}
