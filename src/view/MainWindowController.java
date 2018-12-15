package view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class MainWindowController {
	
	@FXML
	PipeDisplayer pipeDisplayer;
	
	
	public void start() {
		
		System.out.println("start");
	}
	
	
	public void openFile() {
		FileChooser fc=new FileChooser();
		fc.setTitle("open pipe game file");
		fc.setInitialDirectory(new File("./resources"));
		//fc.getSelectedExtensionFilter()
		File chosen = fc.showOpenDialog(null);
		
		if(chosen != null) {
			pipeDisplayer.setPipeDisplayer(chosen);
		}
		
	}
}
