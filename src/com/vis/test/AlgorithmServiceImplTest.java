/**
 *
 */
package com.vis.test;

import org.junit.Assert;
import org.junit.Test;

import com.vis.service.AlgorithmServiceImpl;

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

}
