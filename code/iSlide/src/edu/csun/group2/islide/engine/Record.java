package edu.csun.group2.islide.engine;

public class Record {
	
	//constructors
	Record(long _id, long sol_id, int move_num, int _move_index, Board _board)
	{
		id = _id;
		solution_id = sol_id;
		move_number = move_num;
		move_index = _move_index;
		board = new Board(board);
	}	
	
	// member functions
	@Override
	public String toString()
	{
		return "id: " + id +", solution id: "+ solution_id + ", move#: "+move_number+
				", move tile at index: "+move_index; 
	}
	
	//Data members
	long id;
	long solution_id;
	int move_number;
	int move_index;
	Board board;

}
