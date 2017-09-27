/**
 *
 */
package com.vis.util;

/**
 * @author Vis
 *
 */
public class AlgorithmUtil {

	public static void copyIntoTempGrid(int[][] tempGrid, int[][] grid) {
		for (int i = 0; i < tempGrid.length; i++) {
			for (int j = 0; j < tempGrid.length; j++) {
				tempGrid[i][j] = grid[i][j];
			}
		}

	}

}
