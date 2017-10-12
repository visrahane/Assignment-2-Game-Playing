/**
 *
 */
package com.vis.test;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Vis
 *
 */
public class TestRandom {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Queue<Integer> pr = new PriorityQueue<>();
		pr.add(4);
		pr.add(6);
		pr.add(7);
		pr.add(5);

		System.out.println(pr.remove());
		System.out.println(pr.toString());
	}

}
