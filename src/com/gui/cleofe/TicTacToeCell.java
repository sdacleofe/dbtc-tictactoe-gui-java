package com.gui.cleofe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TicTacToeCell extends JPanel {

	public static final int BLANK = 0;

	public static final int CIRCLE = 1;

	public static final int CROSS = 2;

	private static final String CROSS_IMAGE_FILE = "images/cross.png";

	private static final String CIRCLE_IMAGE_FILE = "images/circle.png";

	private static final String BLANK_IMAGE_FILE = "images/blank.png";

	private JLabel content;

	private Point location;

	public TicTacToeCell() {
		this(null);
	}

	public TicTacToeCell(Point pt) {

		ImageIcon initImage = new ImageIcon("images/blank.png");

		setLayout(new BorderLayout());
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.WHITE));

		content = new JLabel(initImage);
		add(content);

		location = pt;
	}

	public Point getPosition() {
		return location;
	}

	public void setContent(int image) {

		switch (image) {

		case CIRCLE:
			content.setIcon(new ImageIcon(CIRCLE_IMAGE_FILE));
			break;

		case CROSS:
			content.setIcon(new ImageIcon(CROSS_IMAGE_FILE));
			break;

		default: // do nothing
			break;
		}
	}
}