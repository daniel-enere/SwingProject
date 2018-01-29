package com.enere.simplePackage;

import javax.swing.*;

public class App {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				System.out.println("DB pass: ");
				new MainFrame();
			}
		});	
	}
}
