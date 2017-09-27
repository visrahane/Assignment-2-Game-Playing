/**
 *
 */
package com.vis.service;

import com.vis.models.InputData;
import com.vis.models.OutputData;

/**
 * @author Vis
 *
 */
public class AlgorithmServiceImpl implements AlgorithmService {

	@Override
	public OutputData runAlgorithm(InputData inputData) {
		OutputData outputData = new OutputData();
		outputData.setBoard(inputData.getBoard());
		outputData.setColumnNo('B');
		outputData.setRowNo(1);
		return outputData;
	}

}
