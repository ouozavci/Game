import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	
	BufferedReader in = null;
	PrintWriter out = null;
	Socket clientSocket = null;
	ServerSocket serverSocket;
	public Server() throws IOException{	
			 
		try {
			serverSocket = new ServerSocket(2222);	
			System.out.println("Server is running...");		
			clientSocket = serverSocket.accept();
			System.out.println("Got connection from "+clientSocket.getInetAddress()+ " port number : " + clientSocket.getPort());
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
				Game game = new Game();
			System.out.println("Game has started!");	
			String inputLine;	
			Scanner scanner = new Scanner(System.in);
				while(true){
			/*		inputLine = in.readLine();
						if(inputLine.equals("finish")) break;
					System.out.println("Got message : "+inputLine);
					//out.println(inputLine);
					 */
					System.out.println("It's your turn enter a number");
					int move = scanner.nextInt();
					game.pickStone(move, 1);
					System.out.println("You picked "+move+" stone. "+game.StoneCount+" stone remains!");
						if(game.hasWinner()){
							System.out.println("You lose!");
							out.println("YOU_WON");
							break;
						}
						out.println("YOUR_TURN "+move+" "+game.StoneCount);
					System.out.println("Waiting player 2...");
						inputLine = in.readLine();
							if(inputLine.startsWith("PLAYED")){
							
								int move2 = Integer.parseInt(inputLine.substring(7));
								game.pickStone(move2, 2);
								System.out.println("Player 2 picked "+move2+" stone. "+game.StoneCount+" stone remains!");
								if(game.hasWinner()){
									System.out.println("You won!");
									out.println("YOU_LOSE");
									break;
								}
							}							
				}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			serverSocket.close();
			clientSocket.close();
		}
	}

	public static void main(String[] args) throws IOException{
		Server server = new Server();
		
	}
}
class Game{
	int StoneCount;
	int winner = -1;
	public Game(){
		this.StoneCount = 20;
	}
	public void pickStone(int count,int id){
		this.StoneCount -= count;
			if(this.StoneCount<=0){
				switch (id){
					case 1:this.winner = 2;
						break;
					case 2:this.winner = 1;
						break;
				}
			}
/*		if(this.StoneCount>0)
			System.out.println("Player "+id+" picked "+count+" stone :: "+this.StoneCount+" stone remains");
		else{
			System.out.println("Player "+id+" defeated");
		}
*/			
	}
	public boolean hasWinner(){
		return !(this.StoneCount>0);
	}
}
