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

	private String name;

	private int value;

	private int depth;

	private List<Node> childrenList;

	Node() {
		childrenList = new ArrayList<>();
	}

	public Node(int value, String name) {
		this.setName(name);
		this.value = value;
		childrenList = new ArrayList<>();
	}

	public Node(String name) {
		childrenList = new ArrayList<>();
		this.name = name;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}