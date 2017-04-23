package com.unicel.test;

import java.io.File;

public class TestUserInput {

	public static void main(String ar[]) {
	
		System.out.println("First Argument is :" + ar[0]);
		System.out.println("Second Argument is :" + ar[1]);
		
		String fileName = ar[0];
		if (new File(fileName).exists()) {
			//continue;
		} else {
			System.out.println();
		}
	} 
	
}
