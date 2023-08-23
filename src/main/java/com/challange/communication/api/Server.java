package com.challange.communication.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.challange.communication.player.Player;
import com.challange.communication.player.PlayerBuilder;
import com.challange.communication.util.Constants;
public class Server extends BaseApi {

	ServerSocket serverSocket;


	public Server() {
		super();
		try (ServerSocket serverSocket = new ServerSocket(Constants.port);
			 Socket socket = serverSocket.accept();
			 ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			 ObjectInputStream is = new ObjectInputStream(socket.getInputStream());) {

			player = new PlayerBuilder().setName(Constants.player1).getPlayer();

			print("Server Initialized");

			chat(player, is, os);
		}
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	void chat(Player player, ObjectInputStream is, ObjectOutputStream os) throws IOException, ClassNotFoundException {
		while ((sender = (Player) is.readObject()) != null) {
			player.getResponseFor(sender);
			print("Sending From Server: " + player.getMessage());
			os.reset();
			os.writeObject(player);
			if (sender.getMessageCount().intValue() == Constants.messageCount && player.getMessageCount().intValue() == Constants.messageCount) {
				super.exitApplication();
			}
		}
	}
}
