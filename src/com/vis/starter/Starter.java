/**
 *
 */
package com.vis.starter;

import java.util.ArrayList;
import java.util.List;

import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.reader.InputReader;
import com.vis.reader.OutputWriter;
import com.vis.service.AlgorithmService;
import com.vis.service.AlgorithmServiceImpl;
import com.vis.util.DataParserUtil;

/**
 * @author Vis
 *
 */
public class Starter {

	public static void main(String[] args) {
		long curretTime = System.currentTimeMillis();
		InputData inputData = readInput();
		OutputData outputData = runAlgorithm(inputData);
		displayOutput(outputData);
		System.out.println(System.currentTimeMillis() - curretTime);
		// Arrays.deepToString(outputData.getGrid()));
	}

	private static void displayOutput(OutputData outputData) {
		List<String> outputDataList = generateOutput(outputData);
		OutputWriter outputWriter = new OutputWriter("output.txt");
		outputWriter.writeFile(outputDataList);
	}

	public static OutputData runAlgorithm(InputData inputData) {
		AlgorithmService algorithmService = new AlgorithmServiceImpl();
		OutputData outputData = algorithmService.runAlgorithm(inputData);
		return outputData;
	}

	private static List<String> generateOutput(OutputData outputData) {
		List<String> outputDataList = new ArrayList<>();
		outputDataList.add(Character.toString((char) (outputData.getColumnNo() + 65)) + (outputData.getRowNo() + 1));
		char[][] outputGrid = outputData.getBoard();
		StringBuffer strBuffer =new StringBuffer();
		for (char[] element : outputGrid) {
			for(int j=0;j<outputGrid.length;j++) {
				strBuffer.append(element[j]);
			}
			outputDataList.add(strBuffer.toString());
			strBuffer.delete(0, outputGrid.length);
		}
		return outputDataList;
	}

	private static InputData readInput() {
		InputReader inputReader = new InputReader("input.txt");
		List<String> inputDataList = inputReader.readFile();
		InputData inputData = DataParserUtil.parseInputData(inputDataList);
		return inputData;
	}

}
