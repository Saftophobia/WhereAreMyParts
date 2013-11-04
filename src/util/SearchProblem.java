package util;

import java.util.ArrayList;

public abstract class SearchProblem {
	//operators strings for now
	private Operator[] operators;
	//state space list
	private ArrayList<State> stateSpace;
	//initial State
	private State initialState;

	
	

	// Constructor
	public SearchProblem(Operator[] operators, ArrayList<State> stateSpace,
			State initialState) {
		super();
		this.operators = operators;
		this.stateSpace = stateSpace;
		this.initialState = initialState;
	}
	
	

	public Operator[] getOperators() {
		return operators;
	}



	public void setOperators(Operator[] operators) {
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

	
	// test if goal reached
	public abstract boolean goalTest(State state);
	
	// get path cost
	public abstract double pathCost(Object... operators);
	
	public abstract State transferFunction(State input,Operator operator);



}
