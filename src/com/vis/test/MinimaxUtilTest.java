/**
 *
 */
package com.vis.test;

import org.junit.Test;

import com.vis.util.MinimaxUtil;

/**
 * @author Vis
 *
 */

public class MinimaxUtilTest {

	@Test
	public void testRunAlphaBetaSearch_shouldReturnValue_whenValidInput() {
		Node root=createTree();
		int value = MinimaxUtil.runAlphaBetaSearch(root);
		System.out.println(value);
	}

	private Node createTree() {
		Node root = new Node();
		Node childB = new Node();
		Node childB1 = new Node(15);
		Node childB2 = new Node(10);
		Node childB3 = new Node(6);
		childB.addToChildrenList(childB1);
		childB.addToChildrenList(childB2);
		childB.addToChildrenList(childB3);

		Node childC = new Node();
		Node childC1 = new Node(12);
		Node childC2 = new Node(-8);
		Node childC3 = new Node(7);
		childC.addToChildrenList(childC1);
		childC.addToChildrenList(childC2);
		childC.addToChildrenList(childC3);

		Node childD = new Node();
		Node childD1 = new Node(5);
		Node childD2 = new Node(25);
		Node childD3 = new Node(-9);
		childD.addToChildrenList(childD1);
		childD.addToChildrenList(childD2);
		childD.addToChildrenList(childD3);

		root.addToChildrenList(childB);
		root.addToChildrenList(childC);
		root.addToChildrenList(childD);
		return root;

	}
}
