
public class Dungeon{
	private int rows;
	private int cols;
	private int[][] room;

	public static void main(String[] args)
	{
		Dungeon Dungeon = new Dungeon();
		System.out.println(Dungeon);
	}

	public Dungeon()
	{
		rows = (int)(Math.random() * 6) + 4;
		cols = (int)(Math.random() * 6) + 4;
		room = new int[rows][cols];
	}


	public int[][] generateBorder()
	{
		for (int rows = 0; rows < this.rows; rows++)
		{
			for (int cols = 0; cols < this.cols; cols++)
			{
				if (cols == 0 || rows == 0 || cols == this.cols - 1 || rows == this.rows - 1)
				{
					room[rows][cols] = 1;
				}
			}
		}
		return room;
	}

	

	public String toString()
	{
		String output = "";
		generateBorder();

		for (int rows = 0; rows < room.length; rows++)
		{
			for (int cols = 0; cols < room[rows].length; cols++)
			{
				output += room[rows][cols] + " ";
			}
			output += "\n";
		}
		return output;
	}
}
