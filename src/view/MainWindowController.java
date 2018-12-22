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

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

public class MainWindowController implements Initializable{
	
	@FXML
	PipeDisplayer pipeDisplayer;
	
	@FXML 
	BorderPane window;
	
	ArrayList<String> _game;
	
	public void start() {
		
		//System.out.println("start");
	}
	
	
	public MainWindowController() {
		super();
		
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
		        _game=pipeGame;
			pipeDisplayer.setPipeDisplayer(_game);
		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			
			if(_game==null) {
				openFile();
			}
			
			pipeDisplayer.setPipeDisplayer(_game);
			pipeDisplayer.addEventHandler(javafx.scene.input.MouseEvent.MOUSE_CLICKED,
					(javafx.scene.input.MouseEvent click)->{
						if(_game!=null) {
							double w=pipeDisplayer.getWidth()/_game.get(0).length();
							double h=pipeDisplayer.getHeight()/_game.size();
							int x= (int)(click.getX()/w);
							int y=(int)(click.getY()/h);
							pipeDisplayer.pipeClicked(x, y);
							//System.out.println("location: "+x+","+y+" ,canvas size: "+w+","+h);
						}
					
					});
			
		}
		

	
}
