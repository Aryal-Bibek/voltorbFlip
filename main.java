package voltorbFlip;

import java.util.Scanner;


public class main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		Game game= new Game();
		String input = "1";
		do {
		System.out.println("Enter a difficulty level: 1 - easy, 2 - medium, 3 - hard, 0  to exit");
		input = scan.next();
		}while(input.length() !=1 || (int)input.toCharArray()[0] > 51 || (int)input.toCharArray()[0] < 	48);
		
		System.out.println((int)(input.toCharArray()[0]));
		game.playGame((int)input.toCharArray()[0]-48);
	}

}
