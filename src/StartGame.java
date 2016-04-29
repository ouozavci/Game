import java.io.IOException;
import java.util.Scanner;

public class StartGame {
	public StartGame(){

	}
	public static void main(String[] args) throws IOException{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your choose please :");
		System.out.println("1-Create Game.");
		System.out.println("2-Join Game");
		int choosen = input.nextInt();	
		int i = 0;
		while(i == 0){
			switch (choosen) {
			case 1:
				new Server();
				i=1;
				break;
			case 2:
				new Client();
				i=1;
				break;
			default:
				System.out.println("Wrong choice please Try Again!");
				break;
			}
		}
	}
}
