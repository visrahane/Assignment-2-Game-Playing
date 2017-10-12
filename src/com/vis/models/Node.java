/**
 *
 */
package com.vis.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Vis
 *
 */
public class Node {
	private int value;

	private char[][] board;

	private int rowNo;

	private int colNo;

	private List<Node> childrenList;

	public Node(char[][] board) {
		this.board = board;
		childrenList = new ArrayList<>();
	}

	public Node(int value) {
		this.value = value;
		childrenList = new ArrayList<>();
	}

	public Node() {
		childrenList = new ArrayList<>();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Node [value=").append(value).append(", board=").append(Arrays.toString(board))
				.append(", rowNo=").append(rowNo).append(", colNo=").append(colNo).append(", childrenList=")
				.append(childrenList).append("]");
		return builder.toString();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<Node> getChildrenList() {
		return childrenList;
	}

	public void addToChildrenList(Node child) {
		childrenList.add(child);
	}

	public char[][] getBoard() {
		return board;
	}

	public void setBoard(char[][] board) {
		this.board = board;
	}

	public int getColNo() {
		return colNo;
	}

	public void setColNo(int colNo) {
		this.colNo = colNo;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}
}