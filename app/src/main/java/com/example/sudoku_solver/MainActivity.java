package com.example.sudoku_solver;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

	private GameManager gameManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		gameManager = new GameManager();
		gameManager.startGame();

		Solver solver = new Solver( gameManager );
		solver.solve();

		gameManager.getBoard().logBoard();


		Bundle bundle = new Bundle();
		bundle.putInt("gridSize", 9);

		getSupportFragmentManager().beginTransaction()
				.setReorderingAllowed(true)
				.add(R.id.main_container, GridFragment.class, bundle)
				.commit();

	}

	public Board getBoardState()
	{
		return gameManager.getBoard();
	}

}