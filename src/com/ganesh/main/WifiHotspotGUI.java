package com.ganesh.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.UIManager;
import java.awt.Cursor;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class WifiHotspotGUI {
	
	private WifiHotspot wifi;
	
	private String[] info;
	

	private JFrame frmWifiHotspot;
	
	private static JTextField txtNetworkId;
	private static JTextField txtSecurityKey;
	private static JButton btnStart;
	private static JLabel lblRunning;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WifiHotspotGUI window = new WifiHotspotGUI();
					window.frmWifiHotspot.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WifiHotspotGUI() {
		info = new PropertiesInfo().getInfo();
		wifi = new WifiHotspot();
		initialize();
	}
	
	public static void revertChanges() {
		btnStart.setEnabled(true);
		txtNetworkId.setEditable(true);
		txtSecurityKey.setEditable(true);
		
		lblRunning.setIcon(new ImageIcon(WifiHotspotGUI.class.getResource("/com/ganesh/resources/Stopped.png")));
		lblRunning.setText("Stopped");
	}

	private void initialize() {
		frmWifiHotspot = new JFrame();
		frmWifiHotspot.setLocationByPlatform(true);
		frmWifiHotspot.setResizable(false);
		frmWifiHotspot.setLocation(450, 350);
		frmWifiHotspot.getContentPane().setForeground(new Color(139, 69, 19));
		frmWifiHotspot.setForeground(Color.BLACK);
		frmWifiHotspot.setTitle("Wifi Hotspot");
		frmWifiHotspot.setBackground(Color.WHITE);
		frmWifiHotspot.getContentPane().setBackground(new Color(220, 220, 220));
		frmWifiHotspot.getContentPane().setFont(new Font("Verdana", Font.BOLD, 14));
		frmWifiHotspot.setFont(new Font("Verdana", Font.PLAIN, 12));
		frmWifiHotspot.setBounds(100, 100, 396, 416);
		frmWifiHotspot.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmWifiHotspot.getContentPane().setLayout(null);
		
		lblRunning = new JLabel("Stopped");
		lblRunning.setIcon(new ImageIcon(WifiHotspotGUI.class.getResource("/com/ganesh/resources/Stopped.png")));
		lblRunning.setOpaque(true);
		lblRunning.setBackground(SystemColor.control);
		lblRunning.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblRunning.setBounds(299, 83, 79, 20);
		frmWifiHotspot.getContentPane().add(lblRunning);
		
		JLabel imgLabel = new JLabel("Wifi Hotspot");
		imgLabel.setOpaque(true);
		imgLabel.setForeground(new Color(0, 128, 128));
		imgLabel.setBackground(SystemColor.activeCaptionBorder);
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 45));
		imgLabel.setBounds(0, 0, 390, 74);
		frmWifiHotspot.getContentPane().add(imgLabel);
		
		JLabel lblNetworkId = new JLabel("Network ssid:");
		lblNetworkId.setOpaque(true);
		lblNetworkId.setBackground(SystemColor.control);
		lblNetworkId.setForeground(new Color(0, 128, 128));
		lblNetworkId.setFont(new Font("Lucida Calligraphy", Font.BOLD, 13));
		lblNetworkId.setBounds(10, 127, 115, 25);
		frmWifiHotspot.getContentPane().add(lblNetworkId);
		
		JLabel lblSecurityKey = new JLabel("Security Key:");
		lblSecurityKey.setOpaque(true);
		lblSecurityKey.setBackground(SystemColor.control);
		lblSecurityKey.setForeground(new Color(0, 128, 128));
		lblSecurityKey.setFont(new Font("Lucida Calligraphy", Font.BOLD, 13));
		lblSecurityKey.setToolTipText("");
		lblSecurityKey.setBounds(10, 179, 115, 25);
		frmWifiHotspot.getContentPane().add(lblSecurityKey);
		
		txtNetworkId = new JTextField(info[0]);
		txtNetworkId.setForeground(new Color(165, 42, 42));
		txtNetworkId.setBackground(SystemColor.inactiveCaptionBorder);
		txtNetworkId.setBorder(null);
		txtNetworkId.setFont(new Font("Lucida Calligraphy", Font.BOLD, 14));
		txtNetworkId.setBounds(129, 127, 166, 25);
		frmWifiHotspot.getContentPane().add(txtNetworkId);
		txtNetworkId.setColumns(10);
		
		txtSecurityKey = new JTextField(info[1]);
		txtSecurityKey.setForeground(new Color(165, 42, 42));
		txtSecurityKey.setBackground(SystemColor.inactiveCaptionBorder);
		txtSecurityKey.setBorder(null);
		txtSecurityKey.setFont(new Font("Lucida Calligraphy", Font.BOLD, 14));
		txtSecurityKey.setBounds(129, 179, 166, 25);
		frmWifiHotspot.getContentPane().add(txtSecurityKey);
		txtSecurityKey.setColumns(10);
		
		btnStart = new JButton("START");
		btnStart.setFocusPainted(false);
		btnStart.setOpaque(false);
		btnStart.setBackground(SystemColor.inactiveCaption);
		btnStart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStart.setBorder(UIManager.getBorder("Button.border"));
		btnStart.setForeground(new Color(30, 144, 255));
		btnStart.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
		btnStart.setBounds(46, 244, 123, 35);
		
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new PropertiesInfo().setInfo(txtNetworkId.getText(), txtSecurityKey.getText());
					
				btnStart.setEnabled(false);
				txtNetworkId.setEditable(false);
				txtSecurityKey.setEditable(false);
					
				lblRunning.setIcon(new ImageIcon(WifiHotspotGUI.class.getResource("/com/ganesh/resources/Running.png")));
				lblRunning.setText("Running");
				
				wifi.setInfo(txtNetworkId.getText(), txtSecurityKey.getText());
			}
				
		});
		
		frmWifiHotspot.getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("STOP");
		btnStop.setFocusPainted(false);
		btnStop.setOpaque(false);
		btnStop.setBackground(SystemColor.inactiveCaption);
		btnStop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStop.setBorder(UIManager.getBorder("Button.border"));
		btnStop.setForeground(new Color(30, 144, 255));
		btnStop.setFont(new Font("Lucida Calligraphy", Font.BOLD, 15));
		btnStop.setBounds(218, 244, 123, 35);
		
		btnStop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(lblRunning.getText().equalsIgnoreCase("Stopped")) {
					return;
				}
				else {
					btnStart.setEnabled(true);
					txtNetworkId.setEditable(true);
					txtSecurityKey.setEditable(true);
					
					lblRunning.setIcon(new ImageIcon(WifiHotspotGUI.class.getResource("/com/ganesh/resources/Stopped.png")));
					lblRunning.setText("Stopped");
					
					wifi.stop();
				}
			}
		});
		
		frmWifiHotspot.getContentPane().add(btnStop);
		
		JLabel lblStep1 = new JLabel("Step 1");
		lblStep1.setIconTextGap(1);
		lblStep1.setIcon(new ImageIcon(WifiHotspotGUI.class.getResource("/com/ganesh/resources/arrowLeft.png")));
		lblStep1.setOpaque(true);
		lblStep1.setBackground(SystemColor.control);
		lblStep1.setForeground(new Color(139, 0, 0));
		lblStep1.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
		lblStep1.setBounds(299, 127, 79, 25);
		frmWifiHotspot.getContentPane().add(lblStep1);
		
		JLabel lblStep2 = new JLabel("Step 2");
		lblStep2.setIconTextGap(1);
		lblStep2.setIcon(new ImageIcon(WifiHotspotGUI.class.getResource("/com/ganesh/resources/arrowLeft.png")));
		lblStep2.setOpaque(true);
		lblStep2.setBackground(SystemColor.control);
		lblStep2.setForeground(new Color(139, 0, 0));
		lblStep2.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
		lblStep2.setBounds(299, 179, 79, 25);
		frmWifiHotspot.getContentPane().add(lblStep2);
		
		JLabel lblStep3 = new JLabel("Step 3");
		lblStep3.setHorizontalTextPosition(SwingConstants.LEADING);
		lblStep3.setIconTextGap(10);
		lblStep3.setIcon(new ImageIcon(WifiHotspotGUI.class.getResource("/com/ganesh/resources/arrowAbove.png")));
		lblStep3.setOpaque(true);
		lblStep3.setBackground(SystemColor.control);
		lblStep3.setForeground(new Color(139, 0, 0));
		lblStep3.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
		lblStep3.setBounds(46, 309, 79, 25);
		frmWifiHotspot.getContentPane().add(lblStep3);
		
		JLabel lblStep4 = new JLabel("Step 4");
		lblStep4.setIconTextGap(10);
		lblStep4.setHorizontalTextPosition(SwingConstants.LEADING);
		lblStep4.setIcon(new ImageIcon(WifiHotspotGUI.class.getResource("/com/ganesh/resources/arrowRight.png")));
		lblStep4.setOpaque(true);
		lblStep4.setBackground(UIManager.getColor("CheckBox.background"));
		lblStep4.setForeground(new Color(139, 0, 0));
		lblStep4.setFont(new Font("Lucida Calligraphy", Font.BOLD, 12));
		lblStep4.setBounds(46, 353, 87, 26);
		frmWifiHotspot.getContentPane().add(lblStep4);
		
		JButton btnImage = new JButton("");
		btnImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnImage.setBorder(null);
		btnImage.setIcon(new ImageIcon(WifiHotspotGUI.class.getResource("/com/ganesh/resources/icon.png")));
		btnImage.setBounds(315, 309, 63, 35);
		frmWifiHotspot.getContentPane().add(btnImage);
		
		btnImage.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new HelperImageGUI().setVisible(true);				
			}
		});
		
		JTextArea txtrLastButVery = new JTextArea();
		txtrLastButVery.setForeground(new Color(255, 0, 0));
		txtrLastButVery.setBackground(UIManager.getColor("CheckBox.background"));
		txtrLastButVery.setRows(3);
		txtrLastButVery.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 11));
		txtrLastButVery.setText("1. Press Window Key + R\r\n2. Type ncpa.cpl \u2014> hit enter.\r\n\r\n3. Follow the instruction given in image.");
		txtrLastButVery.setBounds(132, 310, 248, 69);
		frmWifiHotspot.getContentPane().add(txtrLastButVery);		
		
		JButton btnHelp = new JButton("Help");
		btnHelp.setBackground(new Color(240, 240, 240));
		btnHelp.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		btnHelp.setFocusPainted(false);
		btnHelp.setBorder(UIManager.getBorder("Button.border"));
		btnHelp.setBounds(107, 82, 87, 23);
		
		btnHelp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogBox dialog = new DialogBox();
				dialog.setVisible(true);
				dialog.successMsg("If you got any problem contact me on\ng.karale2911@gmail.com.\nplease mention subject line in mail.");
			}
		});
		
		frmWifiHotspot.getContentPane().add(btnHelp);
		
		JButton btnAbout = new JButton("About");
		btnAbout.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 12));
		btnAbout.setFocusPainted(false);
		btnAbout.setBorder(UIManager.getBorder("Button.border"));
		btnAbout.setBackground(SystemColor.menu);
		btnAbout.setBounds(10, 82, 87, 23);
		
		btnAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				DialogBox dialog = new DialogBox();
				dialog.setVisible(true);
				dialog.successMsg("Author : Ganesh Karale\nCreated on : May 20, 2016");
			}
		});
		
		frmWifiHotspot.getContentPane().add(btnAbout);
	}
	
	@Override
	public void finalize() throws Throwable {
		wifi.stop();
		super.finalize();
	}
}
