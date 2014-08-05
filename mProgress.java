import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
public class mProgress {
	
	 JProgressBar progressBar;
	   // JCheckBox progressType;
	   // JCheckBox switchType;
	    //final JButton goButton;
	    
	    //Note: Typically the main method will be in a
	    //separate class. As this is a simple one class
	    //example it's all in the one class.

	    
	 
	    public mProgress()
	    {
	        JFrame guiFrame = new JFrame();
	        
	        //make sure the program exits when the frame closes
	        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        guiFrame.setTitle("Creating a Table Example");
	        guiFrame.setSize(1000,200);
	        guiFrame.setLocationRelativeTo(null);
	      
     
	        progressBar = new JProgressBar(0, 100);
	        progressBar.setValue(0);     
	        guiFrame.add(progressBar, BorderLayout.CENTER);

	        
	        guiFrame.setVisible(true);
	        progressBar.setStringPainted(true);
  
	    }
	    
	    public void mSetProgress(int p){
	    	progressBar.setValue(p);
	    }

	}