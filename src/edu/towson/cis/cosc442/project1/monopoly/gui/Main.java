package edu.towson.cis.cosc442.project1.monopoly.gui;

import edu.towson.cis.cosc442.project1.monopoly.*;
import javax.swing.JOptionPane;

/**
 * Starts the Monopoly graphical application and initializes its game state.
 */
public class Main {

	/**
	 * Prompts until a valid number of players is supplied.
	 *
	 * @param window parent window for the input dialogs
	 * @return the accepted player count
	 */
	private static int inputNumberOfPlayers(MainWindow window) {
		int numPlayers = 0;
		while(numPlayers <= 0 || numPlayers > GameMaster.MAX_PLAYER) {
			String numberOfPlayers = JOptionPane.showInputDialog(window, "How many players");
			if(numberOfPlayers == null) {
				System.exit(0);
			}
			numPlayers = parseNumberOfPlayers(window, numberOfPlayers);
			if (numPlayers <= 0 || numPlayers > GameMaster.MAX_PLAYER) {
				JOptionPane.showMessageDialog(window, "Please input a number between one and eight");
			} else {
				GameMaster.instance().setNumberOfPlayers(numPlayers);
			}
		}
		return numPlayers;
	}

	/**
	 * Parses a player-count response and reports nonnumeric input.
	 *
	 * @param window parent window for the error dialog
	 * @param input text entered by the user
	 * @return the parsed count, or zero when the input is not numeric
	 */
	private static int parseNumberOfPlayers(MainWindow window, String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(window, "Please input a number");
			return 0;
		}
	}

	/**
	 * Initializes the board, players, and Swing game window.
	 *
	 * @param args optional test mode and game-board class arguments
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		GameMaster master = GameMaster.instance();
		MainWindow window = new MainWindow();
		GameBoard gameBoard;
		if(args.length > 0) {
			if(args[0].equals("test")) {
				master.setTestMode(true);
			}
			try {
				Class<?> c = Class.forName(args[1]);
				gameBoard = (GameBoard)c.newInstance();
			}
			catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(window, "Class Not Found.  Program will exit");
				System.exit(0);
				return;
			}
			catch (IllegalAccessException e ) {
				JOptionPane.showMessageDialog(window, "Illegal Access of Class.  Program will exit");
				System.exit(0);
				return;
			}
			catch (InstantiationException e) {
				JOptionPane.showMessageDialog(window, "Class Cannot be Instantiated.  Program will exit");
				System.exit(0);
				return;
			}
		}
		else {
			gameBoard = new GameBoardFull();
		}
		master.setGameBoard(gameBoard);
		int numPlayers = inputNumberOfPlayers(window);
		for(int i = 0; i < numPlayers; i++) {
			String name = 
				JOptionPane.showInputDialog(window, "Please input name for Player " + (i+1));
			GameMaster.instance().getPlayer(i).setName(name);
		}
		window.setupGameBoard(gameBoard);
		window.show();
		master.setGUI(window);
		master.startGame();
	}
}
