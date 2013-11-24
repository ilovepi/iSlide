
class SolutionGenerator
{
	private int seed;
	private Stack<Move> move_list;
	private Random rand;
	private HashMap<Move> hash;
	private ArrayList<Integer> rand_list;

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
		seed = rand.randInt(num_solutions);
		rand = new Random(seed);

		// dB = new SQLdB 	// make a SQLite DB
	 
		int id, solution_length;
		for(int i = 0; i < num_solutions; ++i)
		{
			
			do{
				id = rand.randInt();
			}while(null != rand_list.find(id));
			rand_list.insert(id);

			solution_length = rand.randInt(50-18) + 18;
			GenerateSolution(size, id, solution_length);
		}

	}

	public GenerateSolution(int size, int solution_id, int solution_length)

};



