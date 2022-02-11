/**
 * The class TicTacToeGame is the
 * class that implements the Tic Tac Toe Game.
 * It contains the grid and tracks its progress.
 * It automatically maintain the current state of
 * the game as players are making moves.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Catherine Chen, University of Ottawa
 */
public class TicTacToeGame {

    /**
	 * The board of the game, stored as a one dimension array.
	 */
	CellValue[] board;


    /**
	 * level records the number of rounds that have been
	 * played so far. 
	 */
	int level;

    /**
	 * gameState records the current state of the game.
	 */
	GameState gameState;


    /**
	 * lines is the number of lines in the grid.
	 */
	int lines;

    /**
 	 * columns is the number of columns in the grid.
	 */
	int columns;


    /**
	 * sizeWin is the number of cell of the same type
	 * that must be aligned to win the game.
	 */
	int sizeWin;

	/**
	 * Default constructor, for a game of 3x3, which must
	 * align 3 cells.
	 */
	public TicTacToeGame(){

		this.level = 0;
		this.gameState = GameState.PLAYING;
		this.lines = 3;
		this.columns = 3;
		this.sizeWin = 3;
		this.board = new CellValue[this.lines * this.columns];
		this.createEmptyBoard();	
	}
	
	/**
	 * Constructor allowing to specify the number of lines
	 * and the number of columns for the game. 3 cells must
	 * be aligned.
	 * 
   	 * @param lines
     *  the number of lines in the game
     * @param columns
     *  the number of columns in the game
   	 */
	public TicTacToeGame(int lines, int columns){

		this.level = 0;
		this.gameState = GameState.PLAYING;
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = 3;
		this.board = new CellValue[this.lines * this.columns];
		this.createEmptyBoard();
	}
	
	
	/**
	 * Constructor allowing to specify the number of lines
	 * and the number of columns for the game, as well as 
	 * the number of cells that must be aligned to win.
	 * 
   	 * @param lines
     *  the number of lines in the game
     * @param columns
     *  the number of columns in the game
     * @param sizeWin
     *  the number of cells that must be aligned to win.
  	 */
	public TicTacToeGame(int lines, int columns, int sizeWin){

		this.level = 0;
		this.gameState = GameState.PLAYING;
		this.lines = lines;
		this.columns = columns;
		this.sizeWin = sizeWin;
		this.board = new CellValue[this.lines * this.columns];
		this.createEmptyBoard();
	}



	/**
	 * Getter for the variable lines.
	 * 
	 * @return
	 * 	the value of lines
	 */
	public int getLines(){

		return this.lines;
	}
	
	/**
	 * Getter for the variable columns.
	 * 
	 * @return
	 * 	the value of columns
	 */
	public int getColumns(){

		return this.columns;
	}
	
	/**
	 * Getter for the variable level.
	 * 
	 * @return
	 * 	the value of level
	 */
	public int getLevel(){

		return this.level;
	}

  	/**
	 * Getter for the variable sizeWin.
	 * 
	 * @return
	 * 	the value of sizeWin
	 */
	public int getSizeWin(){

		return this.sizeWin;
	}
	
	/**
	 * Getter for the variable gameState.
	 * 
	 * @return
	 * 	the value of gameState
	 */
	public GameState getGameState(){

		return this.gameState;
	}


	/**
	 * Returns the cellValue that is expected next,
	 * in other word, which played (X or O) should 
	 * play next.
	 * This method does not modify the state of the
	 * game.
	 * 
	 * @return 
     * 	the value of the enum CellValue corresponding
     * to the next expected value.
  	 */
	public CellValue nextCellValue(){

		if (this.level % 2 != 0) {
			return CellValue.O;
		} else {
			return CellValue.X;
		}
	}


	/**
	 * Returns the current cellValue, in other words, 
	 * which was played (X or O).
	 * This method does not modify the state of the
	 * game.
	 *
	 * @return 
     * 	the value of the enum CellValue corresponding
     * to the current value.
  	 */
	public CellValue currentCellValue(){

		if (this.level % 2 != 0) {
			return CellValue.X;
		} else {
			return CellValue.O;
		}
	}


	/**
	 * Returns the value of the cell at index i.
	 * If the index is invalid, an error message is
	 * printed out. The behaviour is then unspecified
	 *
   	 * @param i
     * 	the index of the cell in the array board
     * @return 
     * 	the value at index i in the variable board.
  	 */
	public CellValue valueAt(int i) {

		return this.board[i];
	}


	/**
	 * This method is called when the next move has been
	 * decided by the next player. It receives the index
	 * of the cell to play as parameter.
	 * If the index is invalid, an error message is
	 * printed out. The behaviour is then unspecified
	 * If the chosen cell is not empty, an error message is
	 * printed out. The behaviour is then unspecified
	 * If the move is valid, the board is updated, as well
	 * as the state of the game.
	 * To faciliate testing, is is acceptable to keep playing
	 * after a game is already won. If that is the case, the
	 * a message should be printed out and the move recorded. 
	 * the  winner of the game is the player who won first
	 *
   	 * @param i
     * 	the index of the cell in the array board that has been 
     * selected by the next player
  	 */
	public void play(int i) {
		
		if ((i + 1) > (this.columns * this.lines) || (i + 1) <= 0){
			System.out.println("Value should be between 1 and " + (this.columns * this.lines));
		} else if (this.valueAt(i) != CellValue.EMPTY){
			System.out.println("This cell has already been played");
		} else {
			this.level++;
			this.board[i] = this.currentCellValue();
			this.setGameState(i);
		}
	}
	
	
	/**
	 * A helper method which updates the gameState variable
	 * correctly after the cell at index i was just set in
	 * the method play(int i).
	 * The method assumes that prior to setting the cell
	 * at index i, the gameState variable was correctly set.
	 * It also assumes that it is only called if the game was
	 * not already finished when the cell at index i was played
	 * (i.e. the game was playing). Therefore, it only needs to 
	 * check if playing at index i has concluded the game, and if
	 * set the outcome correctly.
	 * 
	 * @param i
	 * 	the index of the cell in the array board that has just 
	 * been set
	 */
	private void setGameState(int i){

		CellValue winner = this.valueAt(i);

		if (this.checkWin(i)){
			this.setWin(winner);
		} else if (this.checkDraw()){
			this.gameState = GameState.DRAW;
		}
		
	}


	/**
	 * Returns a String representation of the game matching
	 * the example provided in the assignment's description.
	 * 
	 * @return
	 * 	String representation of the game
	 */
	public String toString(){

		String display = "";
		CellValue[][] current_board = this.getCurrentBoard();

		for (int i = 0; i < this.lines; i++){
			for (int j = 0; j < this.columns; j++){
				if (current_board[i][j] == CellValue.EMPTY){
					if ((j + 1) % this.columns != 0){
						display += "   |";
					} else {
						display += "   ";
					}
				} else {
					if ((j + 1) % this.lines != 0){
						display += " " + current_board[i][j] + " |";
					} else {
						display += " " + current_board[i][j] + " ";
					}
				}
			}
			if (i != this.lines - 1){
				display += "\n-----------\n";
			}
		}
		return display;
	}
	
	
	/**
	 * Initializes an empty board for the start of the game.
	 */
	public void createEmptyBoard(){
		for (int i = 0; i < this.board.length; i++){
			this.board[i] = CellValue.EMPTY;
		}
	}
	
	
	/**
	 * Returns a 2D array represennting the current states of
	 * the cells (X, O, or EMPTY).
	 * 
	 * @return
	 * 	2D array containing current states of the cells
	 */
	public CellValue[][] getCurrentBoard(){

		CellValue[][] current_board = new CellValue[this.lines][this.columns];

		int cell_num = 0;
		for (int i = 0; i < this.lines; i++){
			for (int j = 0; j < this.columns; j++){
				current_board[i][j] = this.board[cell_num];
				cell_num++;
			}
		}
		return current_board;
	}


	/**
	 * Checks if X or O has won by calling other methods that
	 * check for vertical, horizontal, or diagonal wins.
	 * 
	 * @param i
	 * 	the index of the cell in the array board that has just been set
	 * @return
	 * 	boolean variable stating whether or not there's a win
	 */
	public boolean checkWin(int i){

		boolean result = false;

		CellValue[][] current_board = this.getCurrentBoard();

		if (this.horizontalCheck(current_board, i) || this.verticalCheck(current_board, i) || this.diagonalCheck(current_board, i)){
			result = true;
		}
		return result;
	}


	/**
	 * Checks every row in the current board for a horizontal win.
	 * 
	 * @param current_board
	 * 	2D array containing current states of the cells
	 * @param i
	 * 	the index of the cell in the array board that has just been set
	 * @return
	 * 	boolean stating whether or not there's a horizontal win
  	 */
	public boolean horizontalCheck(CellValue[][] current_board, int i){
		
		boolean result = false;
		int count = 0;
		for (int j = 0; j < this.lines; j++){
			for (int k = 0; k < this.columns; k++){
				if (current_board[j][k] == this.valueAt(i)){
					count += 1;
				} else {
					count = 0;
				}
				if (count == this.getSizeWin()){
					result = true;
				}
			}
			count = 0;
		}

		return result;
	}


	/**
	 * Checks every column in the current board for a vertical win.
	 * 
	 * @param current_board
	 * 	2D array containing current states of the cells
	 * @param i
	 * 	the index of the cell in the array board that has just been set
	 * @return
	 *  boolean stating whether or not there's a vertical win
	 */
	public boolean verticalCheck(CellValue[][] current_board, int i){

		boolean result = false;
		int count = 0;
		for (int j = 0; j < this.columns; j++){
			for (int k = 0; k < this.lines; k++){
				if (current_board[k][j] == this.valueAt(i)){
					count += 1;
				} else {
					count = 0;
				}
				if (count == this.getSizeWin()){
					result = true;
				}
			}
			count = 0;
		}
		return result;
	}
	
	/**
	 * A method that checks if the current board contains a
	 * diagonal win, either right to left or left to right.
	 * 
	 * @param current_board
	 * 	2D array containing current states of the cells
	 * @param i
	 * 	the index of the cell in the array board that has just been set
	 * @return
	 * 	boolean stating whether or not there's a diagonal win 
	 */
	public boolean diagonalCheck(CellValue[][] current_board, int i){

		boolean result = false;

		// Determine if it's possible for this board dimension to have a 
		// diagonal win with the current sizeWin
		if ((this.lines >= this.sizeWin) && (this.columns >= this.sizeWin)){
			int row = this.lines;
			int col = this.columns;
			int count = 0;
			
			// Traversing and checking diagonally from bottom left to top right.
			// Starting from the top left and going down, each time
			// starting from the first cell of each row
			for (int k = 0; k <= (row - 1); k++){
				int r = k;
				int c = 0;
				while ((c <= col - 1) && (r >= 0)){
					if (current_board[r][c] == this.valueAt(i)){
					count += 1;
					} else {
					count = 0;
					}
					if (count == this.sizeWin){
						result = true;
					}
					r = r - 1;
					c = c + 1;
				}
				count = 0;
			}

			// Traversing and checking diagonally from bottom left to top right.
			// Starting from the bottom left (skipping the first 
			// one as it has already been checked) and going right, 
			// each time starting from the last cell of each column
			for (int k = 1; k <= (col - 1); k++){
				int r = row - 1;
				int c = k;
				while ((c <= col - 1) && (r >= 0)){
					if (current_board[r][c] == this.valueAt(i)){
					count += 1;
					} else {
					count = 0;
					}
					if (count == this.sizeWin){
						result = true;
					}
					r = r - 1;
					c = c + 1;
				}
				count = 0;
			}
			
			// Traversing and checking diagonally from bottom left to top
			// right. Starting from the top right and going down, each time
			// starting from the last cell of each row.
			for (int k = 0; k <= (row - 1); k++){
				int r = k;
				int c = col - 1;
				while ((c >= 0) && (r >= 0)){
					if (current_board[r][c] == this.valueAt(i)){
					count += 1;
					} else {
					count = 0;
					}
					if (count == this.sizeWin){
						result = true;
					}
					r = r - 1;
					c = c - 1;
				}
				count = 0;
			}

			// Traversing and checking diagonally from bottom left to 
			// top right. Starting from the bottom right (skipping the 
			// first  one as it has already been checked) and going 
			// left, each time starting from the last cell of each column.
			for (int k = (col - 2); k >= 0; k--){
				int r = row - 1;
				int c = k;
				while ((c >= 0) && (r >= 0)){
					if (current_board[r][c] == this.valueAt(i)){
					count += 1;
					} else {
					count = 0;
					}
					if (count == this.sizeWin){
						result = true;
					}
					r = r - 1;
					c = c - 1;
				}
				count = 0;
			}
		}
		return result;
	}
	
	
	/**
	 * Sets the game state to XWIN or OWIN based on the winner 
	 * passed as parameter
	 * 
	 * @param winner
	 * 	the CellValue that has just won the game
	 */
	public void setWin(CellValue winner){

		if (winner == CellValue.X){
			this.gameState = GameState.XWIN;
		} else if (winner == CellValue.O){
			this.gameState = GameState.OWIN;
		}
	}
	
	
	/**
	 * Checks if the game is a draw (board is full with no winners).
	 *
	 * @return
	 * 	boolean indicating whether or not the game is a draw 
	 */
	public boolean checkDraw(){

		boolean draw = true;

		for (int i = 0; i < this.board.length; i++){
			if (this.board[i] == CellValue.EMPTY){
				draw = false;
			}
		}

		return draw;
	}
}