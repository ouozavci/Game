import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	
	public Client() throws IOException {
		try {
			socket = new Socket("localhost", 2222);
			
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner scanner = new Scanner(System.in);
			System.out.println("Game started!");
			String command;
			while(true){
				System.out.println("Waiting player 1...");
				command = in.readLine();
					if(command.startsWith("YOUR_TURN")){
						int pick = Integer.parseInt(command.substring(10,11));
						int stoneCount = Integer.parseInt(command.substring(12));
						System.out.println("Player 1 picked "+pick+" stone. "+stoneCount+" stone remains.");	
						System.out.println("It's your turn enter a number:");
						int move = scanner.nextInt();
						out.println("PLAYED "+move);
					}
					else if(command.startsWith("YOU_LOSE")){
						System.out.println("You lose!");
						break;
					}
					else if(command.startsWith("YOU_WON")){
						System.out.println("You win!");
						break;
					}
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			socket.close();
		}
		
	}
	public static void main(String[] args) throws IOException{
		Client client = new Client();
		//client.findServer();
	}
}
