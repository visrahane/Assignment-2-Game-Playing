/**
 *
 */
package com.vis.util;

import com.vis.test.Node;

/**
 * @author Vis
 *
 */
public class MinimaxUtil {

	public static int runAlphaBetaSearch(Node state) {
		return maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE);
	}

	private static int maxValue(Node currentState, int alpha, int beta) {
		if (shouldTerminate(currentState))// terminal
		{
			return getValueFromUtility(currentState);
		}
		int v = Integer.MIN_VALUE;
		for(int i=0;i<currentState.getChildrenList().size();i++)//each action a
		{
			v = Math.max(v, minValue(getNextState(currentState,i), alpha, beta));
			if (v >= beta) {
				return v;
			}
			alpha = Math.max(alpha, v);
		}
		return v;
	}

	private static Node getNextState(Node currentState, int index) {
		return currentState.getChildrenList().get(index);
	}

	private static int minValue(Node currentState, int alpha, int beta) {
		if (shouldTerminate(currentState))// terminal
		{
			return getValueFromUtility(currentState);
		}
		int v = Integer.MAX_VALUE;
		for(int i=0;i<currentState.getChildrenList().size();i++) //for each a in actions
		{
			v = Math.min(v, maxValue(getNextState(currentState, i), alpha, beta));
			if (v <= alpha)
				return v;
			beta = Math.min(beta, v);
		}
		return v;
	}

	private static int getValueFromUtility(Node currentState) {
		return currentState.getValue();
	}

	private static boolean shouldTerminate(Node currentState) {
		if (currentState.getChildrenList().isEmpty())
			return true;
		return false;
	}

}
