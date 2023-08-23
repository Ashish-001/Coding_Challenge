package com.challange.communication.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.challange.communication.player.Player;

public abstract class BaseApi {

	Socket socket = null;
	Player player = null;
	Player sender = null;


	abstract void chat(Player player, ObjectInputStream is, ObjectOutputStream os) throws IOException, ClassNotFoundException;

	protected void exitApplication() throws IOException {
		System.out.println("Game Finished!!");
		System.exit(0);
	}


	protected void print(String s) {
		System.out.println(s);
	}
}
