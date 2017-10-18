/**
 *
 */
package com.vis.util;

import java.util.ArrayList;
import java.util.List;

import com.vis.constants.IOConstants;
import com.vis.models.InputData;
import com.vis.reader.OutputWriter;

/**
 * @author Vis
 *
 */
public class CalibrationUtil {

	public static void main(String[] args) {
		List<String> outputList = calibrateDepthToTime();
		OutputWriter outputWriter = new OutputWriter(IOConstants.CALIBRATION_FILENAME);
		outputWriter.writeFile(outputList);
	}

	public static List<String> calibrateDepthToTime() {
		List<String> outputList = new ArrayList<>();
		for (int depth = 1; depth < 10; depth++) {
			long array[] = new long[2];
			for (int i = 0; i < 2; i++) {
				char[][] board = new char[][] {
					{'*','*','*','5','2','4','*'},
					{'*','2','2','1','7','1','*'},
					{'5','5','6','7','6','2','5'},
					{'0','4','6','7','0','7','6'},
					{'0','6','6','7','5','4','1'},
					{'6','2','4','4','7','3','7'},
					{'5','1','5','2','1','6','0'}
				};
				InputData inputData = createInput(board, 7, 8, 247.276f);
				long curretTime = System.currentTimeMillis();
				com.vis.models.VNode vNode = MinimaxUtil.runAlphaBetaSearch(new com.vis.models.Node(inputData.getBoard()),
						depth);
				// System.out.println("vNodeValue-" +
				// vNode.getChildNode().getValue());
				array[i] = System.currentTimeMillis() - curretTime + IOConstants.IO_TIME;

			}
			System.out.println("depth:" + depth + " time: " + (float) (array[0] + array[1]) / 2000);
			outputList.add(depth + IOConstants.WORD_SPLITTER + (float) (array[0] + array[1]) / 2000);
		}
		return outputList;
	}

	private static InputData createInput(char[][] board, int gridLength, int noOfFruitTypes, float timeInSeconds) {
		InputData inputData = new InputData();
		inputData.setBoard(board);
		inputData.setGridLength(gridLength);
		inputData.setNoOfFruitTypes(noOfFruitTypes);
		inputData.setTimeInSeconds(timeInSeconds);
		return inputData;
	}
}
