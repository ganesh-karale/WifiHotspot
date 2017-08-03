package com.ganesh.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WifiHotspot {
	
	DialogBox dialog;
	
	//get network id and security key and validate them
	public void setInfo(String networkId, String securityKey) {
		if(networkId.length() > 0) {
			if(securityKey.length() >= 8) {
				start(networkId, securityKey);
			}
			else {
				WifiHotspotGUI.revertChanges();
				dialog = new DialogBox();
				dialog.setVisible(true);
				dialog.errorMsg("Security Key must be 8 to 63 character long.");
				return;
			}
		}
		else {
			WifiHotspotGUI.revertChanges();
			dialog = new DialogBox();
			dialog.setVisible(true);
			dialog.errorMsg("Network SSID must not be null. Atleast enter one character.");
			return;
		}
	}
	
	//start the wifi creation process
	private void start(String networkId, String securityKey) {
		String cmd_1 = "netsh wlan set hostednetwork mode=allow ssid=" + networkId + " key=" + securityKey;
		String cmd_2 = "netsh wlan start hostednetwork";
		
		try {
			Runtime.getRuntime().exec(cmd_1);
			Runtime.getRuntime().exec(cmd_2);
		}
		catch (Exception e) {
			WifiHotspotGUI.revertChanges();
			dialog = new DialogBox();
			dialog.setVisible(true);
			dialog.errorMsg(e.getMessage());
		}
		if(!getStatus()) {
			WifiHotspotGUI.revertChanges();
			stop();
			dialog = new DialogBox();
			dialog.setVisible(true);
			dialog.errorMsg("Problem in creating Wifi Hotspot. Please check your PC settings or try again.");
			return;
		}
	}
	
	public void stop() {
		String cmd = "netsh wlan stop hostednetwork";
		
		try {
			Runtime.getRuntime().exec(cmd);
		}
		catch (Exception e) {
			WifiHotspotGUI.revertChanges();
			dialog = new DialogBox();
			dialog.setVisible(true);
			dialog.errorMsg(e.getMessage());
		}
	}
	
	//check status whether started or not
	public boolean getStatus() {
		
		boolean status = false;
		String cmd = "netsh wlan show hostednetwork";
				
		try {
			Process process = Runtime.getRuntime().exec(cmd);
			process.waitFor();
			
			String input = "";
			
			BufferedReader stdInput = new BufferedReader(new
	                 InputStreamReader(process.getInputStream()));
			
			while((input = stdInput.readLine()) != null) {
				
				if(input.equalsIgnoreCase("    Status                 : Started")) {
					status = true;
				}
				else if(input.equalsIgnoreCase("    Status                 : Not started")) {
					status = false;
				}
				
			}
		}
		catch (Exception e) {
			WifiHotspotGUI.revertChanges();
			dialog = new DialogBox();
			dialog.setVisible(true);
			dialog.errorMsg(e.getMessage());
		}
		
		return status;
	}
	
}
