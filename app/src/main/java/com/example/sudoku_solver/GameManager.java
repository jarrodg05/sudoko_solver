package com.example.sudoku_solver;

public class GameManager
{
	private static final int GRID_SIZE = 9;

	private Board board;

	public GameManager()
	{

	}

	public void startGame()
	{
		this.board = new Board( GRID_SIZE );
		this.board.initBoard();
		this.board.logBoard();
	}

	public Board getBoard()
	{
		return this.board;
	}

	public void makeMove( int row, int col, int number )
	{
		this.board.setTile( row, col, number );
	}

	public boolean hasWon()
	{
		for( int i = 0; i < GRID_SIZE; i++ )
		{
			for( int j = 0; j < GRID_SIZE; j++ )
			{
				if (!this.board.rowContains(i, j+1) )
					return false;
				if (!this.board.colContains(i, j+1))
					return false;
			}
		}

		return true;
	}
}
