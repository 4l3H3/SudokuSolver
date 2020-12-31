
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

	public SudokuSolver() {
		if (solveProblem(matrix))
			printSolution(matrix);
	}

	private boolean solveProblem(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] == EMPTY) {
					for (int number = 1; number < 10; number++) {
						if (fitsInSudoku(number, i, j, matrix)) {
							matrix[i][j] = number;
							if (solveProblem(matrix))
								return true;
							else
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
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix.length; y++) {
				System.out.print(matrix[x][y] +"  ");
			}
			System.out.println();
		}
		System.out.println();
	}


	private boolean fitsInSudoku(int number, int x, int y, int[][] matrix) {
		for (int i = 0; i < matrix.length; i++)
			if (matrix[i][y] == number)
				return false;
		for (int j = 0; j < matrix.length; j++)
			if (matrix[x][j] == number)
				return false;
		int grenze_a = x - x % 3 + 3;
		int grenze_b = y - y % 3 + 3;
		for (int i = x - x % 3; i < grenze_a; i++) {
			for (int j = y - y % 3; j < grenze_b; j++) {
				if (matrix[i][j] == number)
					return false;
				}
		}
		return true;
	}
}
