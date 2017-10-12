/**
 *
 */
package com.vis.models;

/**
 * @author Vis
 *
 */
public class MaxMove {

	private int value;

	private int i;

	private int j;

	public MaxMove(int value, int i, int j) {
		this.value=value;
		this.i = i;
		this.j = j;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MaxMove [value=").append(value).append(", i=").append(i).append(", j=").append(j).append("]");
		return builder.toString();
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
}
