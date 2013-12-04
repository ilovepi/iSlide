package edu.csun.group2.islide.engine;

class Pair
{
	public Board board;
	public int index;
	
	Pair(int _index, Board _board)
	{
		board = new Board(_board);
		index = _index;
	}
	
	public boolean Equals(Object o)
	{
		if(this == o)
			return true;
		else
		{
			Pair p = (Pair)o;
			if (p.index == this.index  && p.board.hashCode() == this.board.hashCode())
				return true;
		}
		
		return false;
	}
	
}	
