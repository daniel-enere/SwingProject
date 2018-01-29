package com.enere.simplePackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Toolbar extends JPanel implements ActionListener {
	private JButton saveToDatabaseButton;
	private JButton refreshTableButton;
	private ToolbarListener toolbarListener;
	
	public Toolbar() {
		setBorder(BorderFactory.createEtchedBorder());
		saveToDatabaseButton = new JButton("Save");
		refreshTableButton = new JButton("Refresh Table");
		
		saveToDatabaseButton.addActionListener(this);
		refreshTableButton.addActionListener(this);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		add(saveToDatabaseButton);
		add(refreshTableButton);
	}
	
	public void setToolbarListener(ToolbarListener toolbarListener) {
		this.toolbarListener = toolbarListener;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JButton clicked = (JButton) event.getSource();
		if (clicked == saveToDatabaseButton) {
			if(toolbarListener != null)
			toolbarListener.saveEvent();
		} else if (clicked == refreshTableButton) {
			if (toolbarListener != null) {
				toolbarListener.refreshEvent();
			}
		}
	}
}
