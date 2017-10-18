package com.vis.constants;
/**
 *
 */


/**
 * @author vis
 *
 */
public enum BoardConstants {
	BLANK('*'), VISITED('v');

	private char symbol;

	private BoardConstants(char number) {
		this.setSymbol(number);
	}

	public char getSymbol() {
		return symbol;
	}

	public void setSymbol(char number) {
		this.symbol = number;
	}
}
