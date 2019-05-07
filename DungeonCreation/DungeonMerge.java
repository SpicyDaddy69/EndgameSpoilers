import java.util.ArrayList;

public class DungeonMerge {
	private static int[][] dung;
	private ArrayList<Dungeon> rooms;
	private static int totalSize;

	public static void main(String[] args) {
		totalSize = 25;
		DungeonMerge temp = new DungeonMerge();
		temp.generateRooms();
		temp.addRooms();
		for (int rows = 0; rows < dung.length; rows++) {
			for (int cols = 0; cols < dung[rows].length; cols++)
				System.out.print(dung[rows][cols] + " ");
			System.out.println();
		}

	}

	public void addRooms()
	{
		boolean roomNotAdded = true;
		int counterForIfRoomPossible = 0;
		dung = new int[totalSize][totalSize];
		for(int rows = 0; rows < dung.length; rows++)
			for(int cols = 0; cols < dung[rows].length; cols++)
				dung[rows][cols] = 7;
		for(int i = 0; i < getRooms().size(); i++)
		{
			counterForIfRoomPossible = 0;
			roomNotAdded = true;
			while(roomNotAdded)
			{
				int tempRow = (int) (Math.random() * (totalSize - 10));
				int tempCol = (int) (Math.random() * (totalSize - 10));
				if(inBounds(getRooms().get(i), tempRow , tempCol))
				{
					for(int rows = tempRow; rows < getRooms().get(i).getRoom().length + tempRow; rows++)
					{
						for(int cols = tempCol; cols < getRooms().get(i).getRoom()[0].length + tempCol; cols++)
						{
								dung[rows][cols] = 0;
						}
					}
					roomNotAdded = false;
				}
				counterForIfRoomPossible++;
				if(counterForIfRoomPossible == 100)
					roomNotAdded = false;
				
			}
		}
		
	}

	public void generateRooms() {
		rooms = new ArrayList<Dungeon>();
		for (int i = 0; i < 6; i++) {
			rooms.add(new Dungeon());
		}
	}

	public ArrayList<Dungeon> getRooms() {
		return rooms;
	}

	public boolean inBounds(Dungeon room, int row, int col) {
		int count = 0;
		int[][] roomTemp = room.getRoom();
		if (dung[0].length - roomTemp[0].length + col > 0)
			count++;
		if (dung.length - roomTemp.length + row > 0)
			count++;
		for(int rows = row; rows < roomTemp.length + row; rows++)
		{
			for(int cols = col; cols < roomTemp[0].length + col; cols++)
			{
				if(dung[rows][cols] == 0)
					return false;
			}
		}
		if (count == 2)
			return true;
		return false;
	}
}
