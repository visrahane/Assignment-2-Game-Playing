/**
 *
 */
package com.vis.test;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.vis.models.InputData;
import com.vis.models.OutputData;
import com.vis.service.AlgorithmService;
import com.vis.service.AlgorithmServiceImpl;
import com.vis.starter.Starter;

/**
 * @author Vis
 *
 */
public class AlgorithmServiceImplTest {

	@Test
	public void testApplyGravityFor3x3_shouldApplyGravity_whenValidInput() {
		AlgorithmServiceImpl algorithmServiceImpl = new AlgorithmServiceImpl();
		char[][] board = { { '1', '*', '2' }, { '*', '*', '2' }, { '1', '2', '*' } };

		algorithmServiceImpl.applyGravity(board);
		char[][] expected = new char[][] { { '*', '*', '*' }, { '1', '*', '2' }, { '1', '2', '2' } };
		Assert.assertArrayEquals(expected, board);
		// System.out.println(Arrays.toString(board));
	}

	@Test
	public void testApplyGravityFor6x6_shouldApplyGravity_whenValidInput() {
		AlgorithmServiceImpl algorithmServiceImpl = new AlgorithmServiceImpl();
		char[][] board = { { '1', '*', '*', '1', '*', '4' }, { '*', '*', '1', '1', '*', '5' },
				{ '2', '*', '1', '1', '*', '7' }, { '*', '*', '1', '1', '*', '*' }, { '*', '4', '*', '1', '*', '*' },
				{ '3', '5', '1', '1', '*', '*' } };

		algorithmServiceImpl.applyGravity(board);
		char[][] expected = new char[][] { { '*', '*', '*', '1', '*', '*' }, { '*', '*', '*', '1', '*', '*' },
			{ '*', '*', '1', '1', '*', '*' }, { '1', '*', '1', '1', '*', '4' }, { '2', '4', '1', '1', '*', '5' },
			{ '3', '5', '1', '1', '*', '7' } };
			Assert.assertArrayEquals(expected, board);
			// System.out.println(Arrays.toString(board));
	}

	@Test
	public void testRunAlgorithm1() {
		AlgorithmService algorithmService = new AlgorithmServiceImpl();
		char[][] board = new char[][]{
			{ 0, 1 }, { 2, 1 }
		};
		InputData inputData = createInput(board, 2, 3, 123.6f);
		OutputData outputData = algorithmService.runAlgorithm(inputData);
		Assert.assertEquals(outputData.getColumnNo(), 1);
		Assert.assertEquals(outputData.getRowNo(), 0);

	}

	@Test
	public void testRunAlgorithm2() {
		AlgorithmService algorithmService = new AlgorithmServiceImpl();
		char[][] board = new char[][] { { '*', '*', '*' }, { '*', '*', '*' }, { '*', '0', '*' } };
		InputData inputData = createInput(board, 3, 1, 257.2f);
		OutputData outputData = algorithmService.runAlgorithm(inputData);
		Assert.assertEquals(outputData.getColumnNo(), 1);
		Assert.assertEquals(outputData.getRowNo(), 2);

	}

	@Test
	public void testRunAlgorithm3() {
		AlgorithmService algorithmService = new AlgorithmServiceImpl();
		char[][] board = new char[][] { { '*', '*', '*' }, { '*', '1', '0' }, { '0', '0', '0' } };
		InputData inputData = createInput(board, 3, 2, 24.345f);
		OutputData outputData = algorithmService.runAlgorithm(inputData);
		Assert.assertEquals(outputData.getColumnNo(), 2);
		Assert.assertEquals(outputData.getRowNo(), 1);

	}

	@Test
	public void testRunAlgorithm4() {
		AlgorithmService algorithmService = new AlgorithmServiceImpl();
		char[][] board = new char[][] { { '4', '4', '4' }, { '4', '4', '4' }, { '4', '4', '4' } };
		InputData inputData = createInput(board, 3, 10, 7.24f);
		OutputData outputData = algorithmService.runAlgorithm(inputData);
		Assert.assertEquals(outputData.getColumnNo(), 0);
		Assert.assertEquals(outputData.getRowNo(), 0);

	}

	@Test
	public void testGetDepthBasedOnTime_shouldReturnValidDepth() {
		AlgorithmServiceImpl algorithmServiceImpl = new AlgorithmServiceImpl();
		InputData inputData = createInput(null, 0, 5, 280);
		Starter.calibrationMap = new HashMap<>();
		for(int i=1;i<=9;i++){
			Starter.calibrationMap.put(i, (float) (0.1 * i));
		}
		Assert.assertEquals(9, algorithmServiceImpl.getDepthBasedOnTime(inputData));
		inputData.setTimeInSeconds(189);
		Assert.assertEquals(8, algorithmServiceImpl.getDepthBasedOnTime(inputData));
		inputData.setTimeInSeconds(188);
		Assert.assertEquals(7, algorithmServiceImpl.getDepthBasedOnTime(inputData));
		inputData.setTimeInSeconds(50);
		Assert.assertEquals(4, algorithmServiceImpl.getDepthBasedOnTime(inputData));
		inputData.setTimeInSeconds(2);
		Assert.assertEquals(1, algorithmServiceImpl.getDepthBasedOnTime(inputData));
	}
	private InputData createInput(char[][] board, int gridLength, int noOfFruitTypes, float timeInSeconds) {
		InputData inputData = new InputData();
		inputData.setBoard(board);
		inputData.setGridLength(gridLength);
		inputData.setNoOfFruitTypes(noOfFruitTypes);
		inputData.setTimeInSeconds(timeInSeconds);
		return inputData;
	}

}
