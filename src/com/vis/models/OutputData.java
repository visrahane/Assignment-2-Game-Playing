/**
 *
 */
package com.vis.models;

import java.util.Arrays;

/**
 * @author vis
 *
 */
public class OutputData {

	private char board[][];

	private int columnNo;

	private int rowNo;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OutputData [board=").append(Arrays.toString(board)).append(", columnNo=").append(columnNo)
		.append(", rowNo=").append(rowNo).append("]");
		return builder.toString();
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public int getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(int maxMoveArray) {
		columnNo = maxMoveArray;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

}
