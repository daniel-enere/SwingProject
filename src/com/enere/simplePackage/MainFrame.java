package com.enere.simplePackage;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

import javax.swing.*;

import controller.Controller;

public class MainFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4703116364184859655L;
	private TextPanel textPanel;
	private Toolbar toolbar;
	private FormPanel formPanel;
	private JFileChooser fileChooser;
	private Controller controller;
	private EmployeeTablePanel empTablePanel;
	private PrefsDialog dialog;
	private Preferences prefs;
	// private MainMenuBar menuBar; will implement at a later date

	public MainFrame() {
		super("Dan Gui");

		setLayout(new BorderLayout());

		textPanel = new TextPanel();
		toolbar = new Toolbar();
		formPanel = new FormPanel();
		fileChooser = new JFileChooser();
		empTablePanel = new EmployeeTablePanel();
		dialog = new PrefsDialog(this);
		
		prefs = Preferences.userRoot().node("db");
		// menuBar = new MainMenuBar();

		controller = new Controller();
		empTablePanel.setData(controller.getEmployees());
		
		//Employee Table listener set up
		empTablePanel.setEmployeeTableListener(new EmployeeTableListener() {
			public void rowDeleted(int row) {
				controller.removeEmployee(row);
			}
		});
		
		//preferences Listener set up
		dialog.setPrefsListener(new PrefsListener() {
			@Override
			public void preferencesSet(String user, String pass, int port) {
			 prefs.put("user", user);
			 prefs.put("pass", pass);
			 prefs.putInt("port", port);
			}
			
		});
		String user = prefs.get("user", "");
		String pass = prefs.get("pass", "");
		Integer port = prefs.getInt("port", 3306);
		dialog.setDefaults(user, pass, port);

		// fileChooser setup
		fileChooser.addChoosableFileFilter(new EmployeeFileFilter());

		setJMenuBar(createMenuBar());

		toolbar.setToolbarListener(new ToolbarListener() {

			@Override
			public void saveEvent() {
				connect();
				try {
					controller.save();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to save to database", "Database Connection Error", JOptionPane.ERROR_MESSAGE);
				}
			}

			@Override
			public void refreshEvent() {
				connect();
				try {
					controller.load();
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(MainFrame.this, "Unable to load from database", "Database Connection Error", JOptionPane.ERROR_MESSAGE);
				}
				
				empTablePanel.refresh();
				
			}
		});

		formPanel.setFormListener(new FormListener() {
			public void formEventOccurrence(FormEvent event) {
				controller.addEmployee(event);
				empTablePanel.refresh();
				formPanel.resetValues();
				// String firstName = event.getFirstName();
				// String lastName = event.getLastName();
				// int ageCat = event.getAgeCategory();
				// String empType = event.getEmpCat();
				// String gender = event.getGender();
				//
				// textPanel.appendText(lastName + ", " + firstName + "\n" + "Age Group: " +
				// ageCat + "\n" + empType + "\n"
				// + gender + "\n\n");

			}
		});

		add(toolbar, BorderLayout.NORTH);
		// add(textPanel, BorderLayout.CENTER); /* fix later*/
		add(formPanel, BorderLayout.WEST);
		add(empTablePanel, BorderLayout.CENTER);

		setMinimumSize(new Dimension(300, 400));
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}
	
	private void connect() {
		try {
			controller.connect();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(MainFrame.this, "Unable to connect to database", "Database Connection Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();

		// file menu setup
		JMenu fileMenu = new JMenu("File");
		JMenuItem exportDataItem = new JMenuItem("Export Data...");
		JMenuItem importDataItem = new JMenuItem("Import Data...");
		JMenuItem exitItem = new JMenuItem("Exit");
		fileMenu.add(exportDataItem);
		fileMenu.add(importDataItem);
		fileMenu.addSeparator();
		fileMenu.add(exitItem);
		menuBar.add(fileMenu);

		// import setup
		importDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent importEvent) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.loadFromFile(fileChooser.getSelectedFile());
						empTablePanel.refresh();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrame.this, "Failed to load from file", "File Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// export setup
		exportDataItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent importEvent) {
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					try {
						controller.saveToFile(fileChooser.getSelectedFile());
					} catch (IOException e) {
						JOptionPane.showMessageDialog(MainFrame.this, "Failed to save to file", "File Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});

		// window menu set up
		JMenu windowMenu = new JMenu("Window");
		// show menu item
		JMenu showMenu = new JMenu("Show Menu");
		windowMenu.add(showMenu);
		// window menu sub item setup
		JMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
		showFormItem.setSelected(true);
		showMenu.add(showFormItem);
		menuBar.add(windowMenu);
		JMenuItem prefItem = new JMenuItem("Preferences...");
		prefItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				dialog.setVisible(true);
			}	
		});
		windowMenu.add(prefItem);

		showFormItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) event.getSource();

				formPanel.setVisible(menuItem.isSelected());
			}
		});
		
		//Accelerators
		fileMenu.setMnemonic(KeyEvent.VK_F);
		exitItem.setMnemonic(KeyEvent.VK_X);
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		prefItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
		
		exitItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent exitEvent) {

				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure?", "Confirm Exit",
						JOptionPane.OK_CANCEL_OPTION);

				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}

		});

		return menuBar;
	}
}
