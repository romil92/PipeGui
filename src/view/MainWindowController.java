package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

public class MainWindowController implements Initializable{
	
	@FXML
	PipeDisplayer pipeDisplayer;
	
	@FXML 
	BorderPane window;
	
	@FXML
	private TextField numMoves = new TextField();
	
	@FXML
	private TextField numTime = new TextField();
	
	@FXML
	private TextField idPort = new TextField();

	
	File _boardFile=null;
	ArrayList<String> _game;
	int moves = 0;
	int secondsPlay = 0;
	Timeline timer;
	String serverIP ;
	Integer portNumber ;

	
	public void initTime() {
		if (timer != null)
		{
			timer.stop();
			secondsPlay = 0;
		}
		numTime.setText(Integer.toString(secondsPlay));
		timer = new Timeline(new KeyFrame(Duration.millis(1000), e-> {
			secondsPlay++;
			numTime.setText(Integer.toString(secondsPlay));
		}));
		timer.setCycleCount(Animation.INDEFINITE);
		timer.play();
	}
	
	public String getIP() {
		return this.serverIP;
	}
	
	public int getPort() {
		return this.portNumber;
	}
	
	public void setIP(String ip) {
		this.serverIP = ip;
	}
	
	public void setPort() {
		System.out.println("string");
		System.out.println(idPort.toString());
		System.out.println("text");
		System.out.println(idPort.getText());
		this.portNumber = Integer.parseInt(idPort.getText());


	}
	
	public void solve() throws InterruptedException{
		timer.pause();
		System.out.println(idPort.getText());
		this.portNumber = Integer.parseInt(idPort.getText());
		
		
		try {
			
		    Socket mySocket = new Socket(this.getIP(), this.getPort());
		    PrintWriter out = new PrintWriter(mySocket.getOutputStream());
		    System.out.println("p1");
		    //ArrayList<String> temp2= new ArrayList<String>(_game);
		    for (int i = 0; i < this._game.size(); i++)
		    	out.println(new String(this._game.get(i).toString()));
		    out.println("done");
		    out.flush();
			BufferedReader in = new BufferedReader(new java.io.InputStreamReader(mySocket.getInputStream()));
			Timeline sortLoop = new Timeline(); 
			double curDelay = 0.2;
			String line;
			while (!(line = in.readLine()).equals("done")) { 
		        int i = Integer.parseInt(line.split(",")[0]); //rows
		        int j = Integer.parseInt(line.split(",")[1]); // cols
		        int times = Integer.parseInt(line.split(",")[2]); //rotations
		        //board.switchCell(i, j, times);
		        int rot = 0;
		        while( rot < times) {
					KeyFrame kf = new KeyFrame(Duration.seconds(curDelay), actionEvent -> {
						this._game = rotate(this._game,i,j);
						pipeDisplayer.setPipeDisplayer(this._game);		
					});
					rot++;
					curDelay += 0.2;
		            sortLoop.getKeyFrames().add(kf);
				}
		      }
		      sortLoop.play();
		      in.close();
		      out.close();
		      mySocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
		
	}
	
	public static ArrayList<String> rotate(ArrayList<String> myLevel,int row, int col){
		StringBuilder temp = new StringBuilder(myLevel.get(row));
		ArrayList<String> a = new ArrayList<String>(myLevel);
		
		switch(temp.charAt(col)) {
		case 'L':
			temp.setCharAt(col, 'f');
			break;
		case 'j':
			temp.setCharAt(col, 'L');
			break;
		case '7':
			temp.setCharAt(col, 'j');
			break;
		case 'f':
			temp.setCharAt(col, '7');
			break;
		case '-':
			temp.setCharAt(col, '|');
			break;
		case '|':
			temp.setCharAt(col, '-');
			break;
		}
		
		a.set(row, temp.toString());
		return a;
	}
	
	public void start() {
		
		System.out.println("start");
	}
	
	public MainWindowController() {
		super();
		numMoves.setText("0");
		this.serverIP = "127.0.0.1";
		this.portNumber = 0;
		
	}
	
	public void spongeBob() {
		System.out.println("spongeBob");
		Theme t = new Theme();
		pipeDisplayer.stopMusic();
		t.setTheme("./resources/spongeBoB/sad spongeBob.png", "./resources/spongeBoB/sponge pinnapple.png",
				"./resources/spongeBoB/right-up.jpeg", "./resources/spongeBoB/right-down.jpeg", 
				"./resources/spongeBoB/left-up.jpeg", "./resources/spongeBoB/left-down.jpeg",
				"./resources/spongeBoB/horizontal.jpeg", "./resources/spongeBoB/vertical.jpeg",
				"./resources/media/spongebob.wav", "./resources/spongeBoB/sad spongeBob.png", "./resources/spongeBoB/background.jpg");	
		pipeDisplayer.setTheme(t);
		pipeDisplayer.playMusic();
		
	}
	
	public void stam() {
		System.out.println("stam");
		Theme t = new Theme();
		pipeDisplayer.stopMusic();
		t.setTheme("./resources/Dora/dora.jpg", "./resources/Dora/buts.gif",
				"./resources/spongeBoB/right-up.jpeg", "./resources/spongeBoB/right-down.jpeg", 
				"./resources/spongeBoB/left-up.jpeg", "./resources/spongeBoB/left-down.jpeg",
				"./resources/spongeBoB/horizontal.jpeg", "./resources/spongeBoB/vertical.jpeg",
				"./resources/media/Dora The Explorer Theme Song.wav", "./resources/spongeBoB/sad spongeBob.png", "./resources/spongeBoB/background.jpg");	
		pipeDisplayer.setTheme(t);
		pipeDisplayer.playMusic();
		
	}

	
	public void custom() {
		System.out.println("custom");
	}
	
	
	
	public void openFile() throws IOException {
		File chosen;
		FileChooser fc=new FileChooser();
		fc.setTitle("open pipe game file");
		fc.setInitialDirectory(new File("./resources"));
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
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		        try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
		        _game=pipeGameLev;
		        pipeDisplayer.setPipeDisplayer(_game);
		        numMoves.setText("0");
		        this.initTime();

		}
	}


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(_game == null) {
			try {
				this.openFile();
				pipeDisplayer.playMusic();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		System.out.println();
		pipeDisplayer.setPipeDisplayer(_game);
		pipeDisplayer.addEventHandler(MouseEvent.MOUSE_CLICKED, 
			(MouseEvent e)->{
				if(_game != null) {
					double w = pipeDisplayer.getWidth() / _game.get(0).length();
					double h = pipeDisplayer.getHeight() / _game.size();
					int x = (int) (e.getX() / w);
					int y = (int) (e.getY() / h);
					boolean flag = false;
					flag = pipeDisplayer.changeByclick(x,y);
					if(flag == true) {
						moves++;
						
						numMoves.setText(Integer.toString(moves));
					}
				}
			}
		);
	}
	
}
