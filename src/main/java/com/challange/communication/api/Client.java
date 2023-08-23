package com.challange.communication.api;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;

import com.challange.communication.player.Player;
import com.challange.communication.player.PlayerBuilder;
import com.challange.communication.util.Constants;
public class Client extends BaseApi {

	public Client() throws ConnectException {
		super();
		try (Socket socket = new Socket(Constants.hostname, Constants.port);
			 ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
			 ObjectInputStream is = new ObjectInputStream(socket.getInputStream());){

			player = new PlayerBuilder().setName(Constants.player2).initializeMessage().getPlayer();

			print("Server found");
			print("Initializing Client");
			print("Client Connected!");

			player.getResponseFor(player);
			print("Sending From Client: " + player.getMessage());
			os.writeObject(player);

			chat(player, is, os);
		} catch (ConnectException e) {
			throw new ConnectException();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	void chat(Player player, ObjectInputStream is, ObjectOutputStream os) throws IOException, ClassNotFoundException {
		while ((sender = (Player) is.readObject()) != null) {
			if (sender.getMessageCount().intValue() == Constants.messageCount && player.getMessageCount().intValue() == Constants.messageCount) {
				super.exitApplication();
			}
			player.getResponseFor(sender);
			print("Sending From Client: " + player.getMessage());
			os.reset();
			os.writeObject(player);
		}
	}
}
