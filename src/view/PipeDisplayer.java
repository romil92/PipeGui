package view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PipeDisplayer extends Canvas{

	
	private ArrayList<String> pipeGame;
	private StringProperty s;
	private	StringProperty g;
	private StringProperty L;
	private StringProperty f;
	private StringProperty j;
	private StringProperty seven;
	private StringProperty finished;
	private StringProperty hor;
	private StringProperty ver;
	private StringProperty music;
	private Thread musicThread;
	private MediaPlayer player;
	Integer steps;
	@FXML
	PipeDisplayer pipeDisplayer;
	void stopMusic() {
		if(this.musicThread!=null) {
			player.stop();
			this.musicThread.stop();
			
		}	
		
	}
	
	void playMusic() {
		

			 File file = new File(music.get());
			 player =new MediaPlayer(new Media(file.toURI() .toString()));		 
			 player.setOnEndOfMedia(musicThread=new Thread(new Runnable() {
			       public void run() {
			         player.seek(Duration.ZERO);
			       }
			   }));
			  player.play();
		
		
		
	}
	
	public Boolean pipeClicked(int x,int y) {
		StringBuilder row=new StringBuilder(pipeGame.get(y));
		ArrayList<String> a=new ArrayList<String>(pipeGame);
		char clicked =row.charAt(x);
		boolean flag=false;
		switch (clicked) {
		case 'f':
			row.setCharAt(x, '7');
			flag=true;
			break;

		case 'j':
			row.setCharAt(x, 'L');
			flag=true;
			break;
		
		case 'L':
			row.setCharAt(x, 'f');
			flag=true;
			break;
		
		case '7':
			row.setCharAt(x, 'j');
			flag=true;
			break;
			
		case '|':
			row.setCharAt(x, '-');
			flag=true;
			break;
	
		case '-':
			row.setCharAt(x, '|');
			flag=true;
			break;
	
		default:
			break;
		}
		
		a.set(y,row.toString());
		this.setPipeDisplayer(a);
		return flag;

		
	}

	public String getHor() {
		return hor.get();
	}

	public void setHor(String hor) {
		this.hor.set(hor);
	}
	
	public String getVer() {
		return ver.get();
	}

	public void setVer(String ver) {
		this.ver.set(ver);
	}

	public String getS() {
		return s.get();
	}

	public void setS(String s) {
		this.s.set(s);
	}

	public String getG() {
		return g.get();
	}

	public void setG(String g) {
		this.g.set(g);
	}

	public String getL() {
		return L.get();
	}

	public void setL(String l) {
		this.L.set(l);
	}

	public String getF() {
		return f.get();
	}

	public void setF(String f) {
		this.f.set(f);
	}

	public String getMusic() {
		return music.get();
	}
	public void setMusic(String music) {
		this.music.set(music);
	}
	public String getJ() {
		return j.get();
	}

	public void setJ(String j) {
		this.j.set(j);
	}

	public String getSeven() {
		return seven.get();
	}

	public void setSeven(String seven) {
		this.seven.set(seven);
	}

	public String getFinished() {
		return finished.get();
	}	
	public PipeDisplayer() {
		pipeGame = new ArrayList<String>();
		this.s=new SimpleStringProperty();
		this.f=new SimpleStringProperty();
		this.g=new SimpleStringProperty();
		this.j=new SimpleStringProperty();
		this.L=new SimpleStringProperty();
		this.finished=new SimpleStringProperty();
		this.seven=new SimpleStringProperty();
		this.ver=new SimpleStringProperty();
		this.hor=new SimpleStringProperty();
		this.music=new SimpleStringProperty();
		this.musicThread=null;
		this.player=null;
		this.music.set(new Theme().get_music());
		this.steps=0;
	}
	
	public void setFinished(StringProperty finished) {
		this.finished = finished;
	}
	
	public void setPipeDisplayer(ArrayList<String> game) {
		
		if(pipeGame==null) {
			this.setTheme(new Theme());
			
		}
		pipeGame=game;
		if(pipeGame!=null)
			redraw();
		
		
	}
	public void setTheme(Theme t) {
		s.set(t.get_s());
		f.set(t.get_f());
		g.set(t.get_g());
		j.set(t.get_j());
		L.set(t.get_L());
		finished.set(t.get_finished());
		seven.set(t.get_seven());
		ver.set(t.get_ver());
		hor.set(t.get_hor());
		music.set(t.get_music());
	
		//playMusic();
		
			
		
		
		redraw();
		
	}
	
	@Override
	public boolean isResizable() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public double minHeight(double width) {
		// TODO Auto-generated method stub
		return 90;
	}
	@Override
	public double maxHeight(double width) {
		// TODO Auto-generated method stub
		return 1200;
	}
	@Override
	public double prefHeight(double width) {
		// TODO Auto-generated method stub
		return super.prefHeight(width);
	}
	@Override
	public double minWidth(double height) {
		// TODO Auto-generated method stub
		return 10;
	}
	@Override
	public double maxWidth(double height) {
		// TODO Auto-generated method stub
		return 1200;
	}
	@Override
	public double prefWidth(double height) {
		// TODO Auto-generated method stub
		return super.prefWidth(height);
	}
	@Override
	public void resize(double width, double height) {
		// TODO Auto-generated method stub
		super.setWidth(width-15);
		super.setHeight(height-10);
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
				startImage = new Image(new FileInputStream(s.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Image horizontal=null;
			try {
				horizontal = new Image(new FileInputStream(hor.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image leftDown=null;
			try {
				leftDown = new Image(new FileInputStream(seven.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image leftUp=null;
			try {
				leftUp = new Image(new FileInputStream(j.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image rightDown=null;
			try {
				rightDown = new Image(new FileInputStream(f.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image rightUp=null;
			try {
				rightUp = new Image(new FileInputStream(L.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image vertical=null;
			try {
				vertical = new Image(new FileInputStream(ver.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Image goal=null;
			try {
				goal = new Image(new FileInputStream(g.get()));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//TODO set finished photo
			
			gc.clearRect(0, 0, W, H);
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
