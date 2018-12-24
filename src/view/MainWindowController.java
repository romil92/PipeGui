package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class MainWindowController implements Initializable{
	
	@FXML
	PipeDisplayer pipeDisplayer;
	File _boardFile=null;
	ArrayList<String> _game;
	
	public void start() {
		
		System.out.println("start");
	}
	
	
	public void openFile() throws IOException {
		File chosen;
		FileChooser fc=new FileChooser();
		fc.setTitle("open pipe game file");
		fc.setInitialDirectory(new File("./resources"));
		//fc.getSelectedExtensionFilter()

		File file = new File("./resources/media/spongebob.wav");
		MediaPlayer a =new MediaPlayer(new Media(file.toURI() .toString()));
		a.setOnEndOfMedia(new Runnable() {
			public void run() {
				a.seek(Duration.ZERO);
			}
		});
		a.play();

		
		if(_game  == null) {
			chosen = new File("./resources/Levels/level1.txt");
		}
		else {
			chosen = fc.showOpenDialog(null);
		}
		if(chosen != null) {
			ArrayList<String> pipeGameLev = new ArrayList<String>();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader (chosen));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    String         line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		    String         ls = System.getProperty("line.separator");
	
		        try {
					while((line = reader.readLine()) != null) {
						pipeGameLev.add(line);
					    //stringBuilder.append("/n");
					    //stringBuilder.delete(0,stringBuilder.length());
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
		       //System.out.println(pipeGame.toString());
	
		        try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		        _game=pipeGameLev;
		        pipeDisplayer.setPipeDisplayer(_game);
		}
	}


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(_game == null) {
			try {
				this.openFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		pipeDisplayer.setPipeDisplayer(_game);
		pipeDisplayer.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			(MouseEvent e)->{
				if(_game != null) {
					double w = pipeDisplayer.getWidth() / _game.get(0).length();
					double h = pipeDisplayer.getHeight() / _game.size();
					int x = (int) (e.getX() / w);
					int y = (int) (e.getY() / h);
				
					pipeDisplayer.changeByclick(x,y);
		        	//vm.changePipe(x, y);
				}
			}
		);
	}
	
}
