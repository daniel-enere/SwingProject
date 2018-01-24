package com.enere.simplePackage;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class FormPanel extends JPanel {
	private JLabel firstNameLabel;
	private JTextField firstNameField;
	private JLabel lastNameLabel;
	private JTextField lastNameField;
	private JButton submit;
	private FormListener formListener;
	
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Employee");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder ));
		
		firstNameLabel = new JLabel("First Name: ");
		firstNameField = new JTextField(10);
		lastNameLabel = new JLabel("Last Name: ");
		lastNameField = new JTextField(10);
		submit = new JButton("Submit");
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				
				FormEvent formEvent = new FormEvent(this, firstName, lastName);
				
				if (formListener != null) {
					formListener.formEventOccurrence(formEvent);
				}
			}
			
		});
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbConstraints = new GridBagConstraints();
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0.1;
		
		//first row
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 0;
		gbConstraints.fill = GridBagConstraints.NONE;
		gbConstraints.anchor = GridBagConstraints.LINE_END;
		gbConstraints.insets = new Insets(0, 0, 0, 5);
		add(firstNameLabel, gbConstraints);
		
		gbConstraints.anchor = GridBagConstraints.LINE_START;
		gbConstraints.gridx = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		add(firstNameField, gbConstraints);
		
		//Second row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0.1;
		gbConstraints.anchor = GridBagConstraints.LINE_END;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 5);
		add(lastNameLabel, gbConstraints);
		
		gbConstraints.anchor = GridBagConstraints.LINE_START;
		gbConstraints.gridx = 1;
		gbConstraints.gridy = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		add(lastNameField,gbConstraints);
		
		//Third row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 2.0;
		gbConstraints.gridx = 0;
		gbConstraints.gridy = 2;
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(submit, gbConstraints);
	}
	/**
	 * 
	 * @param formPanelEvent
	 * sends events wherever it is called
	 */
	void setFormListener(FormListener formPanelEvent) {
		this.formListener = formPanelEvent;
	}
}
