package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.sun.javafx.tk.Toolkit;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class PipeDisplayer extends Canvas{

	ArrayList<String> pipeGame;
	
	@FXML
	PipeDisplayer pipeDisplayer;
	
	public PipeDisplayer() {
		pipeGame = new ArrayList<String>();
	}
	
	public void setPipeDisplayer(File game) {
		System.out.println(game);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader (game));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");

	        try {
				while((line = reader.readLine()) != null) {
				    pipeGame.add(line);
				    //stringBuilder.append("/n");
				    //stringBuilder.delete(0,stringBuilder.length());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	       System.out.println(pipeGame.toString());

	        try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	    redraw();
	}
	
	public void redraw() {
		if(pipeGame!=null) {
			double W=this.getWidth();
			double H=this.getHeight();
			double w=W/pipeGame.get(0).length();
			double h=H/pipeGame.size();
			
			GraphicsContext gc=this.getGraphicsContext2D();
			Image startImage=null;
			try {
				startImage = new Image(new FileInputStream("./resources/spongeBoB/sad spongeBob.png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Image horizontal=null;
			try {
				horizontal = new Image(new FileInputStream("./resources/spongeBoB/horizontal.jpeg"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image leftDown=null;
			try {
				leftDown = new Image(new FileInputStream("./resources/spongeBoB/left-down.jpeg"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image leftUp=null;
			try {
				leftUp = new Image(new FileInputStream("./resources/spongeBoB/left-up.jpeg"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image rightDown=null;
			try {
				rightDown = new Image(new FileInputStream("./resources/spongeBoB/right-down.jpeg"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image rightUp=null;
			try {
				rightUp = new Image(new FileInputStream("./resources/spongeBoB/right-up.jpeg"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image vertical=null;
			try {
				vertical = new Image(new FileInputStream("./resources/spongeBoB/vertical.jpeg"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image goal=null;
			try {
				goal = new Image(new FileInputStream("./resources/spongeBoB/sponge pinnapple.png"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			gc.setFill(Color.WHITE);
			for(int i=0;i<pipeGame.size();i++) {
				for(int j=0;j<pipeGame.get(0).length();j++) {
					switch (pipeGame.get(i).charAt(j)) {
					case 's':
						;
						gc.fillRect(j*w,i*h,w,h);
						gc.drawImage(startImage,j*w,i*h,w,h);//840.1350,j*w,i*h
						break;
					
					case 'g':
						gc.fillRect(j*w,i*h,w,h);
						gc.drawImage(goal,j*w,i*h,w,h);
						break;
					case '-':
						gc.drawImage(horizontal,j*w,i*h,w,h);
						break;	
					case '|':
						gc.drawImage(vertical,j*w,i*h,w,h);
						break;
					case 'L':
						gc.drawImage(rightUp,j*w,i*h,w,h);
						break;
					case '7':
						gc.drawImage(leftDown,j*w,i*h,w,h);
						break;
					case 'f':
						gc.drawImage(rightDown,j*w,i*h,w,h);
						break;
					case 'j':
						gc.drawImage(leftUp,j*w,i*h,w,h);
						break;
					default:
						break;
					} {
					
					
					}
					
				
				}
				
			}
		}
		
	}
}
