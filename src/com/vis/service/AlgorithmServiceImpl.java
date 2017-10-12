/**
 *
 */
package com.vis.service;

import com.vis.constants.BoardConstants;
import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.models.VNode;
import com.vis.util.AlgorithmUtil;
import com.vis.util.MinimaxUtil;

/**
 * @author Vis
 *
 */
public class AlgorithmServiceImpl implements AlgorithmService {

	@Override
	public OutputData runAlgorithm(InputData inputData) {
		OutputData outputData = new OutputData();
		char tempBoard[][] = new char[inputData.getGridLength()][inputData.getGridLength()];
		AlgorithmUtil.copyIntoTempGrid(tempBoard, inputData.getBoard());
		int depth = 3;
		com.vis.models.VNode vNode = MinimaxUtil.runAlphaBetaSearch(new com.vis.models.Node(tempBoard), depth);
		// makeAMove(inputData.getBoard(), maxMoveHeap, outputData);
		// applyGravity(outputData.getBoard());
		prepareOutput(outputData, vNode);
		return outputData;
	}

	public void applyGravity(char[][] board) {
		for (int j = 0; j < board.length; j++) {
			for (int i = board.length-1; i > -1; i--) {
				if (board[i][j] == BoardConstants.BLANK.getSymbol()) {
					int k = i, l = i - 1;
					for(;l>-1;l--){//find next fruit
						if (board[l][j] != BoardConstants.BLANK.getSymbol()) {
							break;
						}
					}
					//swap
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

	private void prepareOutput(OutputData outputData, VNode vNode) {
		outputData.setBoard(vNode.getChildNode().getBoard());
		outputData.setRowNo(vNode.getChildNode().getRowNo());
		outputData.setColumnNo(vNode.getChildNode().getColNo());
	}

	/*private void makeAMove(char[][] board, Queue<MaxMove> maxMoveHeap, OutputData outputData) {
		char[][] visitedMatrix = new char[board.length][board.length];
		runDFS(board, visitedMatrix, maxMoveHeap[1], maxMoveHeap[2], 0);
		outputData.setBoard(prepareBoard(board, visitedMatrix, maxMoveHeap[0]));
	}
	 */
	private char[][] prepareBoard(char[][] board, char[][] visitedMatrix, int totalCount) {
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

}
