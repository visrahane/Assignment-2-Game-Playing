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

	private char columnNo;

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

	public char getColumnNo() {
		return columnNo;
	}

	public void setColumnNo(char columnNo) {
		this.columnNo = columnNo;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

}
