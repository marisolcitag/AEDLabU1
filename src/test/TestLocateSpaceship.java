package test;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import model.LocateSpaceships;

class TestLocateSpaceship {

	private LocateSpaceships locate; 
	private long[][] A;
	private long[][] B;

	public void setupStage1() {
		locate = new LocateSpaceships();
		A = new long[3][3];
		B = new long[3][3];
		A[0][0] = 1;     B[0][0] = 1;
		A[1][0] = 2;	 B[1][0] = 2;
		A[2][0] = 3;	 B[2][0] = 3;
		A[0][1] = 4;     B[0][1] = -1;
		A[1][1] = 5;     B[1][1] = -1;
		A[2][1] = 6;     B[2][1] = -3;
		A[0][2] = 7;     B[0][2] = 2;
		A[1][2] = 8;     B[1][2] = 2;
		A[2][2] = 9;     B[2][2] = 0;
	}
	
	public void setupStage2() {
		locate = new LocateSpaceships();
		A = new long[2][2];
		B = new long[2][2];
		A[0][0] = 2;     B[0][0] = -1;
		A[1][0] = 1;	 B[1][0] = 5;
		A[2][0] = 0;	 B[2][0] = -1;
		A[0][1] = 3;     B[0][1] = 6;
		
	}
	
}
