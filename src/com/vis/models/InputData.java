/**
 *
 */
package com.vis.models;

import java.util.Arrays;

/**
 * @author Vis
 *
 */
public class InputData {

	private int gridLength;

	private char[][] board;

	private int noOfFruitTypes;

	private float timeInSeconds;

	public InputData() {
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("InputData [gridLength=").append(gridLength).append(", board=").append(Arrays.toString(board))
		.append(", noOfFruitTypes=").append(noOfFruitTypes).append(", timeInSeconds=").append(timeInSeconds)
		.append("]");
		return builder.toString();
	}

	public void setGridLength(int gridLength) {
		this.gridLength = gridLength;
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public int getNoOfFruitTypes() {
		return noOfFruitTypes;
	}

	public void setNoOfFruitTypes(int noOfFruitTypes) {
		this.noOfFruitTypes = noOfFruitTypes;
	}

	public float getTimeInSeconds() {
		return timeInSeconds;
	}

	public void setTimeInSeconds(float timeInSeconds) {
		this.timeInSeconds = timeInSeconds;
	}

	public int getGridLength() {
		return gridLength;
	}

}
