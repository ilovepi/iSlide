package edu.csun.comp380.group2.islide;

public class Move extends Board implements Comparable<Move>{

	int h;//! the Manhattan distance heuristic
	int g;//! how many moves have gone before
	
	Move parent;
	
	public Move(Board _parent) {
		super(_parent);
		parent = null;
		eval();
		g= 0;
	}
	
	public Move(Move other) {
		super(other);
		parent = other.parent;
		h = other.h;
		g = other.g;
		
	}
	
	public Move(Move other, boolean deep_copy) {
		super(other);
		if(deep_copy)
			parent = other.parent;
		else
			parent = other;
		h = other.h;
		g = other.g;		
	}
	
	@Override
	public boolean move(int index)
	{
		if( super.move(index))
		{
			++g;
			eval();
			return true;
		}
		return false;		
	}

	
	/**
	 *  /brief Evaluates the heuristic value of the configuration of board
	 * 
	 */
	void eval(){
		h = 0;
		int num;
		for(int i = 0; i < board_size; ++i)
		{
			num = Math.abs(ary.get(i) - i);
			h += (num/width) + (num%width);
		}
	}

	@Override
	public int compareTo(Move y) {
		if ((h + g) < (y.h + y.g))
	   {
	       return -1;
	   }
	   if ((h + g) > (y.h + y.g))
	   {
	       return 1;
	   }
	   return 0;
	}
	
	public int hashCode()
	{
		return super.hashCode();
	}
		
}





