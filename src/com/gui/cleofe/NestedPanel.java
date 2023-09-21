package com.gui.cleofe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

class NestedPanel extends JFrame implements MouseListener, ActionListener {
	
	/***************
	 * Data members
	 ***************/

	private static final int FRAME_WIDTH = 500;

	private static final int FRAME_HEIGHT = 700;

	private static final int FRAME_X_ORIGIN = 0;

	private static final int FRAME_Y_ORIGIN = 0;

	private boolean circle;

	private int boardSize = 4;

	private String[][] arrBoard = new String[boardSize][boardSize];

	private boolean flag;

	private int clicks;

	private int cellX;

	private int cellY;

	private int p1Score = 0;

	private int p2Score = 0;

	private JPanel gamePanel;

	private JLabel player1ScoreLabel;

	private JLabel player2ScoreLabel;

	private TicTacToeCell cell;

	private JPanel controlPanel;
	
	private JButton newGameButton;
	
	private JButton quitGameButton;

	/***************
	 * Main method
	 ***************/
	
	public static void main(String[] args) {
		NestedPanel frame = new NestedPanel();
		frame.setVisible(true);
	}
	
	/***************
	 * Contructors
	 ***************/
	
	public NestedPanel() {
		initGUI();
	}

	public void initGUI(){
		JPanel scorePanel;
		JPanel playerPanel;
		JPanel declarePanel;
		JLabel player1Image;
		JLabel player2Image;
		JLabel drawScoreLabel;
		JLabel declareWinnerLabel;
		
		setLayout(new BorderLayout());
		
		// set the frame properties
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Program Ch14NestedPanels1");
		setLocation(FRAME_X_ORIGIN, FRAME_Y_ORIGIN);

		initBoard();

		scorePanel = new JPanel();
		scorePanel.setBackground(Color.BLACK);
		add(scorePanel, BorderLayout.NORTH);

		scorePanel.setLayout(new BorderLayout());

		ImageIcon player1Icon = new ImageIcon("./Images/player1.png");
		ImageIcon player2Icon = new ImageIcon("./Images/player2.png");

		player1Image = new JLabel("", player1Icon, JLabel.CENTER);
		player2Image = new JLabel("", player2Icon, JLabel.CENTER);

		playerPanel = new JPanel();
		scorePanel.add(playerPanel);
		playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

		player1ScoreLabel = new JLabel("0");
		player1ScoreLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		player1ScoreLabel.setForeground(Color.WHITE);
		playerPanel.add(player1ScoreLabel);

		playerPanel.add(player1Image);

		drawScoreLabel = new JLabel("--");
		drawScoreLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		drawScoreLabel.setForeground(Color.WHITE);
		playerPanel.add(drawScoreLabel);

		playerPanel.add(player2Image);

		player2ScoreLabel = new JLabel("0");
		player2ScoreLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		player2ScoreLabel.setForeground(Color.WHITE);
		playerPanel.add(player2ScoreLabel);

		playerPanel.setBackground(Color.BLACK);

		declarePanel = new JPanel();
		scorePanel.add(declarePanel, BorderLayout.SOUTH);
		declarePanel.setBackground(Color.RED);

		declareWinnerLabel = new JLabel("FIGHT!");
		declareWinnerLabel.setFont(new Font("Verdana", Font.PLAIN, 16));
		declareWinnerLabel.setForeground(Color.WHITE);
		declarePanel.add(declareWinnerLabel); 
	}
	
	/*******************
	 * Create Board Game
	 *******************/
	
	public void initBoard() {
		gamePanel = new JPanel();
		add(gamePanel, BorderLayout.CENTER);
		gamePanel.setLayout(new GridLayout(boardSize, boardSize));
		gamePanel.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				cell = new TicTacToeCell(new Point(row, col));
				arrBoard[row][col] = "-";
				cell.addMouseListener(this);
				gamePanel.add(cell);
			}
		}
		controlPanel = new JPanel();
		add(controlPanel, BorderLayout.SOUTH);

		controlPanel.setLayout(new GridLayout(1, 2));
		newGameButton = new JButton("NEW GAME");
		controlPanel.add(newGameButton);
		newGameButton.setBackground(Color.RED);
		newGameButton.setForeground(Color.WHITE);
		newGameButton.addActionListener(this);

		quitGameButton = new JButton("QUIT GAME");
		controlPanel.add(quitGameButton);
		quitGameButton.setBackground(Color.BLUE);
		quitGameButton.setForeground(Color.WHITE);
		quitGameButton.addActionListener(this);

		circle = true;
	}

	/*************
	 * Valid moves
	 *************/
	
	public void winningCombination(String shape) {
		switch (shape) {
		case "o":
			if ((arrBoard[0][0] == shape && arrBoard[0][1] == shape && arrBoard[0][2] == shape)
					|| (arrBoard[0][1] == shape && arrBoard[0][2] == shape && arrBoard[0][3] == shape)
					|| (arrBoard[1][0] == shape && arrBoard[1][1] == shape && arrBoard[1][2] == shape)
					|| (arrBoard[1][1] == shape && arrBoard[1][2] == shape && arrBoard[1][3] == shape)
					|| (arrBoard[2][0] == shape && arrBoard[2][1] == shape && arrBoard[2][2] == shape)
					|| (arrBoard[2][1] == shape && arrBoard[2][2] == shape && arrBoard[2][3] == shape)
					|| (arrBoard[3][0] == shape && arrBoard[3][1] == shape && arrBoard[3][2] == shape)
					|| (arrBoard[3][1] == shape && arrBoard[3][2] == shape && arrBoard[3][3] == shape)) {
				checkWinner(shape); // check if horizontal move is valid
			} else if ((arrBoard[0][0] == shape && arrBoard[0][1] == shape && arrBoard[0][2] == shape)
					|| (arrBoard[1][0] == shape && arrBoard[2][1] == shape && arrBoard[3][2] == shape)
					|| (arrBoard[0][0] == shape && arrBoard[1][1] == shape && arrBoard[2][2] == shape)
					|| (arrBoard[3][3] == shape && arrBoard[2][2] == shape && arrBoard[1][1] == shape)
					|| (arrBoard[0][1] == shape && arrBoard[1][2] == shape && arrBoard[2][3] == shape)
					|| (arrBoard[0][2] == shape && arrBoard[1][1] == shape && arrBoard[2][0] == shape)
					|| (arrBoard[1][3] == shape && arrBoard[2][2] == shape && arrBoard[3][1] == shape)
					|| (arrBoard[0][3] == shape && arrBoard[1][2] == shape && arrBoard[2][1] == shape)
					|| (arrBoard[3][0] == shape && arrBoard[2][1] == shape && arrBoard[1][2] == shape)) {
				checkWinner(shape); // check if diagonal move is valid
			} else if ((arrBoard[0][0] == shape && arrBoard[1][0] == shape && arrBoard[2][0] == shape)
					|| (arrBoard[3][0] == shape && arrBoard[2][0] == shape && arrBoard[1][0] == shape)
					|| (arrBoard[0][1] == shape && arrBoard[1][1] == shape && arrBoard[2][1] == shape)
					|| (arrBoard[3][1] == shape && arrBoard[2][1] == shape && arrBoard[1][1] == shape)
					|| (arrBoard[0][2] == shape && arrBoard[1][2] == shape && arrBoard[2][2] == shape)
					|| (arrBoard[3][2] == shape && arrBoard[2][2] == shape && arrBoard[1][2] == shape)
					|| (arrBoard[0][3] == shape && arrBoard[1][3] == shape && arrBoard[2][3] == shape)
					|| (arrBoard[3][3] == shape && arrBoard[2][3] == shape && arrBoard[1][3] == shape)) {
				checkWinner(shape); // check if vertical move is valid
			}
			break;
		case "x":

			if ((arrBoard[0][0] == shape && arrBoard[0][1] == shape && arrBoard[0][2] == shape)
					|| (arrBoard[0][1] == shape && arrBoard[0][2] == shape && arrBoard[0][3] == shape)
					|| (arrBoard[1][0] == shape && arrBoard[1][1] == shape && arrBoard[1][2] == shape)
					|| (arrBoard[1][1] == shape && arrBoard[1][2] == shape && arrBoard[1][3] == shape)
					|| (arrBoard[2][0] == shape && arrBoard[2][1] == shape && arrBoard[2][2] == shape)
					|| (arrBoard[2][1] == shape && arrBoard[2][2] == shape && arrBoard[2][3] == shape)
					|| (arrBoard[3][0] == shape && arrBoard[3][1] == shape && arrBoard[3][2] == shape)
					|| (arrBoard[3][1] == shape && arrBoard[3][2] == shape && arrBoard[3][3] == shape)) {
				checkWinner(shape); // check if horizontal move is valid
			} else if ((arrBoard[0][0] == shape && arrBoard[0][1] == shape && arrBoard[0][2] == shape)
					|| (arrBoard[1][0] == shape && arrBoard[2][1] == shape && arrBoard[3][2] == shape)
					|| (arrBoard[0][0] == shape && arrBoard[1][1] == shape && arrBoard[2][2] == shape)
					|| (arrBoard[3][3] == shape && arrBoard[2][2] == shape && arrBoard[1][1] == shape)
					|| (arrBoard[0][1] == shape && arrBoard[1][2] == shape && arrBoard[2][3] == shape)
					|| (arrBoard[0][2] == shape && arrBoard[1][1] == shape && arrBoard[2][0] == shape)
					|| (arrBoard[1][3] == shape && arrBoard[2][2] == shape && arrBoard[3][1] == shape)
					|| (arrBoard[0][3] == shape && arrBoard[1][2] == shape && arrBoard[2][1] == shape)
					|| (arrBoard[3][0] == shape && arrBoard[2][1] == shape && arrBoard[1][2] == shape)) {
				checkWinner(shape); // check if diagonal move is valid
			} else if ((arrBoard[0][0] == shape && arrBoard[1][0] == shape && arrBoard[2][0] == shape)
					|| (arrBoard[3][0] == shape && arrBoard[2][0] == shape && arrBoard[1][0] == shape)
					|| (arrBoard[0][1] == shape && arrBoard[1][1] == shape && arrBoard[2][1] == shape)
					|| (arrBoard[3][1] == shape && arrBoard[2][1] == shape && arrBoard[1][1] == shape)
					|| (arrBoard[0][2] == shape && arrBoard[1][2] == shape && arrBoard[2][2] == shape)
					|| (arrBoard[3][2] == shape && arrBoard[2][2] == shape && arrBoard[1][2] == shape)
					|| (arrBoard[0][3] == shape && arrBoard[1][3] == shape && arrBoard[2][3] == shape)
					|| (arrBoard[3][3] == shape && arrBoard[2][3] == shape && arrBoard[1][3] == shape)) {
				checkWinner(shape); // check if vertical move is valid
			}
			break;
		}
	}
	
	/****************
	 * Declare winner
	 ****************/
	public void checkWinner(String shape) {
		UIManager UI = new UIManager();
		UI.put("OptionPane.background", new Color(0, 0, 0));
		UI.put("Panel.background", new Color(0, 0, 0));
		UI.put("OptionPane.messageForeground", new Color(250, 250, 250));
		switch (shape) {
		case "o":
			p1Score++;
			JOptionPane.showMessageDialog(this, "                         PLAYER 1 WINS", "",
					JOptionPane.PLAIN_MESSAGE);
			String p1 = Integer.toString(p1Score);
			player1ScoreLabel.setText(String.valueOf(p1));

			rematch();

			break;
		case "x":
			p2Score++;
			JOptionPane.showMessageDialog(this, "                         PLAYER 2 WINS", "",
					JOptionPane.PLAIN_MESSAGE);
			String p2 = Integer.toString(p2Score);
			player2ScoreLabel.setText(String.valueOf(p2));

			rematch();
			break;
		}
	}
	
	/*************
	 * Reset the board
	 *************/
	public void rematch() {
		remove(gamePanel);
		initBoard();
		circle = !circle;
		clicks = 0;
	}
	
	/****************
	 * Mouse listener
	 ****************/
	@Override
	public void mouseClicked(MouseEvent e) {
		if (flag == false) {
			TicTacToeCell cell = (TicTacToeCell) e.getSource();
			if (clicks != 16) {
				if (circle) {
					String shape = "o";
					cell.setContent(TicTacToeCell.CIRCLE);
					cellX = (int) cell.getPosition().getX();
					cellY = (int) cell.getPosition().getY();
					setCellX(cellX);
					setCellY(cellY);

					for (int row = 0; row < boardSize; row++) {
						for (int col = 0; col < boardSize; col++) {
							cell = new TicTacToeCell();
							arrBoard[getCellX()][(int) getCellY()] = shape;
							cell.addMouseListener(this);
						}
					}
					winningCombination(shape);

				} else {
					String shape = "x";
					cell.setContent(TicTacToeCell.CROSS);
					cellX = (int) cell.getPosition().getX();
					cellY = (int) cell.getPosition().getY();
					setCellX(cellX);
					setCellY(cellY);

					for (int row = 0; row < boardSize; row++) {
						for (int col = 0; col < boardSize; col++) {
							cell = new TicTacToeCell();
							arrBoard[getCellX()][(int) getCellY()] = shape;
							cell.addMouseListener(this);
						}
					}
					winningCombination(shape);
				}
				clicks++;
			}

			circle = !circle;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) { }

	@Override
	public void mouseReleased(MouseEvent e) { }

	@Override
	public void mouseEntered(MouseEvent e) { }

	@Override
	public void mouseExited(MouseEvent e) { }
	
	/*****************
	 * Action Listener
	 *****************/
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton source = (JButton) e.getSource();
		if (source.getText().equals("NEW GAME")) {
			player1ScoreLabel.setText("");
			player2ScoreLabel.setText("");
			initGUI();
			clicks = 0;
		} else if (source.getText().equals("QUIT GAME")) {
			System.exit(0);
		}
	}
	
	/***************************
	 * Getter and setter methood
	 ***************************/
	
	public int getCellX() {
		return this.cellX;
	}

	public void setCellX(int cellX) {
		this.cellX = cellX;
	}

	public int getCellY() {
		return this.cellY;
	}

	public void setCellY(int cellY) {
		this.cellY = cellY;
	}

}