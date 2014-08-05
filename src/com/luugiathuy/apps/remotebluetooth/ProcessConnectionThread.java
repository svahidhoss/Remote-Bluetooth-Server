package com.luugiathuy.apps.remotebluetooth;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import javax.microedition.io.StreamConnection;

public class ProcessConnectionThread implements Runnable{

	private StreamConnection mConnection;


	ArrayList<mProgress> ListProgress;
	String all="";
	int max=10;
	
	InputStream inputStream;
	

	
	public ProcessConnectionThread(StreamConnection connection)
	{
		mConnection = connection;
		String[] labels = {"X", "Y", "Z", "mod", "Filtr"}; 
		//String[] labels = {"az", "pitch", "roll", "mod"};//, "Filtr"}; 
		ListProgress = new ArrayList<mProgress>();
		//****
		
		for (int k=0;k<labels.length;k++){
			ListProgress.add(new mProgress(labels[k], max));
			
		}
		//*****
	
		
		
	}
	
	@Override
	public void run() {
		try {
			

			
			// prepare to receive data
			inputStream = mConnection.openInputStream();
	        
			System.out.println("waiting for input");
			
			 
	        while (true) {
  	
	        	byte[] b = new byte[1024];  //this size have to be sufficient, but nothing happens if it's too big, we are going to use only the writes bytes.
	        	int x = inputStream.read(b);  //x give us the number of writed bytes. if x=-1, there is nothing connected (more less).
	        	
	        	if (x==-1){
	        		System.out.println("Device Lost");
	        		inputStream.close();
	        		break;
	       		}

	        	ArrayList<Double> d = new ArrayList<Double>();
	        	byte[] aux = new byte[8];
	        	int j=0;
	        	int ind=0;
	        	for (int i=0; i<(x);i++){
	        		if (j==8){ //because the numbers for read will be doubles (1 double = 8 bytes)
	        			j=0;
	        			double inputValue = toDouble(aux);
	        			System.out.print(""+redondeo(inputValue,1)+" ");  //multiply by 10 for have precision, but without decimals.
	        			
	        			actualize(ind, (int)(inputValue));
	        			
	        			ind++;
	
	        		}
	        		aux[j]=b[i];
	        		j++;	
	        	}
	        
	        	double inputValue = toDouble(aux);
	        	
	        	System.out.println(""+(redondeo(inputValue,1)));
	        	actualize(ind, (int)(inputValue));

        	}
	        
        } catch (Exception e) {
    		e.printStackTrace();
    		System.out.print("\nfin...?");
    		try {
				inputStream.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
    		
    	}
	}
	

	

	private void actualize(int index, int v) {
		if (index>=ListProgress.size() || index<0){
			System.out.println("\n\n***********************\n***************\n");
		}else{
			ListProgress.get(index).mSetProgress(v);
			
		}
	
	}

	public static byte[] toByteArray(double value) {
	    byte[] bytes = new byte[8];
	    ByteBuffer.wrap(bytes).putDouble(value);
	    return bytes;
	}

	public static double toDouble(byte[] bytes) {
	    return ByteBuffer.wrap(bytes).getDouble();
	}
	
	
	public String redondeo(double num, int decimales)
	{
		String res;
		double cifras = Math.pow(10.0, decimales);
		res = "" + (Math.round(num*cifras)/cifras);
		return res;
	}

}
