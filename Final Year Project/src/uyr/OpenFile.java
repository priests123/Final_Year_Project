package uyr;

import javax.swing.JFileChooser;

public class OpenFile {

	JFileChooser fileChooser = new JFileChooser("C:\\Users\\Michael\\Desktop");
	StringBuilder sb = new StringBuilder();
	
	//Produces the file selector window when importing a value
	public void filePicked(){
		if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			java.io.File file = fileChooser.getSelectedFile();
			sb.append(file);
		}else{
			sb.append("No file selected");
		}
	}
	
}
