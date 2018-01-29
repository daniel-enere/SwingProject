package com.enere.simplePackage;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class PrefsDialog extends JDialog {
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner portSpinner;
	private SpinnerNumberModel spinnerModel;
	private JTextField userField;
	private JPasswordField passField;
	
	private PrefsListener prefsListener;
	
	public PrefsDialog(JFrame parent) {
		super(parent, "Preferences", false);
		
		okButton = new JButton("OK");
		cancelButton = new JButton("Cancel");
		spinnerModel = new SpinnerNumberModel(3306, 0, 9999, 1);
		portSpinner = new JSpinner(spinnerModel);
		
		userField = new JTextField(10);
		passField = new JPasswordField(10);
//		passField.setEchoChar('*'); /*statement to change echo character*/
		
		layoutComponents();

		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				Integer port = (Integer)portSpinner.getValue();
				
				String usernameInfo = userField.getText();
				
				char[] passInfo = passField.getPassword();
				
//				System.out.println(usernameInfo + " : " + new String(passInfo));
				 
				if(prefsListener != null) {
					prefsListener.preferencesSet(usernameInfo, new String(passInfo), port);
				}
				setVisible(false);
			}
		});
		
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				
				setVisible(false);
			}
		});
		
		setSize(340, 250);
		setLocationRelativeTo(parent);
	}
	private void layoutComponents() {
		JPanel controlPanel = new JPanel();
		JPanel buttonsPanel = new JPanel();
		
		Border spaceBorder = BorderFactory.createEmptyBorder(15, 15, 15, 15);
		Border titleBorder = BorderFactory.createTitledBorder("Database connector");
		
		controlPanel.setBorder(BorderFactory.createCompoundBorder(spaceBorder, titleBorder));
		
//		controlPanel.setBorder(titleBorder);
//		buttonsPanel.setBorder(BorderFactory.createEtchedBorder());
		////////layout///////
		controlPanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		Insets rightPadding = new Insets(0,0,0,15);
		Insets noPadding = new Insets(0,0,0,0);
		//row1//
		gc.gridy = 0;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlPanel.add(new JLabel("Username: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlPanel.add(userField, gc);
		
		//next row//
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;
		gc.fill = GridBagConstraints.NONE;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlPanel.add(new JLabel("Password: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlPanel.add(passField, gc);
		
		//next row//
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;
		gc.fill = GridBagConstraints.NONE;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.EAST;
		gc.insets = rightPadding;
		controlPanel.add(new JLabel("Port: "), gc);
		gc.gridx++;
		gc.anchor = GridBagConstraints.WEST;
		gc.insets = noPadding;
		controlPanel.add(portSpinner, gc);
		
		//Buttons Panel//
		buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		buttonsPanel.add(okButton);
		buttonsPanel.add(cancelButton);
		
		//Add sub panels to Dialog box//
		Dimension btnSize = cancelButton.getPreferredSize();
		okButton.setPreferredSize(btnSize);
		
		setLayout(new BorderLayout());
		add(controlPanel, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.SOUTH);
	}
	
	public void setDefaults(String user, String pass, int port) {
		userField.setText(user);
		passField.setText(pass);
		portSpinner.setValue(port);
		
	}

	public void setPrefsListener(PrefsListener prefsListener) {
		this.prefsListener = prefsListener;
	}
}
