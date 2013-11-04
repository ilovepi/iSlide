package edu.csun.comp380.group2.islide;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MoveTest {

	Board start;
	Move tester;
	
	public MoveTest() {
		start = new Board(4);
		tester = new Move(start);
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMove() {
		assertTrue("Failed to set parent in constructor", tester.parent == null);
		assertTrue("Failed to initialize Board", tester.ary.equals(start.ary));
		assertTrue("Failed to initialize Board", tester.ary.hashCode() == start.ary.hashCode());
	}
	
	@Test
	public void testMoveCopy(){
		int index = 0;
		while(!tester.move(index))
			index++;
		
		Move mv = new Move(tester);
		
		assertTrue("Failed to set parent in constructor", mv.parent == tester);
		assertTrue("Failed to initialize Board", mv.ary.equals(tester.ary));
		assertTrue("Failed to initialize Board", mv.ary.hashCode() == tester.ary.hashCode());
		
		assertFalse("Failed to set parent in constructor", mv.parent == start);
		assertFalse("Failed to initialize Board", mv.ary.equals(start.ary));
		assertFalse("Failed to initialize Board", mv.ary.hashCode() == start.ary.hashCode());
		
	}
	
	
	@Test
	public void testMoveDeepCopy(){
		int index = 0;
		while(!tester.move(index))
			index++;
		
		Move mv = new Move(tester, true);
		
		assertTrue("Failed to set parent in constructor", mv.parent == tester.parent);
		assertTrue("Failed to set parent in constructor", mv.parent == null);
		assertTrue("Failed to initialize Board -- g", mv.g == tester.g);
		assertTrue("Failed to initialize Board -- h", mv.h == tester.h);
		assertTrue("Failed to initialize Board -- ary", mv.ary.equals(tester.ary));
		assertTrue("Failed to initialize Board -- ary", mv.ary.hashCode() == tester.ary.hashCode());
		
		assertFalse("Failed to initialize Board -- ary", mv.ary.equals(start.ary));
		assertFalse("Failed to initialize Board -- ary", mv.ary.hashCode() == start.ary.hashCode());
		
	}
	
	
	@Test
	public void test_move(){
		Move temp = new Move(tester);
		int test_g = temp.g;
		int index = 0;
		while(!temp.move(index))
			index++;
		assertFalse("Failed to increment g", temp.g == test_g);
		assertFalse("Failed to make a move properly", temp.ary.equals(tester.ary));
	}
	

	@Test
	public void testEval() {
		Move temp = new Move(tester);
		tester.eval();
		temp.eval();
		assertTrue("Eval failed to be consistent between copies", temp.h == tester.h);
		assertTrue("Failed copy in eval test", temp.ary.equals(tester.ary));
		assertTrue("Failed copy in eval test", temp.ary.hashCode() == tester.ary.hashCode());
		
		
		for (byte i = 0; i < temp.board_size; ++i)
			temp.ary.set(i,i);
		temp.empty = 0;
		temp.eval();
		assertTrue("eval() failed to find success", temp.h == 0);
		temp.move(4);
		assertTrue("eval() failed to find success", temp.h == 2);		
	}
	
	

}
