
public class SudokuSolver {
	public static void main(String[] args) {
		new SudokuSolver();
	}

	private final int EMPTY = 0;
	private int[][] matrix = { { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				   { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				   { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				   { 0, 0, 1, 0, 0, 0, 0, 0, 0 },
				   { 0, 0, 0, 0, 0, 0, 2, 0, 0 }, 
				   { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, 
				   { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				   { 0, 0, 0, 0, 0, 0, 0, 0, 0 } };
	private int count = 0;

	public SudokuSolver() {
		solveSudoku(matrix);
	}

	private boolean solveSudoku(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] == EMPTY) {
					for (int number = 1; number < 10; number++) {
						if (fitsInSudoku(number, i, j, matrix)) {
							matrix[i][j] = number;
							if (solveSudoku(matrix))
								printSolution(matrix);
							matrix[i][j] = 0;
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private void printSolution(int[][] matrix) {
		System.out.println("Loesung: " + ++count);
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix.length; y++) {
				System.out.print(matrix[x][y] +"  ");
			}
			System.out.println();
		}
		System.out.println();
	}


	private boolean fitsInRow(int number, int x, int y, int[][] matrix) {
		for (int i = 0; i < matrix.length; i++)
			if (matrix[i][y] == number)
				return false;
		return true;
	}
	
	private boolean fitsInColumn(int number, int x, int y, int[][] matrix) {
		for (int j = 0; j < matrix.length; j++)
			if (matrix[x][j] == number)
				return false;
		return true;
	}
	
	private boolean fitsInSquare(int number, int x, int y, int[][] matrix) {
		for (int i = x - x % 3; i < x - x % 3 + 3; i++)
			for (int j = y - y % 3; j < y - y % 3 + 3; j++)
				if (matrix[i][j] == number)
					return false;
		return true;
	}
	
	private boolean fitsInSudoku(int number, int x, int y, int[][] matrix) {
		return fitsInColumn(number, x, y, matrix) && 
				fitsInRow(number, x, y, matrix) && fitsInSquare(number, x, y, matrix);
	}
}
