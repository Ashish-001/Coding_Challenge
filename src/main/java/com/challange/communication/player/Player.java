package com.challange.communication.player;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;


public class Player implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String name;
	private AtomicInteger messageCount;
	private String message;

	public Player(String name, AtomicInteger messageCount, String message) {
		this.name = name;
		this.messageCount = messageCount;
		this.message = message;
	}

	public String getName() {
		return name;
	}

	public AtomicInteger getMessageCount() {
		return messageCount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	public void increaseMessageCount() {
		this.messageCount.incrementAndGet();
	}
	public void getResponseFor(Player sender) {
		String m = sender.getMessage() + " " + this.getMessageCount();
		increaseMessageCount();
		this.setMessage(m);
	}
}
