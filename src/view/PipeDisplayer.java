package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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
	    //redraw();
	}
	
	public void redraw() {
		if(pipeGame!=null) {
			double W=this.getWidth();
			double H=this.getHeight();
			double w=W/pipeGame.get(0).length();
			double h=H/pipeGame.size();
			
			GraphicsContext gc=this.getGraphicsContext2D();
			
			for(int i=0;i<pipeGame.size();i++) {
				for(int j=0;j<pipeGame.get(0).length();j++) {
					gc.fillRect(j*w, i*h, w, h);
				
				}
				
			}
		}
		
	}
}
