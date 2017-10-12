/**
 *
 */
package com.vis.test;

import org.junit.Assert;
import org.junit.Test;

import com.vis.util.MinimaxUtil;

/**
 * @author Vis
 *
 */

public class MinimaxUtilTest {

	@Test
	public void testRunAlphaBetaSearch_shouldReturnValue_whenValidInput() {
		Node root = createTree();
		VNode vNode = MinimaxUtil.runAlphaBetaSearchTemplate(root, 2);
		System.out.println(vNode.getValue() + " " + vNode.getChildNode().getName());
		Assert.assertEquals(-8, vNode.getValue());
	}

	private Node createTree() {
		Node root = new Node("Root");
		Node childB = new Node(1, "B");
		Node childB1 = new Node(15, "B1");
		Node childB2 = new Node(-10, "B2");
		Node childB3 = new Node(16, "B3");
		childB.addToChildrenList(childB1);
		childB.addToChildrenList(childB2);
		childB.addToChildrenList(childB3);

		Node childC = new Node(2, "C");
		Node childC1 = new Node(12, "C1");
		Node childC2 = new Node(-8, "C2");
		Node childC3 = new Node(7, "C3");
		childC.addToChildrenList(childC1);
		childC.addToChildrenList(childC2);
		childC.addToChildrenList(childC3);

		Node childD = new Node(3, "D");
		Node childD1 = new Node(-9, "D1");
		Node childD2 = new Node(25, "D2");
		Node childD3 = new Node(5, "D3");
		childD.addToChildrenList(childD1);
		childD.addToChildrenList(childD2);
		childD.addToChildrenList(childD3);

		root.addToChildrenList(childB);
		root.addToChildrenList(childC);
		root.addToChildrenList(childD);
		return root;

	}

	@Test
	public void testRunAlphaBetaSearch_shouldReturnValue_whenDepthLimit() {
		Node root = createTree();
		int depth = 1;
		VNode vNode = MinimaxUtil.runAlphaBetaSearchTemplate(root, depth);
		System.out.println(vNode.getValue() + " " + vNode.getChildNode().getName());
		Assert.assertEquals(3, vNode.getValue());
	}
}
