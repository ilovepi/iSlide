package edu.csun.comp380.group2.islide;

import java.util.*;

public class Solver {
	Move start_state;
	PriorityQueue<Move> open_list;
	ArrayList<Move> solution_path;
	HashMap<Integer, Move> used;
	HashMap<Integer, Move> closed_list;
	ArrayList<Integer> solution_state; 
	MoveComparator comp;
	
	
	Solver(Board _board){
		start_state = new Move(_board);
		
		
	}
	
	

	ArrayList<Move> solve(Move start){
		
		solution_state = new ArrayList<Integer>();
		for(int i = 0; i < start.board_size; ++i)
			solution_state.add(i);
		comp = new MoveComparator();
		open_list = new PriorityQueue<Move>(1, comp );
		closed_list = new HashMap<Integer, Move>();
		used = new HashMap<Integer, Move>();
		solution_path = new ArrayList<Move>();
		
		
		Move current = start;
		 //current.ary.hashCode() != solution_state.hashCode()
		used.put(current.hashCode(), current);
		
		//current.print();
		while(current.h != 0 ){
			
			closed_list.put(current.hashCode(), current);
			ArrayList<Move> moves = adjacent_moves(current);
			//.println("Moves\n");
			for(Move move : moves)
			{
				
				
				if(move.h == 0)
				{
					System.out.println("Found the solution!!!");
					used.put(move.hashCode(), move);
					open_list.add(move);
					break;
				}
				Move temp = used.get(move.hashCode()); 
				
				//if we've never seen this move, add it to the open list
				if(temp == null)
				{
					//System.out.println("Adding new move...");
					used.put(move.hashCode(),  move);
					open_list.add(move);
					//System.out.println("Open list size: " + open_list.size());
					//System.out.println("Heuristic value: " +(move.h +move.g));				
					//move.print();
				}
				else if( (temp.h + temp.g) > (move.h + move.g) )
				{
					//System.out.println("Updating better move...");
					// we have a better heuristic value, so remove the old one from the open_list
					used.remove(move);
					open_list.remove(temp);
					
					//and add the new one
					used.put(move.hashCode(), move);
					open_list.add(move);
					//System.out.println("Open list size: " + open_list.size());
					//System.out.println("Heuristic value: " +(move.h +move.g));				
					//move.print();
				}
 
			}//end for
			
			
			// get the item with the lowest h+g value
			//current.print();
			//System.out.println("Open list size: " + open_list.size());
			Move temp = open_list.poll();
			if (temp == null)
			{
				System.out.println("Open list size: " + open_list.size());
				System.out.println("Hash size: " + used.size());
				System.out.println("h_val:" + current.h);
				System.out.println("Number of previous moves: " + current.g);
				System.out.println("Heuristic value: " + (current.h +current.g));
				current.print();
				System.out.println("Is it Solvable: " +current.solvable());
			}
			current = temp;
			
			/*System.out.println("New h:");
			System.out.println("Heuristic value: " +current.h +current.g);
			current.print();*/
			
		}
	
		
		System.out.println("Open list size: " + open_list.size());
		System.out.println("Hash size: " + used.size());
		System.out.println("h_val:" + current.h);
		System.out.println("Heuristic value: " +(current.h +current.g));
		current.print();
		
		solution_path.clear();
		if(current.parent == null)
			solution_path.add(current);
		
		while(current.parent != null){
			solution_path.add(current);
			current = closed_list.get(current.parent.hashCode());
		}
		Collections.reverse(solution_path);
		return (ArrayList<Move>)solution_path.clone();	
		
	}
	
	
	
	ArrayList<Move> adjacent_moves(Move board)
	{
		
		ArrayList<Move> ret = new ArrayList<Move>(4); 
		int empty = board.empty;
		
		{
			Move temp = new Move(board, false);
			if(temp.move(empty +1))
				ret.add(temp);			
		}
		
		{
			Move temp = new Move(board, false);
			if(temp.move(empty - 1))
				ret.add(temp);
		}
		
		{
			Move temp = new Move(board, false);
			if(temp.move(empty + temp.width))
				ret.add(temp);
		}
		
		{
			Move temp = new Move(board, false);
			if(temp.move(empty - temp.width))
				ret.add(temp);
		}
		return ret;
		
	}

}
