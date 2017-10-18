/**
 *
 */
package com.vis.util;

import com.vis.test.Node;
import com.vis.test.VNode;

/**
 * @author Vis
 *
 */
public class MinimaxTemplateUtil {
	public static VNode runAlphaBetaSearchTemplate(Node state, int depth) {
		return maxValue(state, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
	}

	private static VNode maxValue(Node currentState, int alpha, int beta, int depth) {
		if (shouldTerminate(currentState, depth))// terminal
		{
			int value = getValueFromUtility(currentState);
			VNode vnode = prepareVNode(currentState, 0, value);
			return vnode;
		}
		int v = Integer.MIN_VALUE;
		VNode vnode = null;
		for (int i = 0; i < currentState.getChildrenList().size(); i++)// each
			// action
			// a
		{
			int maxValue = minValue(getNextState(currentState, i), alpha, beta, depth - 1).getValue();
			if (v < maxValue) {
				v = maxValue;
				vnode = prepareVNode(currentState, i, v);
			}
			if (v >= beta) {
				vnode = prepareVNode(currentState, i, v);
				return vnode;
			}
			alpha = Math.max(alpha, v);
		}
		return vnode;
	}

	private static VNode minValue(Node currentState, int alpha, int beta, int depth) {
		if (shouldTerminate(currentState, depth))// terminal
		{
			int value = getValueFromUtility(currentState);
			VNode vnode = prepareVNode(currentState, 0, value);
			return vnode;
		}
		VNode vnode = null;
		int v = Integer.MAX_VALUE;
		for(int i=0;i<currentState.getChildrenList().size();i++) //for each a in actions
		{
			int minValue = maxValue(getNextState(currentState, i), alpha, beta, depth - 1).getValue();
			if (v > minValue) {
				v = minValue;
				vnode = prepareVNode(currentState, i, v);
			}
			if (v <= alpha) {
				vnode = prepareVNode(currentState, i, v);
				return vnode;
			}
			beta = Math.min(beta, v);
		}
		//VNode vnode = prepareVNode(currentState, currentState.getChildrenList().size() - 1, v);
		return vnode;

	}

	private static VNode prepareVNode(Node currentState, int index, int value) {
		VNode vnode = new VNode();
		Node childNode = null;
		if(!currentState.getChildrenList().isEmpty()){
			childNode = currentState.getChildrenList().get(index);
		}
		vnode.setChildNode(childNode);
		vnode.setValue(value);
		return vnode;
	}

	private static boolean shouldTerminate(Node currentState, int depth) {
		System.out.println("visited values" + currentState.getValue());
		if (currentState.getChildrenList().isEmpty() || depth == 0)
			return true;
		return false;
	}

	private static Node getNextState(Node currentState, int index) {
		return currentState.getChildrenList().get(index);
	}

	private static int getValueFromUtility(Node currentState) {
		return currentState.getValue();
	}

}
