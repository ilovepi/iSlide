package edu.csun.group2.islide.engine;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
//import java.util.ArrayList;


public class GameBoard extends Board {
	
	public ArrayDeque<Pair> solution_stack;	
	private HashMap<Integer, Board> hash;
	private int solution_code;

	public GameBoard(int size) {
		super(size);
		solution_code = this.ary.hashCode();
		Random rand = new Random();
		rand.setSeed(rand.nextInt(1000));// need to seed the number generator to make it deterministic.
		int solution_length = rand.nextInt(1000);
		hash = new HashMap<Integer, Board>(solution_length);
		solution_stack = new ArrayDeque<Pair>(solution_length);
		
		
		// make a copy of the solution board 
		// making a new board an initializing it is expensive, this copy is much cheaper
		Board board = new Board(this);
		
		ArrayList<Pair> temp;
		Pair p;
		// until the # of moves is full
		while(solution_stack.size() < solution_length)
		{
							
			temp = adjacent_moves(board);
			// if there were no valid moves, go back and look for more valid moves (backtrack)
			while(temp.isEmpty())
			{
				solution_stack.pop();					
				temp = adjacent_moves(solution_stack.peek().board);
			}
			
			p = temp.get(rand.nextInt(temp.size()));// pick a random move
			board = p.board;			
			
			hash.put(board.hashCode(), board);// add the new move to the hash
			solution_stack.push(p);			
		}
		
		
		// our actual board is in the solution state, 
		// so replay the move list we just made in proper order
		ArrayDeque<Pair> s = solution_stack.clone();
		solution_stack.clear();
		while(!s.isEmpty())		
			this.move(s.pollLast().index);
		
		
	}

	@SuppressWarnings("unchecked")
	public GameBoard(GameBoard other) {
		super(other);
		solution_stack = other.solution_stack.clone();
		hash = (HashMap<Integer, Board>) other.hash.clone();		
	}
	
	
	/**
	 * 
	 * @param board
	 * @return The list of moves not yet seen;
	 */
	private ArrayList<Pair> adjacent_moves(Board board)
	{
		
		ArrayList<Pair> ret = new ArrayList<Pair>(4); 
		int empty = board.empty;		
		int index;
		{
			Board temp = new Board(board);
			index = empty +1;
			if(temp.move(index) && !hash.containsKey(temp.hashCode()))
				ret.add(new Pair(empty, temp));			
		}
		
		{
			Board temp = new Board(board);
			index = empty - 1;
			if(temp.move(index) && !hash.containsKey(temp.hashCode()))
				ret.add(new Pair(empty, temp));
		}
		
		{
			Board temp = new Board(board);
			index = empty + temp.width;
			if(temp.move(index) && !hash.containsKey(temp.hashCode()))
				ret.add(new Pair(empty, temp));
		}
		
		{
			Board temp = new Board(board);
			index = empty - temp.width;
			if(temp.move(index) && !hash.containsKey(temp.hashCode()))
				ret.add(new Pair(empty, temp));
		}
		
		return ret;
		
	}

	public boolean move(int index)
	{
		int e = empty;
		if(super.move(index))// if the move was successful
		{
			Board b = new Board(this);
			Pair p = new Pair(e, b);// make a new pair to put in the stack
			// if the move is already in the solution, get rid of don't add it, 
			// and get rid of extra moves
			if(solution_stack.contains(p))
			{
				while(solution_stack.peek() != null && !solution_stack.peek().Equals(p))
					solution_stack.pop();				
			}
			else
				solution_stack.push(p);
			return true;
		}		
		return false;		
	}
	
	/**
	 * 
	 * @return the index of the next move in the solution
	 */
	public int hint()
	{
		return solution_stack.peek().index;
	}
	
	public int solve()
	{
		Pair p = solution_stack.pop();
		super.move(p.index);
		return p.index;
	}
	
	public boolean solved()
	{
		return this.ary.hashCode() == solution_code;
	}
	
	
	
}
