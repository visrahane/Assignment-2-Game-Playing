/**
 *
 */
package com.vis.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vis
 *
 */
public class Node {
	private int value;

	private List<Node> childrenList;

	Node() {
		childrenList = new ArrayList<>();
	}

	public Node(int value) {
		this.value = value;
		childrenList = new ArrayList<>();
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
}