package edu.csun.group2.islide.engine;
import java.util.ArrayList;
import java.util.Collections;


public class Board {
	
	ArrayList<Byte> ary;//! the representation of the game board
	byte empty;//! the index of the empty tile
	byte width;// dimension of the board (N)
	byte board_size;//! total number of tiles (NxN)
	
	/** /brief Constructor for the board class
	 * 	Creates an NxN board to play a sliding puzzle game with
	 * @param size the width and height of the board, dimension N in an NxN board
	 */
	public Board(int size){

		width = (byte) size;
		board_size = (byte) (width*width);
		ary = new ArrayList<Byte>(board_size);
		
		for(byte i =0; i < board_size; ++i )
			ary.add(i);

		java.util.Collections.shuffle(ary);
		empty = (byte) ary.indexOf(0);

		
		while(!solvable())
		{
			int row =empty/width;
			int next = (row +1) * width;
			int safe_index = next%board_size;			
			Collections.swap(ary, (safe_index ), (safe_index +1) );
			empty = (byte) ary.indexOf(0);
		}
			
	}
	
	//! copy constructor
	public Board(Board other)
	{
		width = other.width;
		board_size = other.board_size;
		empty = other.empty;
		//ary.addAll(other.ary);
		ary = (ArrayList<Byte>)other.ary.clone();
	}
		
	/** /brief Determines if the puzzle is solvable
	 * @return boolean -true if the puzzle is solvable, otherwise false
	 * 
	*/
	public static boolean solvable(Board board){
		int width = board.width;
		int board_size = board.board_size;
		int empty = board.empty;
		ArrayList<Byte> ary =(ArrayList<Byte>)board.ary.clone();
		
		//do we have an even number of inversions?
		boolean even_inversions = true;
		
		//is the width of the board even?
		int row = width - (empty/width);
		boolean even_width = (width & 0x1) == 0;
		/*System.out.println("Empty: " + empty);
		System.out.println("Empty Row from Bottom: "+ row);
		System.out.println("even width: " + even_width);*/
		
		// is the blank in an even row?
		boolean odd_blank = (row & 0x1) != 0;
		//System.out.println("Odd blank row from bottom: " + odd_blank);
		boolean even_blank = !odd_blank;
		
		int inversions = 0;
		
		for(int i = 0; i < board_size; ++i)
		{
			int x = ary.get(i);
			if(x == 0)
				continue;
			for(int j = i+1; j < board_size; ++j)
			{
				int y =ary.get(j);
				if( (y !=0) && (x > y) )
					inversions++;
			}
		}
		
		//if # of inversions is odd
		if((inversions & 0x1) != 0 )
			even_inversions = false;
		//System.out.println("odd inversions: " + !even_inversions);
		boolean odd_inversions = !even_inversions;
		//System.out.println("#inversions: " + inversions);
		//board.print();
		
		// odd width and even inv
		if(!even_width && even_inversions) 
				return true;
		
		// even width, odd m-i and odd inv
		if(even_width &&  odd_blank && odd_inversions)
			return true;
		
		// even width, even m-i and even inv
		if(even_width && even_blank && even_inversions)
			return true;
		
		return false;
	} 
	
	public boolean solvable(){
		return Board.solvable(this);
	}
	
	/**
	 * /brief Makes a move for a sliding puzzle board
	 * @param index
	 * @return returns true if the move was made, else false
	 */
	public boolean move(int index){
		
		if(index < board_size && index >= 0 && index != empty)
		{
			int diff = Math.abs(index - empty);
			
			if( ( (index%width == empty%width) && ( diff/width == 1 ) ) 
					|| ( (diff ==1) && empty/width == index/width))//(Math.abs((empty%width) - (index%width)) != (width-1)) ) )
			{
				Collections.swap(ary, empty, index);
				empty = (byte) index;
				return true;
			}
			return false;			
		}
		//consider throwing exception
		return false;
	}
	
	public int hashCode(){
		return ary.hashCode();
	}
	
	boolean equals(Board other){
		
		if(ary.equals(other.ary))
			return true;
		
		return false;		
	}
	
	void print(){
		
		for(int i = 0; i < board_size; ++i)
		{
			System.out.print(ary.get(i) + " ");
			if((i+1)%width == 0)
				System.out.println();
		}
		System.out.println();
	}
	
	
}
