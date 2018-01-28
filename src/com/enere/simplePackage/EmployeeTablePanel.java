package com.enere.simplePackage;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.TableModel;

import model.Employee;

public class EmployeeTablePanel extends JPanel {
	
	private JTable table;
	private EmployeeTableModel empTableModel;
	private JPopupMenu popup;
	private EmployeeTableListener employeeTableListener;
	
	public EmployeeTablePanel() {
		empTableModel = new EmployeeTableModel();
		table = new JTable(empTableModel);
		popup = new JPopupMenu();
		
		JMenuItem removeItem = new JMenuItem("Delete Entry");
		popup.add(removeItem);
		
		table.addMouseListener(new MouseAdapter() {

			/* (non-Javadoc)
			 * @see java.awt.event.MouseAdapter#mousePressed(java.awt.event.MouseEvent)
			 */
			@Override
			public void mousePressed(MouseEvent mouseEvent) {
				int row = table.rowAtPoint(mouseEvent.getPoint());
				
				table.getSelectionModel().setSelectionInterval(row, row);
				if(mouseEvent.getButton() == MouseEvent.BUTTON3) {
					popup.show(table, mouseEvent.getX(), mouseEvent.getY());
				}
			}
			
		});
		
		removeItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent selectedEvent) {
				int row = table.getSelectedRow();
				
				if(employeeTableListener != null) {
					employeeTableListener.rowDeleted(row);
					empTableModel.fireTableRowsDeleted(row, row);
				}
			}
			
		});
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
	
	public void setData(List<Employee> db) {
		empTableModel.setData(db);
	}

	public void refresh() {
		empTableModel.fireTableDataChanged();
	}
	
	public void setEmployeeTableListener(EmployeeTableListener listener) {
		this.employeeTableListener = listener;
	}
}
