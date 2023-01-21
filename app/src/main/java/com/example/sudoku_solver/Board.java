package com.example.sudoku_solver;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Board
{
	private static final Logger logger = Logger.getLogger( "Board" );

	private Tile[][] tiles;
	private HashSet<Integer>[] rows;
	private HashSet<Integer>[] cols;
	private int gridSize;

	public Board( int gridSize )
	{
		tiles = new Tile[gridSize][gridSize];
		for( int i = 0; i < gridSize; i++ )
		{
			for( int j = 0; j < gridSize; j++ )
			{
				tiles[i][j] = new Tile( gridSize, i, j );
			}
		}

		rows = new HashSet[ gridSize ];
		cols = new HashSet[ gridSize ];
		for( int i = 0; i < gridSize; i++ )
		{
			rows[i] = new HashSet<>();
			cols[i] = new HashSet<>();
		}

		this.gridSize = gridSize;
	}

	public Collection<Tile> getTiles()
	{
		return Arrays.stream( tiles ).flatMap( Arrays::stream ).collect( Collectors.toList());
	}

	public void setTile( int row, int col, int number )
	{
		this.tiles[row][col].setNumber( number );
		this.rows[row].add( number );
		this.cols[col].add( number );

	}

	public void initBoard()
	{
		String board = "" +
				"9 6  1 4 " +
				"7 129  6 " +
				"4 28 63  " +
				"    2 98 " +
				"6       2" +
				" 94 8    " +
				"  37 84 9" +
				" 4  137 6" +
				" 6 9  1 8";

		for( int i = 0; i < board.length(); i++ )
		{
			char c = board.charAt( i );
			if( c == ' ' )
				continue;

			int number = Integer.parseInt( String.valueOf( c ) );
			setTile( i/gridSize, i%gridSize, number );
		}
	}

	public void logBoard()
	{
		for( int i = 0; i < gridSize; i++ )
		{
			for( int j = 0; j < gridSize; j++ )
			{
				if( tiles[i][j].isSet() )
				{
					System.out.print( "" + tiles[i][j].getNumber() );
				}
				else
				{
					System.out.print( " " );
				}

				if( j == gridSize - 1 )
					System.out.println();
			}
		}
	}



	public Tile getTile( int row, int col )
	{
		return this.tiles[row][col];
	}

	public boolean rowContains( int row, int number )
	{
		return this.rows[row].contains( number );
	}

	public boolean colContains( int col, int number )
	{
		return this.cols[col].contains( number );
	}

	public boolean squareContains( int row, int col, int number )
	{
		int rowMin = row / 3;
		int colMin = col / 3;

		for( int i = 0; i < 3; i ++ )
		{
			for( int j = 0; j < 3; j++ )
			{
				if( tiles[rowMin*3+i][colMin*3+j].getNumber() == number )
				{
					return true;
				}
			}
		}

		return false;
	}
}
