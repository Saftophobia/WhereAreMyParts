package wamp;

import java.util.ArrayList;

import util.Grid;
import util.Grid.GridType;
import util.Operator;
import util.SearchProblem;
import util.State;

public class WampSearchProblem extends SearchProblem {

	public WampSearchProblem(Operator[] operators, ArrayList<State> stateSpace,
			State initialState) {
		super(operators, stateSpace, initialState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean goalTest(State state) {

		return ((WampState) state).getNumberOfConnectedParts() == ((WampState) state)
				.getGrid().getParts().size();
	}

	@Override
	public double pathCost(Operator... actions) {
		// TODO Auto-generated method stub
		return 0;
	}

	// This is the function that generate the next state from the current state

	@Override
	public State transferFunction(State input, Operator operator) {
		WampState currentState = (WampState) input;
		WampOperator currentOperator = (WampOperator) operator;
		Grid currentGrid = currentState.getGrid();
		int partX = currentGrid.getParts().get(currentOperator.getPartIndex()).x;
		int partY = currentGrid.getParts().get(currentOperator.getPartIndex()).y;
		switch (currentOperator.getPartDirection()) {
		case UP: {
			if (partX == 0) {
				return null;
			}
			double costCalucalted = currentState.getCost();
			for (partX = currentGrid.getParts().get(
					currentOperator.getPartIndex()).x; partX > 1; partX--) {

				if (currentGrid.getGridCells()[partX - 1][partY] == GridType.Obstacle) {
					// Apply changes on stop
					return new WampState(currentState.getGrid().fix(
							currentOperator, partX, partY),
							currentState.getNumberOfConnectedParts(),
							costCalucalted + 1);
				} else {
					if (currentGrid.getGridCells()[partX - 1][partY] == GridType.RobotPart) {
						// Apply changes on stop
						return new WampState(currentState.getGrid().fix(
								currentOperator, partX, partY),
								currentState.getNumberOfConnectedParts() + 1,
								costCalucalted + 1);
					} else {
						// will be changed
						costCalucalted += 1;

					}
				}
			}
			return null;

		}
		case DOWN: {

			if (partX == currentGrid.getLength() - 1) {
				return null;
			}
			double costCalucalted = currentState.getCost();
			for (partX = currentGrid.getParts().get(
					currentOperator.getPartIndex()).x; partX < currentGrid
					.getLength() - 1; partX++) {

				if (currentGrid.getGridCells()[partX + 1][partY] == GridType.Obstacle) {
					// Apply changes on stop
					return new WampState(currentState.getGrid().fix(
							currentOperator, partX, partY),
							currentState.getNumberOfConnectedParts(),
							costCalucalted + 1);
				} else {
					if (currentGrid.getGridCells()[partX + 1][partY] == GridType.RobotPart) {
						// Apply changes on stop
						return new WampState(currentState.getGrid().fix(
								currentOperator, partX, partY),
								currentState.getNumberOfConnectedParts() + 1,
								costCalucalted + 1);
					} else {
						// will be changed
						costCalucalted += 1;

					}
				}
			}
			return null;

		}
		case LEFT: {
			if (partY == 0) {
				return null;
			}
			double costCalucalted = currentState.getCost();
			for (partY = currentGrid.getParts().get(
					currentOperator.getPartIndex()).y; partY > 1; partY--) {

				if (currentGrid.getGridCells()[partX][partY - 1] == GridType.Obstacle) {
					// Apply changes on stop
					return new WampState(currentState.getGrid().fix(
							currentOperator, partX, partY),
							currentState.getNumberOfConnectedParts(),
							costCalucalted + 1);
				} else {
					if (currentGrid.getGridCells()[partX][partY - 1] == GridType.RobotPart) {
						// Apply changes on stop
						return new WampState(currentState.getGrid().fix(
								currentOperator, partX, partY),
								currentState.getNumberOfConnectedParts() + 1,
								costCalucalted + 1);
					} else {
						// will be changed
						costCalucalted += 1;

					}
				}
			}
			return null;
		}
		case RIGHT: {
			if (partY == currentGrid.getWidth() - 1) {
				return null;
			}
			double costCalucalted = currentState.getCost();
			for (partY = currentGrid.getParts().get(
					currentOperator.getPartIndex()).y; partY < currentGrid
					.getWidth() - 1; partY++) {

				if (currentGrid.getGridCells()[partX][partY + 1] == GridType.Obstacle) {
					// Apply changes on stop
					return new WampState(currentState.getGrid().fix(
							currentOperator, partX, partY),
							currentState.getNumberOfConnectedParts(),
							costCalucalted + 1);
				} else {
					if (currentGrid.getGridCells()[partX][partY + 1] == GridType.RobotPart) {
						// Apply changes on stop
						return new WampState(currentState.getGrid().fix(
								currentOperator, partX, partY),
								currentState.getNumberOfConnectedParts() + 1,
								costCalucalted + 1);
					} else {
						// will be changed
						costCalucalted += 1;

					}
				}
			}
			return null;
		}

		}

		return null;
	}
}
