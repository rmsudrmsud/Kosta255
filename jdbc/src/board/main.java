package board;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		BoardService s = new BoardService();
		s.addBoard(sc);
	}

}
