package com.ganesh.main;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Cursor;
import java.awt.Rectangle;
import javax.swing.JTextArea;
import java.awt.Frame;
import java.awt.Component;

public class DialogBox extends JFrame {

	private static final long serialVersionUID = 1625761700567665605L;
	
	JTextArea txtrHi;

	public DialogBox() {
		setResizable(false);
		setState(Frame.ICONIFIED);		
		setBounds(new Rectangle(400, 350, 0, 0));
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(20, 20, 335, 116);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFocusable(false);
		btnNewButton.setFocusTraversalKeysEnabled(false);
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setIconTextGap(0);
		btnNewButton.setBackground(SystemColor.inactiveCaptionBorder);
		btnNewButton.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNewButton.setFont(new Font("Verdana", Font.PLAIN, 13));
		btnNewButton.setBounds(246, 60, 80, 25);
		btnNewButton.setBorder(UIManager.getBorder("Button.border"));
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNewLabel_1.setBackground(SystemColor.info);
		lblNewLabel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel_1.setBounds(0, 58, 329, 29);
		getContentPane().add(lblNewLabel_1);
		
		txtrHi = new JTextArea();
		txtrHi.setWrapStyleWord(true);
		txtrHi.setForeground(new Color(0, 128, 128));
		txtrHi.setLineWrap(true);
		txtrHi.setFont(new Font("SansSerif", Font.BOLD, 14));
		txtrHi.setBackground(SystemColor.info);
		txtrHi.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtrHi.setBounds(0, 0, 329, 58);
		getContentPane().add(txtrHi);
	}
	
	//success message
	public void successMsg(String msg) {
		txtrHi.setText(msg);
	}
	
	//error messages
	public void errorMsg(String msg) {
		txtrHi.setText(msg);
	}
}
