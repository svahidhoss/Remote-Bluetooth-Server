package com.luugiathuy.apps.remotebluetooth;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
public class mProgress {
	
	 JProgressBar progressBar;   
	 
	    public mProgress(String label, int max)
	    {
	        JFrame guiFrame = new JFrame();
	        
	        //make sure the program exits when the frame closes
	        guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        guiFrame.setTitle(label);
	        guiFrame.setSize(1000,150);
	        guiFrame.setLocationRelativeTo(null);
	      
     
	        progressBar = new JProgressBar(0, max);  //resize the progress bar.
	        progressBar.setValue(0);

	        guiFrame.add(progressBar, BorderLayout.CENTER);

	        
	        guiFrame.setVisible(true);
	        progressBar.setStringPainted(true);
  
	    }
	    
	    public void mSetProgress(int p){
	    	progressBar.setValue((int)Math.abs(p)); //not accepted negative values
	       // progressBar.setStringPainted(true);
	        progressBar.setString(""+p); //to see the exact value that are drawn, in other case, it converts from 0% to 100%
	    }
	    

	}