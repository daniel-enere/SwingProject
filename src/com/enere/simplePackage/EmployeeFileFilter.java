package com.enere.simplePackage;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class EmployeeFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		
		String name = file.getName();
		
		String extension = Utils.getFileExtension(name);
		
		if (extension == null) {
			return false;
		} 
		
		if (extension.equals("emp")) {
			return true;
		}
		return false;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Employee database files {*.emp}";
	}

}
