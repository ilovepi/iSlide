package edu.csun.group2.islide.engine;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameBoardTest {
GameBoard b, q;
	
	
	@Before
	public void setUp() throws Exception {
		b = new GameBoard(3);
		q = new GameBoard(3);
		for(int i = 0; i < q.ary.size(); ++i)
		{
			q.ary.set(i,  (byte)i);
		}
		q.empty = 0;
		q.solution_stack.clear();		
	}

	@Test
	public void testMove() {
		fail("Not yet implemented");
	}

	@Test
	public void testGameBoardInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGameBoardGameBoard() {
		fail("Not yet implemented");
	}

	@Test
	public void testHint() {
		q.move(1);
		assertTrue("Failed to solve the board", q.hint() == 0);
	}

	@Test
	public void testSolve() {
		q.move(1);
		q.solve();
		assertTrue("Failed to solve the board", q.solved());
	}

	@Test
	public void testSolved() {
		assertTrue("Failed to solve the board", q.solved());
	}

}
