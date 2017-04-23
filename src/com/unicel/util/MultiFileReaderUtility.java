package com.unicel.util;

import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.unicel.model.TestArtifact;

public class MultiFileReaderUtility {
		int manager =0;	
		
	public static List<TestArtifact> ReadMultiFiles() {

		List<TestArtifact> testArtifactsLst = new ArrayList<TestArtifact>();

		int noOfFiles = 0;

		System.out.println("Enter how many files you want to enter");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		TestArtifact testArtifact = null;

		try {

			noOfFiles = Integer.parseInt(br.readLine());

			for (int i = 0; i < noOfFiles; i++) {

				testArtifact = new TestArtifact();

				testArtifact.setFileName(readFileName("Enter FileName"));

				testArtifact.setSheetName(readFileName("Enter Sheet Name"));

				testArtifactsLst.add(testArtifact);
			}

		} catch (NumberFormatException | IOException e) {

			e.printStackTrace();

		}

		return testArtifactsLst;

	}

	public static String readFileName(String displayStr) {
		System.out.println("Please " + displayStr);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String fileName = null;

		try {
			fileName = br.readLine();

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (fileName != null) {
			System.out.println("FileName Entered is : " + fileName);
		}

		if (!(new File(fileName)).exists()) {
			System.out.println("File :" + fileName + " Doesnt Exist");
		}
		return fileName;
	}

	public static String readSheetName() {

		System.out.println("Please Enter Sheet Name :");
		BufferedReader br1 = new BufferedReader(
				new InputStreamReader(System.in));
		String sheetName = null;

		try {

			sheetName = br1.readLine();
			// sheetName.split(",");

		} catch (IOException e) {
			e.printStackTrace();
		}

		if (sheetName != null) {
			System.out.println("SheetName Entered is : " + sheetName);
		}

		else // (! (new File(sheetName)).exists())
		{
			System.out.println("File :" + sheetName + " Doesnt Exist");
		}
		return sheetName;
	}

	/*public static void main(String[] args) {

		JFrame frame = new JFrame();

		JPanel panel = new JPanel();

		panel.setSize(50, 50);

		frame.setSize(400, 400);

		frame.setVisible(true);

		JFileChooser chooser = new JFileChooser();

		panel.add(chooser);
		
		frame.setLayout(new GridBagLayout());
		
		frame.add(panel);
	}
*/
}
