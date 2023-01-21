package com.example.sudoku_solver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

import java.util.logging.Logger;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GridFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GridFragment extends Fragment
{

	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private static final String ARG_GRID_SIZE = "gridSize";
	private static final Logger logger = Logger.getLogger( "Grid_Fragment" );

	private int gridSize;
	private Button[][] buttons;

	public GridFragment()
	{
		// Required empty public constructor
	}

	/**
	 * Use this factory method to create a new instance of
	 * this fragment using the provided parameters.
	 *
	 * @param gridSize square size of the grid to been shown
	 * @return A new instance of fragment GridFragment.
	 */
	public static GridFragment newInstance( int gridSize )
	{
		GridFragment fragment = new GridFragment();
		Bundle args = new Bundle();
		args.putInt( ARG_GRID_SIZE, gridSize );
		fragment.setArguments( args );
		return fragment;
	}

	@Override
	public void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		if( getArguments() != null )
		{
			this.gridSize = getArguments().getInt( ARG_GRID_SIZE );
		}
	}

	@Override
	public View onCreateView( LayoutInflater inflater, ViewGroup container,
	                          Bundle savedInstanceState )
	{
		// Inflate the layout for this fragment

		View view = inflater.inflate( R.layout.fragment_grid, container, false );
		GridLayout layout = view.findViewById( R.id.grid_layout );
		this.buttons = new Button[gridSize][gridSize];

		for( int i = 0; i < gridSize; i++ )
		{
			for( int j = 0; j < gridSize; j++ )
			{
				Button button = new Button( getContext() );
				GridLayout.LayoutParams params = new GridLayout.LayoutParams( );
				params.height = 0;
				params.width = 0;
				params.rowSpec = GridLayout.spec( i, 1, 1 );
				params.columnSpec = GridLayout.spec( j, 1, 1 );
				layout.addView( button, params );

				this.buttons[i][j] = button;
			}
		}

		MainActivity parent = (MainActivity)getActivity();
		Board board = parent.getBoardState();
		updateBoardState( board );

		return view;
	}

	private void updateBoardState( Board board )
	{
		for( Tile tile : board.getTiles() )
		{
			Button button = this.buttons[tile.getRow()][tile.getCol()];
			button.setText( Integer.toString(tile.getNumber()) );
		}
	}

}