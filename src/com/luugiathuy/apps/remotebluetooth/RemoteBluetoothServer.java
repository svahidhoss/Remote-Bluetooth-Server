package com.luugiathuy.apps.remotebluetooth;

public class RemoteBluetoothServer {
	// The main method creates a thread to wait for connection from client and
	// later handle the communication.
	public static void main(String[] args) {
		Thread waitThread = new Thread(new WaitThread());
		waitThread.start();
	}

}
