package board;

public class FileRank {
	char file; // a, b, c, d, e, f, g, h
	int rank; // 1, 2, 3, 4, 5, 6, 7, 8
	
	public FileRank(char file, int rank) {
		this.file = file;
		this.rank = rank;
	}
	
	public String toString() {
		String result = "File: " + file;
		result += "\nRank: " + rank;
		return result;
	}
}
