package com.enere.simplePackage;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainMenuBar extends JMenuBar {

	public MainMenuBar() {
		super();
		
		//file menu setup
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		add(fileMenu);
		
		//window menu setup
		JMenu windowMenu = new JMenu("Window");
		add(windowMenu);
	}
}
