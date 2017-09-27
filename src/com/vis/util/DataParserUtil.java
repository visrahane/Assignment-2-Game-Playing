/**
 *
 */
package com.vis.util;

import java.util.List;

import com.vis.models.InputData;

/**
 * @author Vis
 *
 */
public class DataParserUtil {

	public static void main(String[] args) {

	}

	public static InputData parseInputData(List<String> inputDataList) {
		InputData inputData = new InputData();
		inputData.setGridLength(Integer.parseInt(inputDataList.get(0)));
		inputData.setNoOfFruitTypes(Integer.parseInt(inputDataList.get(1)));
		inputData.setTimeInSeconds(Float.parseFloat(inputDataList.get(2)));
		char[][] inputMatrix = populateInputMatrix(inputDataList, inputData.getGridLength());
		inputData.setBoard(inputMatrix);
		return inputData;

	}

	private static char[][] populateInputMatrix(List<String> inputDataList, int matrixLength) {
		char[][] inputMatrix = new char[matrixLength][matrixLength];
		for (int k = 3, i = 0; k < matrixLength + 3; k++, i++) {
			String line = inputDataList.get(k);
			for (int j = 0; j < matrixLength; j++) {
				inputMatrix[i][j] = line.charAt(j);
			}
		}
		return inputMatrix;
	}
}
