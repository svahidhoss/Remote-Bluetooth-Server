package com.luugiathuy.apps.remotebluetooth;

import java.io.IOException;


import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class WaitThread implements Runnable{

	/** Constructor */
	public WaitThread() {
	}
	
	@Override
	public void run() {
		waitForConnection();		
	}
	
	/** Waiting for connection from devices */
	private void waitForConnection() {
		// retrieve the local Bluetooth device object
		LocalDevice local = null;
		
		StreamConnectionNotifier notifier;
		StreamConnection connection = null;
		
		// setup the server to listen for connection
		try {
			local = LocalDevice.getLocalDevice();
			local.setDiscoverable(DiscoveryAgent.GIAC);
			
			UUID uuid = new UUID("04c6032b00004000800000805f9b34fc", false);
			
//			UUID uuid = new UUID("0000110100001000800000805F9B34FB", false);
//			System.out.println("0000110100001000800000805F9B34FB");
//			UUID uuid = new UUID(0x1101);
			
//			UUID uuid = new UUID("d0c722b07e1511e1b0c40800200c9a66", false);
			
			System.out.println(uuid.toString());
			
            String url = "btspp://localhost:" + uuid + ";name=RemoteBluetooth";
            notifier = (StreamConnectionNotifier) Connector.open(url);
            
        } catch (BluetoothStateException e) {
        	System.out.println("Bluetooth is not turned on.");
			e.printStackTrace();
			return;
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		// waiting for connection
		while(true) {
			try {
				System.out.println("waiting for connection...");
				
	            connection = notifier.acceptAndOpen();
	            Thread processThread = new Thread(new ProcessConnectionThread(connection));
	            processThread.start();
	            
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}
}
