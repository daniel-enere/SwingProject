package com.enere.simplePackage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4703116364184859655L;
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	
	public MainFrame() {
		super("Dan Gui");
		
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		toolbar = new Toolbar();
		formPanel = new FormPanel();
		
		
		toolbar.setStringListener(new StringListener() {

			@Override
			public void textEmitted(String text) {
				textPanel.appendText("Waddup \n");
			}
		});
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurrence (FormEvent event) {
				String firstName = event.getFirstName();
				String lastName = event.getLastName();
				
				textPanel.appendText(lastName + ", " + firstName + "\n");
			}
		});
		
		add(toolbar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		add(formPanel, BorderLayout.WEST);
		
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
