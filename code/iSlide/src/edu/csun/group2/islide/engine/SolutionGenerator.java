package edu.csun.group2.islide.engine;

import java.util.*;


public class SolutionGenerator
{
	class Pair
	{
		public Board board;
		public int index;
		
		Pair(int _index, Board _board)
		{
			board = new Board(_board);
			index = _index;
		}
		
	}	
	 
	private Random rand;
	private Board solution;
	private String db;
	private HashMap<Integer, Board> hash;

	/**
	 * [Constructor responsible for generating a number of pre computed solutions to the n-puzzle]
	 * @param  num_solutions [number of solutions to generate for dB]
	 * @param  size          [the size of the puzzle to generate solutions for,  (3x3), (4x4), or (5x5)]
	 * @param  dbname        [placeholder variable for the db we're generating]
	 * @return               [none, this is a constructor]
	 */
	public SolutionGenerator(int num_solutions, int size,  String dbname)
	{
		rand = new Random();
		//seed = rand.nextInt(num_solutions);
		int seed = 0x58FA87;
		
		rand.setSeed(seed);
		solution = new Board(size);
		db = dbname;
		
		
		for(byte i = 0; i < solution.ary.size(); ++i)
			solution.ary.set(i, i);

		// dB = new SQLdB 	// make a SQLite DB
	 
		int id, solution_length;
		for(int i = 0; i < num_solutions; ++i)
		{			
			solution_length = rand.nextInt(50-18) + 18;
			GenerateSolution(size, i, solution_length);
		}

	}

	public void GenerateSolution(int size, int solution_id, int solution_length)
	{
		rand.setSeed(solution_id);
		hash = new HashMap<Integer, Board>(solution_length);
		Stack<Pair> move_list = new Stack<Pair>();
		// make a copy of the solution board 
		// making a new board an initializing it is expensive, this copy is much cheaper
		Board board = new Board(solution);
		
		ArrayList<Pair> temp;
		Pair p;
		// until the # of moves is full
		while(move_list.size() < solution_length)
		{
							
			temp = adjacent_moves(board);
			// if there were no valid moves, go back and look for more valid moves (backtrack)
			while(temp.isEmpty())
			{
				move_list.pop();					
				temp = adjacent_moves(move_list.peek().board);
			}
			
			p = temp.get(rand.nextInt(temp.size()));// pick a random move
			board = p.board;			
			
			hash.put(board.hashCode(), board);// add the new move to the hash
			move_list.push(p);
			
		}
		
		writeToDB(db, solution_id, move_list);
		
	}
	
	/**
	 * 
	 * @param board
	 * @return The list of moves not yet seen;
	 */
	ArrayList<Pair> adjacent_moves(Board board)
	{
		
		ArrayList<Pair> ret = new ArrayList<Pair>(4); 
		int empty = board.empty;		
		int index;
		{
			Board temp = new Board(board);
			index = empty +1;
			if(temp.move(index) && !hash.containsKey(temp.hashCode()))
				ret.add(new Pair(index, temp));			
		}
		
		{
			Board temp = new Board(board);
			index = empty - 1;
			if(temp.move(index) && !hash.containsKey(temp.hashCode()))
				ret.add(new Pair(index, temp));
		}
		
		{
			Board temp = new Board(board);
			index = empty + temp.width;
			if(temp.move(index) && !hash.containsKey(temp.hashCode()))
				ret.add(new Pair(index, temp));
		}
		
		{
			Board temp = new Board(board);
			index = empty - temp.width;
			if(temp.move(index) && !hash.containsKey(temp.hashCode()))
				ret.add(new Pair(index, temp));
		}
		
		return ret;
		
	}
	
	/**
	 * Writes the solution path for the n-puzzle to the database
	 * @param db the db to write to
	 * @param seed_id the seed used to start the number generator
	 * @param move_list the list of moves, with the index of the move that got them there. 
	 */
	public void writeToDB(String db, int seed_id, Stack<Pair> move_list)
	{
		// write the data to a record in the db
		
		//Write the id
		
		//write the index_list
		
		//write the move_list
		
	}

};



