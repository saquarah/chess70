package board;

/**
 * 
 * @author Sarah Squillace, Nikita Zala
 * This class represents a location on the chess board, with a file being the columns, and the rank
 * notating the rows.
 */
public class FileRank {
	char file; // a, b, c, d, e, f, g, h
	int rank; // 1, 2, 3, 4, 5, 6, 7, 8
	static char fileList[] = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	/**
	 * Creates a new instance of a FileRank
	 * @param file the character representing the file of the FileRank
	 * @param rank the integer representing the rank of the FileRank
	 */
	public FileRank(char file, int rank) {
		this.file = file;
		this.rank = rank;
	}
	/**
	 * Gets the file of this instance ( a char)
	 * @return the character representing the file of the FileRank
	 */
	public char getFile() {
		return file;
	}
	/**
	 * Gets the rank of this instance
	 * @return the int representing the rank of the FileRank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * Gives the String representation of the FileRank in the form
	 * File: [file] Rank: [rank]
	 * @return the String representation of the FileRank, labeling the file and the rank.
	 */
	public String toString() {
		String result = "File: " + file;
		result += "\nRank: " + rank;
		return result;
	}
	// not sure how useful these will be
	/**
	 * Determines if this FileRank is to the left of the given file.
	 * @param file - char representing the file
	 * @return true if this instance of a FileRank is to the left of the parameterized file
	 */
	public boolean isToTheLeftOf(char file) {
		return this.file < file;
	}
	/**
	 * Determines if this FileRank is to the right of the given file.
	 * @param file - char representing the file
	 * @return true if this instance of a FileRank is to the right of the parameterized file
	 */
	public boolean isToTheRightOf(char file) {
		return this.file > file;
	}
	/**
	 * Determines if this FileRank is above the given rank.
	 * @param rank - int representing the rank
	 * @return true if this instance of a FileRank is above the parameterized rank
	 */
	public boolean isAbove(int rank) {
		return this.rank > rank;
	}
	/**
	 * Determines is this FileRank is below the given rank.
	 * @param rank - int representing the rank
	 * @return true if this instance of a FileRank is above the parameterized rank
	 */
	public boolean isBelow(int rank) {
		return this.rank < rank;
	}
	/**
	 * Determines if this FileRank is equal to the parameterized object.
	 * @return true if the parameterized object is a FileRank, and its fields file and rank are equal
	 * to the instance's file and rank.
	 */
	public boolean equals(Object o) {
		if(o == null || !(o instanceof FileRank)) {
			return false;
		} else {
			FileRank fr = (FileRank) o;
			return this.file == fr.file && this.rank == fr.rank;
		}
	}
}
