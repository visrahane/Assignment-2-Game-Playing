/**
 *
 */
package com.vis.util;

/**
 * @author Vis
 *
 */
public class AlgorithmUtil {

	public static void copyIntoTempGrid(char[][] tempBoard, char[][] cs) {
		for (int i = 0; i < tempBoard.length; i++) {
			for (int j = 0; j < tempBoard.length; j++) {
				tempBoard[i][j] = cs[i][j];
			}
		}

	}

}
