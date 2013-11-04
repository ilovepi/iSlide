package edu.csun.comp380.group2.islide;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SolverTest {

	Board aBoard;
	Move start;
	Solver s;
	
	public SolverTest() {
		aBoard = new Board(4);
		start = new Move(aBoard);
		s = new Solver(start);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSolver() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSolve() {
		Move temp = new Move(new Board(4));
		for (byte i = 0; i < temp.board_size; ++i)
			temp.ary.set(i,i);
		temp.empty = 0;
		
		ArrayList<Integer> temp_solution = (ArrayList<Integer>) temp.ary.clone();

		ArrayList<Move> solution = s.solve(temp);
		Move last = solution.get(solution.size()-1);
		assertTrue(solution.size() < 100);				
		assertTrue(last.ary.equals(temp_solution));
		
		System.out.println("Is the starting move solvable???: " + start.solvable());
		solution = s.solve(start);
		
		last = solution.get(solution.size()-1);
		assertTrue(solution.size() < 100);		
		assertTrue(last.ary.equals(s.solution_state));
		
		//fail("Solver is currently Broken");
	}

	@Test
	public void testAdjacent_moves() {
		//s.adjacent_moves(start);
		
		//fail("Not yet implemented");
	}

}
