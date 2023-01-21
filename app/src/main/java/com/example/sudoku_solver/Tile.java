package com.example.sudoku_solver;

import java.util.ArrayList;
import java.util.Collection;

public class Tile
{
	private int number = -1;
	private ArrayList<Integer> options ;

	private int row;
	private int col;

	public Tile( int optionsSize, int row, int col )
	{
		this.options = new ArrayList<>();
		for( int i = 1; i <= optionsSize; i++ )
		{
			this.options.add(i);
		}

		this.row = row;
		this.col = col;
	}

	boolean isSet()
	{
		return this.number != -1;
	}

	public void setNumber( int number )
	{
		this.number = number;
	}

	public int getNumber()
	{
		return this.number;
	}

	public void removeOption( int option )
	{
		this.options.remove( option );
	}

	public void removeOptions( Collection<Integer> options )
	{
		this.options.removeAll( options );
	}


	public Collection<Integer> getOptions()
	{
		return this.options;
	}

	public int getRow()
	{
		return this.row;
	}

	public int getCol()
	{
		return this.col;
	}

	@Override
	public String toString()
	{
		String extra = this.options.size() == 1 ? "(" + this.options.get(0) + ")" : "";
		return row + " " + col + " " + number + extra;
	}

}
