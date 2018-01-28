package com.enere.simplePackage;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class FormPanel extends JPanel {
	private JLabel firstNameLabel;
	private JTextField firstNameField;
	private JLabel lastNameLabel;
	private JTextField lastNameField;
	private JButton submit;
	private FormListener formListener;
	@SuppressWarnings("rawtypes")
	private JList ageList;
	@SuppressWarnings("rawtypes")
	private JComboBox empComboBox;
	private JCheckBox citizenry;
	private JLabel taxLabel;
	private JTextField taxField;
	
	private JRadioButton maleRadio;
	private JRadioButton femaleRadio;
	private ButtonGroup genderGroup;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FormPanel() {
		Dimension dim = getPreferredSize();
		dim.width = 250;
		setPreferredSize(dim);
		
		Border innerBorder = BorderFactory.createTitledBorder("Add Employee");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder ));
		
		//FormPanel elems
		firstNameLabel = new JLabel("First Name: ");
		firstNameField = new JTextField(10);
		lastNameLabel = new JLabel("Last Name: ");
		lastNameField = new JTextField(10);
		submit = new JButton("Submit");
		ageList = new JList();
		empComboBox = new JComboBox();
		citizenry = new JCheckBox();
		taxLabel = new JLabel("Tax ID");
		taxField = new JTextField(10);
		maleRadio = new JRadioButton("Male");
		femaleRadio = new JRadioButton("Female");
		genderGroup = new ButtonGroup();
		
		//set up mnemonics
		submit.setMnemonic(KeyEvent.VK_ENTER);
		
		// Gender group setup
		genderGroup.add(maleRadio);
		genderGroup.add(femaleRadio);
		maleRadio.setSelected(true);
		maleRadio.setActionCommand("Male");
		femaleRadio.setActionCommand("Female");
		
		//list setup
		DefaultListModel ageModel = new DefaultListModel();
		ageModel.addElement(new AgeCategory(0,"Under 18"));
		ageModel.addElement(new AgeCategory(1,"18 to 65"));
		ageModel.addElement(new AgeCategory(2,"Over 65"));
		ageList.setModel(ageModel);
		ageList.setPreferredSize(new Dimension(115, 66));
		ageList.setBorder(BorderFactory.createEtchedBorder());
		ageList.setSelectedIndex(1);
		
		//Combo Box setup
		DefaultComboBoxModel empModel = new DefaultComboBoxModel();
		empModel.addElement("Hourly");
		empModel.addElement("Salaried");
		empModel.addElement("Contractor");
		empComboBox.setModel(empModel);
		empComboBox.setSelectedIndex(0);
		empComboBox.setEditable(true);
		
		//TaxID setup
		taxLabel.setEnabled(false);
		taxField.setEnabled(false);
		
		citizenry.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent event) {
				boolean isTicked = citizenry.isSelected();
				
				taxLabel.setEnabled(isTicked);
				taxField.setEnabled(isTicked);
			}
		});
		
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent event) {
				String firstName = firstNameField.getText();
				String lastName = lastNameField.getText();
				AgeCategory ageCat = (AgeCategory) ageList.getSelectedValue();
				String empType = (String) empComboBox.getSelectedItem();
				String taxId = taxField.getText();
				boolean usCitizen = citizenry.isSelected();
				
				String gender = genderGroup.getSelection().getActionCommand();
				
				FormEvent formEvent = new FormEvent(this, firstName, lastName, 
						ageCat.getId(), empType, taxId, usCitizen, gender);
				
				if (formListener != null) {
					formListener.formEventOccurrence(formEvent);
				}
				
//				if (ageCat != null) {
//					System.out.println(ageCat.getId());
//				}
			}
			
		});
		
		layoutComponents();
		

	}
	
	public void layoutComponents() {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbConstraints = new GridBagConstraints();
	
		//first row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0.1;
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
		gbConstraints.gridx = 0;
		gbConstraints.gridy++;
		gbConstraints.insets = new Insets(0, 0, 0, 5);
		gbConstraints.anchor = GridBagConstraints.LINE_END;
		add(lastNameLabel, gbConstraints);
		
		gbConstraints.anchor = GridBagConstraints.LINE_START;
		gbConstraints.gridx = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		add(lastNameField,gbConstraints);
		
		//Third row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0.2;
		gbConstraints.gridy++;
		
		gbConstraints.gridx = 0;
		gbConstraints.insets = new Insets(0, 0, 0, 5);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Age Group:"), gbConstraints);
		
		gbConstraints.gridx = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(ageList, gbConstraints);
		
		//Next row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0.2;
		gbConstraints.gridy++;
		gbConstraints.gridx = 0;
		gbConstraints.insets = new Insets(0, 0, 0, 5);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Employee Type:"), gbConstraints);
		
		gbConstraints.gridx = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(empComboBox, gbConstraints);
		
		//Next row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0.2;
		gbConstraints.gridy++;
		gbConstraints.gridx = 0;
		gbConstraints.insets = new Insets(0, 0, 0, 5);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("US Citizen:"), gbConstraints);
		
		gbConstraints.gridx = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(citizenry, gbConstraints);
		
		//Next row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0.2;
		gbConstraints.gridy++;
		gbConstraints.gridx = 0;
		gbConstraints.insets = new Insets(0, 0, 0, 5);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(taxLabel, gbConstraints);
		
		gbConstraints.gridx = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(taxField, gbConstraints);
		
		//Next row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0.2;
		gbConstraints.gridy++;
		gbConstraints.gridx = 0;
		gbConstraints.insets = new Insets(0, 0, 0, 5);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(taxLabel, gbConstraints);
		
		gbConstraints.gridx = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(taxField, gbConstraints);
		
		//Next row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 0.2;
		gbConstraints.gridy++;
		gbConstraints.gridx = 0;
		gbConstraints.insets = new Insets(0, 0, 0, 5);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Gender: "), gbConstraints);
		
		gbConstraints.gridx = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(maleRadio, gbConstraints);
		
		//Next row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 2.0;
		gbConstraints.gridy++;
		
		gbConstraints.gridx = 1;
		gbConstraints.insets = new Insets(0, 0, 0, 0);
		gbConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		add(femaleRadio, gbConstraints);
		
		//Next row
		gbConstraints.weightx = 1;
		gbConstraints.weighty = 2.0;
		gbConstraints.gridx = 0;
		gbConstraints.gridy++;
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
	
	void resetValues() {
		firstNameField.setText(null);
		lastNameField.setText(null);
		taxField.setText(null);
		
	}
}

class AgeCategory {
	private int id;
	private String text;
	
	public AgeCategory (int id, String text) {
		this.id = id;
		this.text = text;
	}
	
	@Override
	public String toString() {
		return text;
	}
	
	public int getId () {
		return this.id;
	}
	
}
