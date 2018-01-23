package com.enere.simplePackage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4703116364184859655L;
	private JTextArea textArea;
	private JButton btn;
	
	public MainFrame() {
		super("Dan Gui");
		
		setLayout(new BorderLayout());
		textArea = new JTextArea();
		btn = new JButton("Click Me");
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				textArea.append("Hello\n");
			}
			
		});
		add(textArea, BorderLayout.CENTER);
		add(btn, BorderLayout.SOUTH);
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
