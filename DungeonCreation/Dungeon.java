
public class Dungeon{
	private int rows;
	private int cols;
	private int[][] room;


	public Dungeon()
	{
		rows = (int)(Math.random() * 5) + 5;
		cols = (int)(Math.random() * 5) + 5;
		room = new int[rows][cols];
		generateBorder();
	}
	
	public int[][] getRoom()
	{
		return room;
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
