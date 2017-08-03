package com.ganesh.main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class HelperImageGUI extends JFrame {
	
	private static final long serialVersionUID = 3463291448254402917L;
	
	private JPanel contentPane;

	public HelperImageGUI() {
		setResizable(false);
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 20, 883, 643);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(HelperImageGUI.class.getResource("/com/ganesh/resources/Untitled.png")));
		lblNewLabel.setBounds(0, 0, 880, 650);
		contentPane.add(lblNewLabel);
	}
}
