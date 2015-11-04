/**U10111033, Computer science 4, Hsueh_Hsin Lu*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class MoveMessageDemo extends JFrame {
	// Create a MessagePane instance for moving a message
	MessagePane p1 = new MessagePane();
	
	public MoveMessageDemo() {
		//Both javax.swing and java.util has Timer, so we assign it to javax.swing.Timer
		javax.swing.Timer myTime = new javax.swing.Timer(1000, new TimerListener());
		myTime.start();
		
		// Place the message panel in the frame
		add(p1);
	}
	
	//call the second with setCurrentTime, and repaint the panel every second
	public class TimerListener implements ActionListener{
		@Override /** Handle TimerListener */
		public void actionPerformed(ActionEvent e){
			p1.setCurrentTime();
			p1.repaint();
		}
	}

	/** Main method */
	public static void main(String[] args) {
		MoveMessageDemo frame = new MoveMessageDemo();
		frame.setTitle("MoveMessageDemo");
		frame.setSize(400, 400);
		frame.setLocationRelativeTo(null); // Center the frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	// Inner class: MessagePane draws a message
	static class MessagePane extends JPanel {
		private String message1 = "Java is fun";
		private String message2 = "Java is powerful";
		//defult is 70, 70
		private int xMouse = 70;
		private int yMouse = 70;
		//rotate redius is 50
		private int redius = 50;
		private int second;
		
		//get second
		public void setCurrentTime(){
			Calendar myCalendar = new GregorianCalendar();
			this.second = myCalendar.get(Calendar.SECOND);
		}
		
		/** Construct a panel to draw string s */
		public MessagePane() {
			addMouseMotionListener(new MouseMotionListener() {
					@Override /** Handle mouse-dragged event */
					public void mouseDragged(MouseEvent e) {
						// Get the new location and repaint the screen
						xMouse = e.getX();
						yMouse = e.getY();
						repaint();
					}
					
					@Override /** Handle mouse-moved event */
					public void mouseMoved(MouseEvent e) {
					}
				}
			);
		}
		
		/** Handle Graphics-painting event */
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//set coordinate
			int xSecond1 = (int) (xMouse + redius * Math.sin(second * (2 * Math.PI / 30)));
			int ySecond1 = (int) (yMouse + redius * Math.cos(second * (2 * Math.PI / 30)));
			int xSecond2 = (int) (xMouse + redius * Math.sin(second * (2 * Math.PI / 30) + Math.PI));
			int ySecond2 = (int) (yMouse + redius * Math.cos(second * (2 * Math.PI / 30) + Math.PI));
			//drawString
			g.drawString(message1, xSecond1, ySecond1);
			g.drawString(message2, xSecond2, ySecond2);
		}
	}
}