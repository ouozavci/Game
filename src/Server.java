import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;

public class Server {
	
	BufferedReader in = null;
	PrintWriter out = null;
	Socket clientSocket = null;
	ServerSocket serverSocket;
	public Server() throws IOException{	
			 
		try {
			serverSocket = new ServerSocket(2222);	
			JButton b1 = new JButton("1");
			JButton b2 = new JButton("2");
			JButton b3 = new JButton("3");
			final GameGui gui = new GameGui(b1, b2, b3);
			gui.setMessage("Server is running...");		
			clientSocket = serverSocket.accept();
			System.out.println("Got connection from "+clientSocket.getInetAddress()+ " port number : " + clientSocket.getPort());
			out = new PrintWriter(clientSocket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			
				final Game game = new Game();
			System.out.println("Game has started!");	
			
			
			gui.setTitle("PLAYER 1");
				b1.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						game.pickStone(1, 1);
						gui.setMessage("You picked 1 stone. "+game.StoneCount+" stone remains!");
						if(game.hasWinner()){
							gui.setMessage("You lose!");
							out.println("YOU_WON");
							return;
						}
						out.println("YOUR_TURN "+1+" "+game.StoneCount);
						gui.setMessage("Waiting player 2...");
						gui.pnlButtons.setVisible(false);
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				b2.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						game.pickStone(2, 1);
						gui.setMessage("You picked 2 stone. "+game.StoneCount+" stone remains!");
						if(game.hasWinner()){
							gui.setMessage("You lose!");
							out.println("YOU_WON");
							return;
						}
						out.println("YOUR_TURN "+2+" "+game.StoneCount);
						gui.setMessage("Waiting player 2...");
						gui.pnlButtons.setVisible(false);
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				b3.addMouseListener(new MouseListener() {
					
					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						game.pickStone(3, 1);
						gui.setMessage("You picked 3 stone. "+game.StoneCount+" stone remains!");
						if(game.hasWinner()){
							gui.setMessage("You lose!");
							out.println("YOU_WON");
							return;
						}
						out.println("YOUR_TURN "+3+" "+game.StoneCount);
						gui.setMessage("Waiting player 2...");
						gui.pnlButtons.setVisible(false);
					}
					
					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
				String inputLine;	
			Scanner scanner = new Scanner(System.in);
			gui.setMessage("It's your turn enter a number");
				while(true){
			/*		inputLine = in.readLine();
						if(inputLine.equals("finish")) break;
					System.out.println("Got message : "+inputLine);
					//out.println(inputLine);
					 */
					
					gui.pnlButtons.setVisible(true);
				
						inputLine = in.readLine();
							if(inputLine.startsWith("PLAYED")){
							
								int move2 = Integer.parseInt(inputLine.substring(7));
								game.pickStone(move2, 2);
								gui.setMessage("Player 2 picked "+move2+" stone. "+game.StoneCount+" stone remains!");
								if(game.hasWinner()){
									gui.setMessage("You won!");
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
