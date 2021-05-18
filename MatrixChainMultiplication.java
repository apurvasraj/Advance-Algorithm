import java.util.ArrayList;

import java.util.Scanner;



class Dimension {



	private int col;

	private int row;



	Dimension(int col, int row) {



		this.col = col;

		this.row = row;

	}



	int column() {

		return col;

	}



	int row() {

		return row;

	}

}



public class MatrixChainMultiplication {

	private static Scanner scan = new Scanner(System.in);

	private static ArrayList<Dimension> array = new ArrayList<>();

	private static int arraySize;

	private static int[][] m;

	private static int[][] s;

	private static int[] p;



	public static void main(String[] args) {

		System.out.println("Enter the Dimension of the Matrix: ");

		while (true) {

			String[] mSize = input("Enter the Dimension of M");

			if (mSize[0].equals("#"))

				break;



			int column = Integer.parseInt(mSize[0]);

			int row = Integer.parseInt(mSize[1]);



			Dimension matrix = new Dimension(column, row);

			array.add(matrix);



		}

		arraySize = array.size();

		m = new int[arraySize + 1][arraySize + 1];

		s = new int[arraySize + 1][arraySize + 1];

		p = new int[arraySize + 1];



		for (int i = 0; i < p.length; i++) {

			p[i] = i == 0 ? array.get(i).column() : array.get(i - 1).row();

		}



		matrixChainMultiplication();



		System.out.println("Optimal paranthesization is : ");

		optimalParens(1, arraySize);



	}



	private static void optimalParens(int i, int j) {

		if (i == j) {

			System.out.print("M" + i);

		} else {

			System.out.print("(");

			optimalParens(i, s[i][j]);

			System.out.print("*");

			optimalParens(s[i][j] + 1, j);

			System.out.print(")");

		}

	}



	private static void matrixChainMultiplication() {

		for (int i = 1; i < arraySize + 1; i++) {

			m[i][i] = 0;

		}



		for (int l = 2; l < arraySize + 1; l++) {

			for (int i = 1; i < arraySize - l + 2; i++) {

				int j = i + l - 1;

				m[i][j] = Integer.MAX_VALUE;



				for (int k = i; k < j; k++) {

					int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];

					if (q < m[i][j]) {

						m[i][j] = q;

						s[i][j] = k;

					}

				}

			}

		}

	}



	private static String[] input(String string) {

		return (scan.nextLine().split("\\*"));

	}



}