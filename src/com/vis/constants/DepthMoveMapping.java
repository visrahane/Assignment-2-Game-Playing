package com.vis.constants;
/**
 *
 */


/**
 * @author Vis
 *
 */
public interface DepthMoveMapping {
	public static int moveLimitArray[] = { 0, 0, 20, 40, 80, 100, 140, 200, 235, 270 };

	public static int getLimit(int i) {
		return moveLimitArray[i];
	}
}
