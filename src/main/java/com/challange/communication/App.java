package com.challange.communication;

import java.net.ConnectException;

import com.challange.communication.api.Client;
import com.challange.communication.api.Server;


public class App {

	public static void main(String[] args) {
		try {
			startClient();
		} catch (ConnectException e) {
			startServer();
		}
	}
	private static void startClient() throws ConnectException {
		print("Looking for a server");
		try {
			new Client();
		} catch (ConnectException e) {
			throw new ConnectException();
		}
	}


	private static void startServer() {
		print("Server could not found");
		print("Initializing a Server");
		new Server();
	}


	private static void print(String str) {
		System.out.println(str);
	}
}
