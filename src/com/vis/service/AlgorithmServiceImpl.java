/**
 *
 */
package com.vis.service;

import com.vis.constants.BoardConstants;
import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.util.AlgorithmUtil;

/**
 * @author Vis
 *
 */
public class AlgorithmServiceImpl implements AlgorithmService {

	@Override
	public OutputData runAlgorithm(InputData inputData) {
		OutputData outputData = new OutputData();
		/*outputData.setBoard(inputData.getBoard());
		outputData.setColumnNo('B');
		outputData.setRowNo(1);*/
		char tempBoard[][] = new char[inputData.getGridLength()][inputData.getGridLength()];
		AlgorithmUtil.copyIntoTempGrid(tempBoard, inputData.getBoard());
		int maxMoveArray[] = getMaxMove(tempBoard);
		makeAMove(inputData.getBoard(), maxMoveArray, outputData);
		applyGravity(outputData.getBoard());
		prepareOutput(outputData, maxMoveArray);
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

	private void prepareOutput(OutputData outputData, int[] maxMoveArray) {
		outputData.setRowNo(maxMoveArray[1]);
		outputData.setColumnNo(maxMoveArray[2]);
	}

	private void makeAMove(char[][] board, int[] maxMoveArray, OutputData outputData) {
		char[][] visitedMatrix = new char[board.length][board.length];
		runDFS(board, visitedMatrix, maxMoveArray[1], maxMoveArray[2], 0);
		outputData.setBoard(prepareBoard(board, visitedMatrix, maxMoveArray[0]));
	}

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

	private int[] getMaxMove(char[][] tempBoard) {
		int maxMoveArray[]=new int[3];
		maxMoveArray[0] = Integer.MIN_VALUE;
		char visitedMatrix[][] = new char[tempBoard.length][tempBoard.length];
		for (int i = 0; i < tempBoard.length; i++) {
			for (int j = 0, value = 1; j < tempBoard.length; j++) {
				if (visitedMatrix[i][j] != BoardConstants.VISITED.getSymbol()
						&& tempBoard[i][j] != BoardConstants.BLANK.getSymbol()) {
					value = 1;
					value = runDFS(tempBoard, visitedMatrix, i, j, value);
					if (value > maxMoveArray[0]) {
						maxMoveArray[0] = value;
						maxMoveArray[1] = i;
						maxMoveArray[2] = j;
					}
				}
			}
		}
		return maxMoveArray;
	}

	private int runDFS(char[][] tempBoard, char visitedMatrix[][], int i, int j, int value) {
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

}
