package com.enere.simplePackage;

import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class TextPanel extends JPanel {
	private JTextArea textArea;
	
	public TextPanel() {
		textArea = new JTextArea();
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
	}
	
	public void appendText(String text) {
		textArea.append(text);
	}
}
