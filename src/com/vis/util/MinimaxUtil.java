/**
 *
 */
package com.vis.util;

import java.util.PriorityQueue;
import java.util.Queue;

import com.vis.constants.AlgorithmConstants;
import com.vis.constants.BoardConstants;
import com.vis.models.MaxMove;

/**
 * @author Vis
 *
 */
public class MinimaxUtil {

	private static final int MAX_HEAP_SIZE = 10;
	private static final int DEVIATION = 3;

	public static com.vis.models.VNode runAlphaBetaSearch(com.vis.models.Node root, int depth) {
		return maxValue(root, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);
	}

	private static com.vis.models.VNode maxValue(com.vis.models.Node currentState, int alpha, int beta, int depth) {
		com.vis.models.VNode vnode = null;
		createChildStates(currentState, AlgorithmConstants.MAX);
		if (shouldTerminate(currentState, depth))// terminal
		{
			int value = getValueFromUtility(currentState);
			vnode = prepareVNode(currentState, 0, value);
			return vnode;
		}
		int v = Integer.MIN_VALUE;
		for(int i=0;i<currentState.getChildrenList().size();i++)//each action a
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

	private static com.vis.models.VNode prepareVNode(com.vis.models.Node currentState, int index, int value) {
		com.vis.models.VNode vnode = new com.vis.models.VNode();
		com.vis.models.Node childNode = null;
		if (!currentState.getChildrenList().isEmpty()) {
			childNode = currentState.getChildrenList().get(index);
		}
		vnode.setChildNode(childNode);
		vnode.setValue(value);
		return vnode;
	}

	private static com.vis.models.VNode minValue(com.vis.models.Node currentState, int alpha, int beta, int depth) {
		com.vis.models.VNode vnode = null;
		createChildStates(currentState, AlgorithmConstants.MIN);
		if (shouldTerminate(currentState, depth))// terminal
		{
			int value = getValueFromUtility(currentState);
			vnode = prepareVNode(currentState, 0, value);
			return vnode;
		}
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
		return vnode;
	}

	private static void createChildStates(com.vis.models.Node currentState, String moveName) {
		Queue<MaxMove> moveHeap;
		moveHeap = getMax3Move(currentState.getBoard());
		// create children for these max moves
		int i = 0; // atleast 3
		for (MaxMove maxMove, topMove = null; !moveHeap.isEmpty(); i++) {
			if(topMove==null){
				topMove=maxMove = moveHeap.remove();
			}else{
				maxMove = moveHeap.remove();
				if (i > 2 && topMove.getValue() - maxMove.getValue() > DEVIATION) {
					break;
				}
			}

			com.vis.models.Node childNode = createChildNode(currentState, maxMove, moveName);
			currentState.addToChildrenList(childNode);
		}

	}

	private static com.vis.models.Node createChildNode(com.vis.models.Node currentState, MaxMove maxMove,String moveName) {
		com.vis.models.Node childNode = new com.vis.models.Node();
		char board[][] = makeAMove(currentState.getBoard(), maxMove);
		childNode.setBoard(board);
		childNode.setRowNo(maxMove.getI());
		childNode.setColNo(maxMove.getJ());
		if (AlgorithmConstants.MIN.equals(moveName)) {
			childNode.setValue(-(int) Math.pow(maxMove.getValue(), 2) + currentState.getValue());
		} else {
			childNode.setValue((int) Math.pow(maxMove.getValue(), 2) + currentState.getValue());
		}
		return childNode;
	}

	private static Queue<MaxMove> getMin3Move(char[][] board) {
		Queue<MaxMove> maxMoveHeap = new PriorityQueue<>((x, y) -> x.getValue() - y.getValue());
		char visitedMatrix[][] = new char[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0, value = 1; j < board.length; j++) {
				if (visitedMatrix[i][j] != BoardConstants.VISITED.getSymbol()
						&& board[i][j] != BoardConstants.BLANK.getSymbol()) {
					value = 1;
					value = runDFS(board, visitedMatrix, i, j, value);
					maxMoveHeap.add(new MaxMove(value, i, j));
					if (maxMoveHeap.size() == MAX_HEAP_SIZE + 1) {
						maxMoveHeap.remove();
					}

				}
			}
		}
		return maxMoveHeap;
	}

	private static char[][] makeAMove(char[][] board, MaxMove maxMove) {
		char tempBoard[][] = new char[board.length][board[0].length];
		AlgorithmUtil.copyIntoTempGrid(tempBoard, board);
		char[][] visitedMatrix = new char[board.length][board.length];
		runDFS(tempBoard, visitedMatrix, maxMove.getI(), maxMove.getJ(), 0);
		board = (prepareBoard(tempBoard, visitedMatrix, maxMove.getValue()));
		applyGravity(tempBoard);

		return tempBoard;
	}

	private static char[][] prepareBoard(char[][] board, char[][] visitedMatrix, int totalCount) {
		for (int i = 0, count = 0; i < visitedMatrix.length; i++) {
			for (int j = 0; j < visitedMatrix.length && count <= totalCount; j++) {
				if (visitedMatrix[i][j] == BoardConstants.VISITED.getSymbol()) {
					board[i][j] = BoardConstants.BLANK.getSymbol();
					count++;
				}
			}
		}
		return board;
	}

	public static void applyGravity(char[][] board) {
		for (int j = 0; j < board.length; j++) {
			for (int i = board.length - 1; i > -1; i--) {
				if (board[i][j] == BoardConstants.BLANK.getSymbol()) {
					int k = i, l = i - 1;
					for (; l > -1; l--) {// find next fruit
						if (board[l][j] != BoardConstants.BLANK.getSymbol()) {
							break;
						}
					}
					// swap
					for (; l > -1 && board[l][j] != BoardConstants.BLANK.getSymbol()
							&& board[k][j] == BoardConstants.BLANK.getSymbol(); l--, k--) {
						char temp = board[l][j];
						board[l][j] = board[k][j];
						board[k][j] = temp;

					}
					// i = k + 1;
				}
			}
		}

	}

	private static com.vis.models.Node getNextState(com.vis.models.Node currentState, int index) {
		return currentState.getChildrenList().get(index);
	}

	private static Queue<MaxMove> getMax3Move(char[][] board) {
		Queue<MaxMove> maxMoveHeap = new PriorityQueue<>((x, y) -> x.getValue() - y.getValue());
		Queue<MaxMove> maxHeap = new PriorityQueue<>((x, y) -> y.getValue() - x.getValue());
		char visitedMatrix[][] = new char[board.length][board.length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0, value = 1; j < board.length; j++) {
				if (visitedMatrix[i][j] != BoardConstants.VISITED.getSymbol()
						&& board[i][j] != BoardConstants.BLANK.getSymbol()) {
					value = 1;
					value = runDFS(board, visitedMatrix, i, j, value);
					maxMoveHeap.add(new MaxMove(value, i, j));
					if (maxMoveHeap.size() == MAX_HEAP_SIZE + 1) {
						maxMoveHeap.remove();
					}

				}
			}
		}
		maxHeap.addAll(maxMoveHeap);
		return maxHeap;
	}

	private static int runDFS(char[][] tempBoard, char visitedMatrix[][], int i, int j, int value) {
		if (i + 1 < tempBoard.length && tempBoard[i + 1][j] == tempBoard[i][j]
				&& visitedMatrix[i + 1][j] != BoardConstants.VISITED.getSymbol()) {
			visitedMatrix[i][j] = BoardConstants.VISITED.getSymbol();
			value = runDFS(tempBoard, visitedMatrix, i + 1, j, ++value);
		}

		if (j + 1 < tempBoard.length && tempBoard[i][j + 1] == tempBoard[i][j]
				&& visitedMatrix[i][j + 1] != BoardConstants.VISITED.getSymbol()) {
			visitedMatrix[i][j] = BoardConstants.VISITED.getSymbol();
			value = runDFS(tempBoard, visitedMatrix, i, j + 1, ++value);
		}
		if (i > 0 && tempBoard[i - 1][j] == tempBoard[i][j]
				&& visitedMatrix[i - 1][j] != BoardConstants.VISITED.getSymbol()) {
			visitedMatrix[i][j] = BoardConstants.VISITED.getSymbol();
			value = runDFS(tempBoard, visitedMatrix, i - 1, j, ++value);
		}
		if (j > 0 && tempBoard[i][j - 1] == tempBoard[i][j]
				&& visitedMatrix[i][j - 1] != BoardConstants.VISITED.getSymbol()) {
			visitedMatrix[i][j] = BoardConstants.VISITED.getSymbol();
			value = runDFS(tempBoard, visitedMatrix, i, j - 1, ++value);
		}
		visitedMatrix[i][j] = BoardConstants.VISITED.getSymbol();
		return value;
	}

	private static int getValueFromUtility(com.vis.models.Node currentState) {
		return currentState.getValue();
	}

	private static boolean shouldTerminate(com.vis.models.Node currentState, int depth) {
		if (currentState.getChildrenList().isEmpty() || depth == 0)
			return true;
		return false;
	}

}
