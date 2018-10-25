package board;

public class FileRank {
	char file; // a, b, c, d, e, f, g, h
	int rank; // 1, 2, 3, 4, 5, 6, 7, 8
	static char fileList[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	public FileRank(char file, int rank) {
		this.file = file;
		this.rank = rank;
	}
	
	public char getFile() {
		return file;
	}

	public int getRank() {
		return rank;
	}

	public String toString() {
		String result = "File: " + file;
		result += "\nRank: " + rank;
		return result;
	}
//	public boolean isToTheLeftOf(char rank) {
//		
//	}
}
