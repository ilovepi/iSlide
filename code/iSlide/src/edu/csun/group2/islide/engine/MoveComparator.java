package edu.csun.group2.islide.engine;

import java.util.Comparator;

public class MoveComparator implements Comparator<Move>
{
	@Override
	public int compare(Move x, Move y)
	{
	   // Assume neither string is null. Real code should
	   // probably be more robust
	   if ((x.h + x.g) < (y.h + y.g))
	   {
	       return -1;
	   }
	   if ((x.h + x.g) > (y.h + y.g))
	   {
	       return 1;
	   }
	   return 0;
	}
}
