import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JButton;

public class Client {
	
	Socket socket = null;
	BufferedReader in = null;
	PrintWriter out = null;
	GameGui gui = null;
	
	public Client() throws IOException {
		try {
			socket = new Socket("localhost", 2222);
			
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner scanner = new Scanner(System.in);
			System.out.println("Game started!");
			JButton b1 = new JButton("1");
			JButton b2 = new JButton("2");
			JButton b3 = new JButton("3");
			gui = new GameGui(b1,b2,b3);	
			gui.setTitle("PLAYER 2");
			gui.setMessage("Game started!");
			
			String command;
			gui.pnlButtons.setVisible(false);
			gui.setMessage("Waiting player 1...");
			
			b1.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					out.println("PLAYED 1");
					gui.pnlButtons.setVisible(false);
					gui.setMessage("Waiting player 1...");
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
					out.println("PLAYED 2");
					gui.pnlButtons.setVisible(false);
					gui.setMessage("Waiting player 1...");
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
					
					out.println("PLAYED 3");
					gui.pnlButtons.setVisible(false);
					gui.setMessage("Waiting player 1...");
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
			while(true){
		
				command = in.readLine();
					if(command.startsWith("YOUR_TURN")){
						gui.pnlButtons.setVisible(true);	
						int pick = Integer.parseInt(command.substring(10,11));
						int stoneCount = Integer.parseInt(command.substring(12));
						gui.setMessage("Player 1 picked "+pick+" stone. "+stoneCount+" stone remains.");
						gui.setVisible(false);
						gui.setVisible(true);
						//System.out.println("It's your turn enter a number:");
						
					
					}
					else if(command.startsWith("YOU_LOSE")){
						gui.setMessage("You lose!");
						break;
					}
					else if(command.startsWith("YOU_WON")){
						gui.setMessage("You win!");
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
		
	}
}
