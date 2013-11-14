package edu.csun.group2.islide.engine;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	
	public Board test_board;
	public BoardTest() {
		test_board = new Board(4);
	}

	@Test
	public void testHashCode() {
		assertTrue("Failed to properly Hash Boards", test_board.hashCode() == test_board.ary.hashCode());
		assertTrue("Failed to properly Hash Boards", test_board.equals(test_board));		
	}

	@Test
	public void testBoardShort() {
		assertTrue("Failed to properly set up the test board", test_board.ary.indexOf(0) == test_board.empty);
		assertTrue("Failed to crete a solvable board!", test_board.solvable());
	}

	@Test
	public void testBoardBoard() {
		Board new_board = test_board;
		assertEquals("Failed to properly coppy Boards", test_board, new_board);
		assertTrue("Failed to crete a solvable board!", new_board.solvable());
	}

	@Test
	public void testMove() {
		Board temp = new Board(4);
		for (byte i = 0; i < temp.board_size; ++i)
			temp.ary.set(i,i);
		temp.empty = 0;
		//System.out.println("solvability issues <----------");
		//System.out.println(temp.solvable());
		//temp.print();
		assertTrue("Failed to properly set up the test board", temp.ary.indexOf(0) == temp.empty);
		assertFalse("Failed to properly disallow a move -- null_move", temp.move(temp.empty));
		assertFalse("Failed to properly disallow a move -- invalid edge", temp.move(temp.empty-1));
		assertFalse("Failed to properly disallow a move -- invalid not adjacent", temp.move(temp.empty + 9));
		assertTrue("Failed to properly make a move -- vertical", temp.move(temp.empty + 4));
		
		assertFalse("Failed to properly disallow a move -- opposite edge", temp.move(temp.empty -1));
		assertTrue("Failed to properly make a move -- horizontal", temp.move(temp.empty+1));
		//temp.print();
		//System.out.println(temp.solvable());
		assertTrue("Failed to create a solvable board!", Board.solvable(temp));
		//temp.solvable();
		//temp.print();
		//System.out.println("Done <---------------");
	}

	@Test
	public void testEqualsBoard() {
		Board new_board = test_board;
		assertTrue("Failed to properly compare Boards -- compare of a copy", test_board.equals(new_board));
		assertTrue("Failed to properly compare Boards -- self compare", test_board.equals(test_board));
		assertTrue("Failed to properly compare Boards -- width", test_board.width == new_board.width);
		assertTrue("Failed to properly compare Boards -- ary", test_board.ary == new_board.ary);
		assertTrue("Failed to properly compare Boards -- board_size", test_board.board_size == new_board.board_size);	
		
		Board temp = new Board(4);
		for (int i = 0; i < temp.board_size; ++i)
			temp.ary = (ArrayList<Byte>) test_board.ary.clone();
		temp.empty = test_board.empty;
		assertTrue("Failed to properly compare Boards -- compare of a handmade copy", test_board.equals(temp));
		
		assertTrue("Failed to properly compare Boards -- width", test_board.width == temp.width);
		assertTrue("Failed to properly compare Boards -- ary", test_board.ary.equals(temp.ary));
		assertTrue("Failed to properly compare Boards -- board_size", test_board.board_size == temp.board_size);
		
	}
	
	@Test
	public void testSolvabiltiy(){
		Board temp = new Board(4);
		for (byte i = 0; i < temp.board_size; ++i)
			temp.ary.set(i,i);
		temp.empty = 0;
		assertTrue(Board.solvable(temp));
	
	}

}
