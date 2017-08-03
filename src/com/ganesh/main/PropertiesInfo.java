package com.ganesh.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesInfo {
	DialogBox dialog;
	
	Properties props;
	InputStream input;
	OutputStream out;
	
	public void setInfo(String networkId, String securityKey) {
		try {
			props = new Properties();
			out = new FileOutputStream("C:\\Users\\Public\\Documents\\WifiHotspot\\networkInfo.properties");
			
			props.setProperty("NetworkId", networkId);
			props.setProperty("SecurityKey", securityKey);
			
			props.store(out, null);
		}
		catch (Exception e) {
			dialog = new DialogBox();
			dialog.setVisible(true);
			dialog.errorMsg(e.getMessage());
		}
		finally {
			try {
				if(out != null) {
					out.close();
				}
			}
			catch (Exception e) {
				dialog = new DialogBox();
				dialog.setVisible(true);
				dialog.errorMsg(e.getMessage());
			}
		}
	}
	
	public String[] getInfo() {
		String[] info = new String[2];
		
		if(isFileAvailable()) {
			try {
				props = new Properties();
				input = new FileInputStream("C:\\Users\\Public\\Documents\\WifiHotspot\\networkInfo.properties");
				
				props.load(input);
				
				info[0] = props.getProperty("NetworkId");
				info[1] = props.getProperty("SecurityKey");
				
			}
			catch (Exception e) {
				dialog = new DialogBox();
				dialog.setVisible(true);
				dialog.errorMsg(e.getMessage());
			}
			finally {
				try {
					if(input != null) {
						input.close();
					}
				}
				catch (Exception e) {
					dialog = new DialogBox();
					dialog.setVisible(true);
					dialog.errorMsg(e.getMessage());
				}
			}
			
		}
		else {
			try {
				new File("C:\\Users\\Public\\Documents\\WifiHotspot").mkdir();
				new File("C:\\Users\\Public\\Documents\\WifiHotspot\\networkInfo.properties").createNewFile();
			}
			catch (Exception e) {
				dialog = new DialogBox();
				dialog.setVisible(true);
				dialog.errorMsg(e.getMessage());
			}
		}		
		return info;
	}
	
	private boolean isFileAvailable() {
		boolean available = false;
		
		File file = new File("C:\\Users\\Public\\Documents\\WifiHotspot\\networkInfo.properties");
		
		if(file.exists()) {
			available = true;
		}
		else {
			available = false;
		}
		
		return available;
	}
	
}
