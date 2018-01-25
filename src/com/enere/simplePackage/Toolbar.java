package com.enere.simplePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Toolbar extends JPanel implements ActionListener {
	private JButton someButton;
	private JButton anotherButton;
	private StringListener textListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		someButton = new JButton("Some Button");
		anotherButton = new JButton("Another Button");
		
		someButton.addActionListener(this);
		anotherButton.addActionListener(this);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(someButton);
		add(anotherButton);
	}
	
	public void setStringListener(StringListener toolbarListener) {
		this.textListener = toolbarListener;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (textListener != null) {
			textListener.textEmitted("Waddup");
		}
	}
}
