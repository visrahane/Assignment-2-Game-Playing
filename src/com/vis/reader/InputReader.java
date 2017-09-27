/**
 *
 */
package com.vis.reader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Vis
 *
 */
public class InputReader {

	private String fileName;

	public InputReader(String fileName) {
		this.fileName=fileName;
	}

	public List<String> readFile() {
		FileReader fr = null;
		try {
			fr = new FileReader(fileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		Scanner sc = new Scanner(fr);
		List<String> lines = new ArrayList<>();
		while (sc.hasNext()) {
			lines.add(sc.nextLine());
		}
		return lines;
	}

}
