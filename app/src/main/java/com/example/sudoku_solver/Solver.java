package com.example.sudoku_solver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Solver
{
	private static Logger logger = Logger.getLogger( "Solver" );

	GameManager gameManager;


	public Solver( GameManager gameManager )
	{
		this.gameManager = gameManager;
	}

	public void solve()
	{
		while( !gameManager.hasWon() )
		{
			boolean changeMade = performPass() || checkTiles();
			if( !changeMade )
			{
				logger.warning( "Failed to solve" );
				break;
			}
		}

		if( gameManager.hasWon() )
		{
			logger.info( "Success!" );
		}
	}

	private boolean performPass()
	{
		Collection<Tile> tiles = this.gameManager.getBoard().getTiles();
		List<Tile> unknownTiles = tiles.stream()
				                       .filter( t -> !t.isSet() )
				                       .collect( Collectors.toList());

		boolean changed = false;

		for( Tile tile : unknownTiles )
		{
			ArrayList<Integer> toRemove = new ArrayList<>();
			for( int option : tile.getOptions() )
			{
				if( gameManager.getBoard().colContains( tile.getCol(), option ) ||
					gameManager.getBoard().rowContains( tile.getRow(), option ) ||
					gameManager.getBoard().squareContains( tile.getRow(), tile.getCol(), option) )
				{
					toRemove.add( option );
				}
			}
			tile.removeOptions( toRemove );

			if( !toRemove.isEmpty() )
				changed = true;
		}

		return changed;
	}

	private boolean checkTiles()
	{
		Collection<Tile> tiles = this.gameManager.getBoard().getTiles();
		List<Tile> knownTiles = tiles.stream()
				                     .filter( t -> !t.isSet() )
				                     .filter( t -> t.getOptions().size() == 1 )
				                     .collect( Collectors.toList());

		for( Tile tile : knownTiles )
		{
			gameManager.makeMove( tile.getRow(), tile.getCol(), tile.getOptions().iterator().next() );
		}

		return !knownTiles.isEmpty();
	}
}
