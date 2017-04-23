package com.unicel.util;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class CreateColorChooserDialog {


	private static void createAndShowGUI() {

		// Create and set up the window.
		final JFrame frame = new JFrame("Centered");

		// Display the window.
		frame.setSize(200, 200);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// set flow layout for the frame
		frame.getContentPane().setLayout(new FlowLayout());

		JButton button = new JButton("Choose file/directory");

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				createFileChooser(frame);
			}
		});

		frame.getContentPane().add(button);

	}

	private static void createFileChooser(final JFrame frame) {

		String filename = File.separator+"tmp";
		JFileChooser fileChooser = new JFileChooser(new File(filename));

		// pop up an "Open File" file chooser dialog
		fileChooser.showOpenDialog(frame);

		System.out.println("File to open: " + fileChooser.getSelectedFile());

		// pop up an "Save File" file chooser dialog
		fileChooser.showSaveDialog(frame);

		System.out.println("File to save: " + fileChooser.getSelectedFile());

	}

	public static void main(String[] args) {

  //Schedule a job for the event-dispatching thread:

  //creating and showing this application's GUI.

  javax.swing.SwingUtilities.invokeLater(new Runnable() {

public void run() {

    createAndShowGUI(); 

}

  });
    }

}