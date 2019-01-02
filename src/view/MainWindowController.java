package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.xml.datatype.Duration;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class MainWindowController implements Initializable{
	
	@FXML
	PipeDisplayer pipeDisplayer;
	
	@FXML 
	BorderPane window;
	
	@FXML
	TextField moves;
	@FXML
	TextField time=new TextField();
	private int seconds;
	Timeline timer;
	ArrayList<String> _game;
	
	public void start() {
		
		//System.out.println("start");
	}
	
	
	public MainWindowController() {
		super();
		moves=new TextField("0");
		moves.setText("0");
		seconds=0;
	}
	
	public void showSteps() {
		
		moves.setText(pipeDisplayer.steps.toString());
		
		
	}
	public void initTime() {
		if (timer!=null)
		{
			timer.stop();
			seconds=0;
		}
		time.setText(Integer.toString(seconds));
		timer = new Timeline(new KeyFrame(javafx.util.Duration.millis(1000), e->{
			seconds++;
			time.setText(Integer.toString(seconds));
		}));
		timer.setCycleCount(Animation.INDEFINITE);
		timer.play();
	}
	
	public void doraTheme() {
		Theme t=new Theme();
		pipeDisplayer.stopMusic();
		t.setTheme("./resources/Dora/dora.jpg", "./resources/Dora/buts.gif",
				"./resources/spongeBoB/right-up.jpeg", "./resources/spongeBoB/right-down.jpeg",
				"./resources/spongeBoB/left-up.jpeg", "./resources/spongeBoB/left-down.jpeg",
				"./resources/spongeBoB/horizontal.jpeg", "./resources/spongeBoB/vertical.jpeg",
				"./resources/Dora/Dora The Explorer Theme Song.wav", "./resources/spongeBoB/happy_sponge.png");
		pipeDisplayer.setTheme(t);
		pipeDisplayer.playMusic();
	}

	public void spongeBobTheme() {
		Theme t=new Theme();
		pipeDisplayer.stopMusic();
		t.setTheme("./resources/spongeBoB/happy_sponge.png", "./resources/spongeBoB/sponge pinnapple.png",
				"./resources/spongeBoB/right-up.jpeg", "./resources/spongeBoB/right-down.jpeg",
				"./resources/spongeBoB/left-up.jpeg", "./resources/spongeBoB/left-down.jpeg",
				"./resources/spongeBoB/horizontal.jpeg", "./resources/spongeBoB/vertical.jpeg",
				"./resources/spongeBoB/closing.wav", "./resources/spongeBoB/happy_sponge.png");
		pipeDisplayer.setTheme(t);
		pipeDisplayer.playMusic();
		
	}
	
	public void openFile() {
		FileChooser fc=new FileChooser();
		
		fc.setTitle("open pipe game file");
		fc.setInitialDirectory(new File("./resources"));
		//fc.getSelectedExtensionFilter()
		File _boardFile;
		if(_game!=null)
			_boardFile = fc.showOpenDialog(null);
		else
			_boardFile = new File("./resources/levels/level1.txt");
		
			
		if(_boardFile != null) {
			BufferedReader reader = null;
			ArrayList<String> pipeGame=new ArrayList<String>();
			try {
				reader = new BufferedReader(new FileReader (_boardFile));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    String         line = null;
		//    StringBuilder  stringBuilder = new StringBuilder();
		//    String         ls = System.getProperty("line.separator");

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

		      // System.out.println(pipeGame.toString());

		        try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        if((_game!=null)&&(!_game.containsAll(pipeGame))) {
		        	pipeDisplayer.stopMusic();
			        pipeDisplayer.setPipeDisplayer(pipeGame);
					pipeDisplayer.playMusic();
		        }
		        else
		        	  pipeDisplayer.setPipeDisplayer(pipeGame);
		        _game=pipeGame;
		        pipeDisplayer.steps=0;
		        moves.setText("0");
		        initTime();
					
		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
			if(_game==null) {
				openFile();
				pipeDisplayer.playMusic();
			}
			
			pipeDisplayer.setPipeDisplayer(_game);
			pipeDisplayer.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
					(javafx.scene.input.MouseEvent click)->{
						if(_game!=null) {
							double w=pipeDisplayer.getWidth()/_game.get(0).length();
							double h=pipeDisplayer.getHeight()/_game.size();
							int x= (int)(click.getX()/w);
							int y=(int)(click.getY()/h);
							if(pipeDisplayer.pipeClicked(x, y)) {
								pipeDisplayer.steps++;
								showSteps();
								
							}
							//System.out.println("location: "+x+","+y+" ,canvas size: "+w+","+h);
						}
					
					});
			
		}
	public void costumTheme() {
		
		
	}
		

	
}
