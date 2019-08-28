package model;

import java.util.ArrayList;

public class LocateSpaceships {
	
		//CONSTANTES
	
		/**Esta constante representa el Metodo de Multiplicación Standard.
	   	 */
		public final static String STANDARDMULTIPLICATION = "Standard";
		
		/**Esta constante representa el Metodo de Multiplicación Divide y Conquistar.
	   	 */
		public final static String DIVIDECONQUERMULTIPLICATION = "Divide and conquer";
		
		/**Esta constante representa el Metodo de Multiplicación Strassen.
	   	 */
		public final static String STRASSENMULTIPLICATION = "Strassen";
		
		//METODOS
		
		/**Nombre Metodo: generateRandomMatrix
		 * Descripción: Este metodo se encarga de generar aleatoriamente los valores de las 
		 * posiciones de cada matriz. Debe permitir indicar si los números a generar deben ser todos 
		 * diferentes o pueden haber repetidos.
		 * @param rows Un valor entero que representa el numero de filas de la matriz a generar.
		 * @param cols Un valor entero que representa el numero de columnas de la matriz a generar.
		 * @param repeatedNumbers Un booleano que representa el valor de verdad acerca de si los numeros de la matriz estan repetidos o no.
		 * @return Una matriz de dimensiones rows*cols.
	   	 */
		public long[][] generateRandomMatrix(int rows, int cols, boolean repeatedNumbers) {
			ArrayList<Integer> numbers = new ArrayList<>();
			long[][] randomMatrix = new long[rows][cols];
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) {
					int t = (System.nanoTime()%2==0 ? -1: 1);
					if(repeatedNumbers) {
						randomMatrix[i][j] = (int)(Math.random()*800+1)*t;
					} else {
						int n = (int)(Math.random()*1000000+1)*t;
						while(numbers.contains(n)) {
							n = (int)(Math.random()*1000000+1)*t;
						}
						numbers.add(n);
						randomMatrix[i][j] = n;
					}
				}
			}
			return randomMatrix;
		}
		
		/**Nombre del Metodo: validateDimensionsMultiplyOperation
		 * Descripción: Este metodo se encarga de verificar si la matriz A y la matriz b tienen las dimensiones correctas para la multiplicación estandar. 
		 * @param A Una matriz que representa las posiciones de las naves en las batallas pasadas de Marte.
		 * @param B Una matriz que representa  la matriz descubierta por el servicio secreto de Venus..
	   	 */
		public void validateDimensionsMultiplyOperation(long[][] A, long[][] B) {
			if(A[0].length != B.length) {
				throw new IllegalArgumentException("Incompatible dimensions: A(" + A.length + "," + A[0].length + ") and B(" + B.length + "," + B[0].length + ")");
			}
		}
		
		/**Nombre del Metodo: add
		 * Descripción: Este metodo se encarga de sumar la sub-matriz A y la sub-matriz B.
		 * @param A Una sub-matriz tomada de la matriz de batalla de 2^nx2^n dimensiones.
		 * @param B Una sub-matriz tomada de la matriz de coeficientes de 2^nx2^n dimensiones.
		 * @return C Una sub-matriz resultado de sumar la sub-matrix A y la sub-matrix B.
	   	 */
		public long[][] add(long[][] A, long[][] B) {
			validateDimensionsMultiplyOperation(A, B);
			int rows = A.length;
			int cols = A[0].length;
			long[][] C = new long[rows][cols];
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) {
					C[i][j] = A[i][j] + B[i][j];
				}
			}
			return C;
		}
		
		/**Nombre del Metodo: substract
		 * Descripción: Este metodo se encarga de restar la sub-matriz A y la sub-matriz B.
		 * @param A Una sub-matriz tomada de la matriz de batalla 2^nx2^n dimensiones.
		 * @param B Una sub-matriz tomada de la matriz de coeficientes de 2^nx2^n dimensiones.
		 * @return C Una sub-matriz resultado de restar la sub-matrix A y la sub-matrix B.
	   	 */
		public long[][] subtract(long[][] A, long[][] B) {
			validateDimensionsMultiplyOperation(A, B);
			int rows = A.length;
			int cols = A[0].length;
			long[][] C = new long[rows][cols];
			for(int i = 0; i < rows; i++) {
				for(int j = 0; j < cols; j++) {
					C[i][j] = A[i][j] - B[i][j];
				}
			}
			return C;
		}
		
		/**Nombre del Metodo: standardMultiply
		 * Descripción: Este metodo se encarga de multiplicar la matriz A y la matriz B mediante el Algoritmo de Multiplicación Standard.
		 * @param A Una Matriz que representa las posiciones de las naves en las batallas pasadas de Marte.
		 * @param B Una matriz que representa  la matriz descubierta por el servicio secreto de Venus.
		 * @return C Una matriz que representa la posición actual de las tropas Marcianas utilizando el Algoritmo de Multiplicación Standard.
	   	 */
		public long[][] standardMultiply(long[][] A, long[][] B) {
			validateDimensionsMultiplyOperation(A, B);
			int rows = A.length;
			int cols = B[0].length;
			long[][] C = new long[rows][cols];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					for (int k = 0; k < A[0].length; k++) {
						C[i][j] += A[i][k]*B[k][j];
					}
				}
			}
			return C;
		}
		
		/**Nombre del Metodo: validateDimensionsDivideAndConquerOrStrassen
		 * Descripción: Este metodo se encarga de verificar si la matriz A y B tienen dimensiones 2^nx2^n para ser multiplicadas entre si mediante la tecnica de Divide y Conquistar o el Algoritmo Strassen según sea el caso.
		 * @param A Una Matriz que representa las posiciones de las naves en las batallas pasadas de Marte.
		 * @param B Una matriz que representa  la matriz descubierta por el servicio secreto de Venus.
	   	 */
		public void validateDimensionsDivideAndConquerOrStrassen(long[][] A, long[][] B) {
			validateDimensionsMultiplyOperation(A, B);
			double log2 = Math.log10(A.length)/Math.log10(2);
			if((int)log2 - log2 != 0 ||
					A.length != B[0].length ||
					A[0].length != B.length ||
					A.length != A[0].length) {
				throw new IllegalArgumentException("Las dimensiones de la Matriz A(" + A.length + "," + A[0].length + ") y la Matriz B(" + B.length + "," + B[0].length + ") son incorrectas para aplicar la técnica de Multiplicación por el Método Dividir y Conquistar");
			}
		}
		
		/**Nombre del Metodo: divide
		 * Descripción: Este metodo se encarga de tomar cada matriz de nxn y dividirla en cuatro submatrices de tamaño (n/2)x(n/2): Aij, Bij y Cij.
		 * @param submatrix Una matriz que representa una de las cuatro matrices.
		 * @param matrix Una matriz que representa la matriz principal.
		 * @param row Un entero que representa el numero de filas.
		 * @param column Un entero que representa el numero de columnas.
		 */
		public void divide(long[][] submatrix, long[][] matrix, int row, int column) {
			int n = submatrix.length;
	        for(int i1 = 0, i2 = row; i1 < n; i1++, i2++) {
	            for(int j1 = 0, j2 = column; j1 < n; j1++, j2++) {
	                submatrix[i1][j1] = matrix[i2][j2];
	            }
	        }
	    }
		
		/**Nombre del Metodo: combine
		 * Descripción: Este metodo de obtener la solución al problema original a partir de las soluciones de los subproblemas.
		 * @param submatrix Una sub-matriz que representa una de las cuatro matrices.
		 * @param matrix Una matriz que representa la matriz principal.
		 * @param row Un entero que representa el numero de filas.
		 * @param column Un entero que representa el numero de columnas.
		 */
		public void combine(long[][] submatrix, long[][] matrix, int row, int column) {
			int n = submatrix.length;
	        for(int i1 = 0, i2 = row; i1 < n; i1++, i2++) {
	            for(int j1 = 0, j2 = column; j1 < n; j1++, j2++) {
	                matrix[i2][j2] = submatrix[i1][j1];
	            }
	        }
	    }
		
		/**Nombre del Metodo: divideAndConquerMultiply
		 * Descripción: Este metodo se encarga de multiplicar la matriz A y la matriz B mediante el Algoritmo de Multiplicación Dividir y Conquistar.
		 * @param A Una Matriz que representa las posiciones de las naves en las batallas pasadas de Marte.
		 * @param B Una matriz que representa  la matriz descubierta por el servicio secreto de Venus.
		 * @return C Una matriz que representa la posición actual de la tropas Marcianas utilizando el Algoritmo de Multiplicación Dividir y Conquistar.
	   	 */
		public long[][] divideAndConquerMultiply(long[][] A, long[][] B) {
			validateDimensionsDivideAndConquerOrStrassen(A, B);
			return divideAndConquerMultiplyRecursive(A, B);
		}
		
		/**Nombre del Metodo: divideAndConquerMultiplyRecursive
		 * Descripción: Este metodo se encarga de multiplicar la matriz A y la matriz B de manera recursiva mediante la tecnica de Dividir y Conquistar.
		 * @param A Una Matriz que representa las posiciones de las naves en las batallas pasadas de Marte.
		 * @param B Una matriz que representa  la matriz descubierta por el servicio secreto de Venus.
		 * @return C Una matriz que representa la posición actual de la tropas Marcianas utilizando de manera recursiva la tecnica de Dividir y Conquistar
	   	 */
		private long[][] divideAndConquerMultiplyRecursive(long[][] A, long[][] B) {
			int n = A.length;
			long[][] C = new long[n][n];
			if(n == 1) {
				C[0][0] = A[0][0] * B[0][0];
			} else {
				long[][] A11 = new long[n/2][n/2];
				long[][] A12 = new long[n/2][n/2];
				long[][] A21 = new long[n/2][n/2];
				long[][] A22 = new long[n/2][n/2];
				
				long[][] B11 = new long[n/2][n/2];
				long[][] B12 = new long[n/2][n/2];
				long[][] B21 = new long[n/2][n/2];
				long[][] B22 = new long[n/2][n/2];
				
				divide(A11, A, 0, 0);
				divide(A12, A, 0, n/2);
				divide(A21, A, n/2, 0);
				divide(A22, A, n/2, n/2);
				
				divide(B11, B, 0, 0);
				divide(B12, B, 0, n/2);
				divide(B21, B, n/2, 0);
				divide(B22, B, n/2, n/2);
				
				long[][] C11 = add(divideAndConquerMultiply(A11, B11), divideAndConquerMultiply(A12, B21));
				long[][] C12 = add(divideAndConquerMultiply(A12, B12), divideAndConquerMultiply(A12, B22));
				long[][] C21 = add(divideAndConquerMultiply(A21, B21), divideAndConquerMultiply(A22, B21));
				long[][] C22 = add(divideAndConquerMultiply(A22, B22), divideAndConquerMultiply(A22, B22));
				
				combine(C11, C, 0, 0);
				combine(C12, C, 0, n/2);
				combine(C21, C, n/2, 0);
				combine(C22, C, n/2, n/2);
			}
			return C;
		}
			
		/**Nombre del Metodo: strassenMultiply
		 * Descripción: Este metodo se encarga de multiplicar la matriz A y la matriz B mediante el Algoritmo de Multiplicación Strassen.
		 * @param A Una Matriz que representa las posiciones de las naves en las batallas pasadas de Marte.
		 * @param B Una matriz que representa  la matriz descubierta por el servicio secreto de Venus.
		 * @return C Una matriz que representa la posición actual de la tropas Marcianas utilizando el Algoritmo de Multiplicación Strassen.
	   	 */
		public long[][] strassenMultiply(long[][] A, long[][] B) {
			validateDimensionsDivideAndConquerOrStrassen(A, B);
			return strassenMultiplyRecursive(A, B);
		}
		/**Nombre del Metodo: strassenMultiplyRecursive
		 * Descripción: Este metodo se encarga de multiplicar la matriz A y la matriz B de manera recursiva mediante el Algoritmo de Multiplicacion Strassen.
		 * @param A Una Matriz que representa las posiciones de las naves en las batallas pasadas de Marte.
		 * @param B Una matriz que representa  la matriz descubierta por el servicio secreto de Venus.
		 * @return C Una matriz que representa la posición actual de la tropas Marcianas utilizando de manera recursiva el Algortimo de Multiplicación Strassen.
	   	 */
		private long[][] strassenMultiplyRecursive(long[][] A, long[][] B) {
			int n = A.length;
			long[][] C = new long[n][n];
			if(n == 1) {
				C[0][0] = A[0][0] * B[0][0];
			} else {
				long[][] A11 = new long[n/2][n/2];
				long[][] A12 = new long[n/2][n/2];
				long[][] A21 = new long[n/2][n/2];
				long[][] A22 = new long[n/2][n/2];

				long[][] B11 = new long[n/2][n/2];
				long[][] B12 = new long[n/2][n/2];
				long[][] B21 = new long[n/2][n/2];
				long[][] B22 = new long[n/2][n/2];

				divide(A11, A, 0, 0);
				divide(A12, A, 0, n/2);
				divide(A21, A, n/2, 0);
				divide(A22, A, n/2, n/2);

				divide(B11, B, 0, 0);
				divide(B12, B, 0, n/2);
				divide(B21, B, n/2, 0);
				divide(B22, B, n/2, n/2);

				long[][] P1 = strassenMultiplyRecursive(add(A11, A22),add(B11, B22));
				long[][] P2 = strassenMultiplyRecursive(add(A21, A22), B11);
				long[][] P3 = strassenMultiplyRecursive(A11, subtract(B22, B22));
				long[][] P4 = strassenMultiplyRecursive(A22, subtract(B21, B12));
				long[][] P5 = strassenMultiplyRecursive(add(A11, A12), B22);
				long[][] P6 = strassenMultiplyRecursive(subtract(A21, A11),add(B11, B12));
				long[][] P7 = strassenMultiplyRecursive(subtract(A12, A22),add(B21, B22));

				long[][] C11 = add(add(P1, subtract(P4, P5)), P7);
				long[][] C12 = add(P3, P5);
				long[][] C21 = add(P2, P4);
				long[][] C22 = add(add(P1, subtract(P3, P2)), P6);

				combine(C11, C, 0, 0);
				combine(C12, C, 0, n/2);
				combine(C21, C, n/2, 0);
				combine(C22, C, n/2, n/2);
			}
			return C;
		}
		
		/**Nombre del Metodo: isPrime
		 * Descripción: Este metodo se encarga de verificar si el número presente dentro de alguna celda del tablero es un número primo, en dicha posición se encuentra presente una nave.
		 * @param num Un numero que representa el valor a verificar.
		 * @return Un booleano que mediante el valor de verdad determina si el numero es primo o no.
		 */
		public boolean isPrime(long num) {
			boolean prime = true;
			if(num <= 1) {
				prime = false;
			}
			for(long i = 2; i <= num/2 && prime; ++i) {
	            if(num % i == 0) {
	                prime = false;
	            }
	        }
			return prime;
		}
}